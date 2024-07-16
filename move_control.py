#https://github.com/yorkrobotlab/pi-puck-ros/blob/master/src/pi_puck_driver/src/pi_puck_motors/motor_server.py
#

import math

import grpc
from smbus2 import SMBus, i2c_msg
import sys
import time
import traceback

import logging
from concurrent import futures

import grpc

from out_python import Client_pb2 as Client_pb2
from out_python import Client_pb2_grpc as Client_pb2_grpc

I2C_CHANNEL = 12
LEGACY_I2C_CHANNEL = 4
ROB_ADDR = 0x1F
ACTUATORS_SIZE = (19 + 1)  # Data + checksum.
SENSORS_SIZE = (46 + 1)  # Data + checksum.

actuators_data = bytearray([0] * ACTUATORS_SIZE)
sensors_data = bytearray([0] * SENSORS_SIZE)
prox = [0 for x in range(8)]
prox_amb = [0 for x in range(8)]
mic = [0 for x in range(4)]
mot_steps = [0 for x in range(2)]

WHEEL_DIAMETER = 4.0  # cm
WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * math.pi / 100.0  # in metres
WHEEL_DISTANCE = 0.053  # the distance between the wheels in metres
MOTOR_STEP_DISTANCE = WHEEL_CIRCUMFERENCE / 1000.0  # 1 turn should be 1000 steps

MAX_MOTOR_STEPS_RAW = 65536

MAX_SPEED = 1000


i2c_next_call = time.time()

def update_robot_sensors_and_actuators():
    global sensors_data, actuators_data, i2c_next_call
    time_to_wait = i2c_next_call - time.time()
    if time_to_wait > 0:
        time.sleep(time_to_wait)
    i2c_next_call = time.time() + 0.05
    try:
        write = i2c_msg.write(ROB_ADDR, actuators_data)
        read = i2c_msg.read(ROB_ADDR, SENSORS_SIZE)
        bus.i2c_rdwr(write, read)
        sensors_data = list(read)
    except Exception as e:
        traceback.print_exc()
        sys.exit(1)
        #print("try again")

        #update_robot_sensors_and_actuators()


try:
    bus = SMBus(I2C_CHANNEL)
except:
    try:
        bus = SMBus(LEGACY_I2C_CHANNEL)
    except:
        print("Cannot open I2C device")
        sys.exit(1)

counter = 0
actuators_state = 0

def move_wheels(left_speed, right_speed):
    actuators_data[0] = 0  # Left speed: 512
    actuators_data[1] = left_speed
    actuators_data[2] = 0  # Right speed: -512
    actuators_data[3] = right_speed

    checksum = 0
    for i in range(ACTUATORS_SIZE - 1):
        checksum ^= actuators_data[i]
    actuators_data[ACTUATORS_SIZE - 1] = checksum

    update_robot_sensors_and_actuators()


for i in range(2):
    mot_steps[i] = sensors_data[41 + i * 2 + 1] * 256 + sensors_data[41 + i * 2]
initial_left_steps = int(mot_steps[0])
initial_right_steps = int(mot_steps[1])

# Initial steps are set to counter the fact that the node may have previously run and the
# e-puck firmware will not necessarily have been reset.
_left_steps_previous = initial_left_steps
_right_steps_previous = initial_right_steps
_real_left_steps_previous = initial_left_steps
_real_right_steps_previous = initial_right_steps

_left_overflows = 0
_right_overflows = 0

def calculate_real_steps():
    global _left_overflows, _right_overflows, _left_steps_previous, _right_steps_previous
    # Get current steps
    for i in range(2):
        mot_steps[i] = sensors_data[41 + i * 2 + 1] * 256 + sensors_data[41 + i * 2]
    left_steps = int(mot_steps[0]) - MAX_MOTOR_STEPS_RAW/2
    right_steps = int(mot_steps[1])- MAX_MOTOR_STEPS_RAW/2

    # Check for overflows and underflows
    if left_steps + (MAX_MOTOR_STEPS_RAW / 2.0) < _left_steps_previous:
        _left_overflows += 1
    elif left_steps > _left_steps_previous + (MAX_MOTOR_STEPS_RAW / 2.0):
        _left_overflows -= 1

    if right_steps + (MAX_MOTOR_STEPS_RAW / 2.0) < _right_steps_previous:
        _right_overflows += 1
    elif right_steps > _right_steps_previous + (MAX_MOTOR_STEPS_RAW / 2.0):
        _right_overflows -= 1

    # Calculate the real steps left and right accounting for overflows
    real_left_steps = left_steps + _left_overflows * MAX_MOTOR_STEPS_RAW
    real_right_steps = right_steps + _right_overflows * MAX_MOTOR_STEPS_RAW

    # Update previous steps to current
    _left_steps_previous = left_steps
    _right_steps_previous = right_steps
    _real_left_steps_previous = real_left_steps
    _real_right_steps_previous = real_right_steps
    print("left", left_steps, "oberflow", _left_overflows, "real", real_left_steps, "prev", _left_steps_previous)

    return real_left_steps, real_right_steps





def turn(theta):
    print("theta", theta)

    # in bereich -pi bis pi konvertieren
    theta = theta % (2 * math.pi)
    if theta > math.pi:
        theta -= 2 * math.pi

    real_left_steps, _ = calculate_real_steps()
    turn_direction = 1
    theta_to_goal = theta
    if theta < 0:
        turn_direction = -1
        theta_to_goal = -1 * theta
    print("to goal theta", theta_to_goal)

    steps_for_turn = ((((theta_to_goal * WHEEL_DISTANCE) / 2) / WHEEL_CIRCUMFERENCE) * 1000)


    print( "real", real_left_steps, "steps for turn", steps_for_turn)
    from_steps = real_left_steps - steps_for_turn
    to_steps = real_left_steps + steps_for_turn


    while  from_steps  < real_left_steps < to_steps:
        real_left_steps, _ = calculate_real_steps()
        move_wheels((256 + turn_direction * 2) % 256, (256 - turn_direction * 2) % 256)

    move_wheels(0, 0)
    print("reached turn")

def move_straight(distance):
    # Update the estimate for x, y, and rotation
    real_left_steps, _ = calculate_real_steps()

    step_goal_left = distance/ MOTOR_STEP_DISTANCE
    from_steps = real_left_steps - step_goal_left
    to_steps = real_left_steps + step_goal_left

    print("distance_steps:", distance/ MOTOR_STEP_DISTANCE, "real", real_left_steps, "from", from_steps, "to", real_left_steps, "to", to_steps)

    while from_steps < real_left_steps < to_steps:
        real_left_steps, _ = calculate_real_steps()
        move_wheels(2, 2)

    move_wheels(0, 0)
    print("reached straigt")


def move_to(x, y, theta_final):

    theta_to_goal = math.atan2(y, x)

    turn(theta_to_goal)

    move_straight(math.sqrt(x ** 2 + y ** 2) )

    turn(theta_final - theta_to_goal)



class Tracker(Client_pb2_grpc.ClientServicer):
    def MoveTo(self, request, context):
        move_to(request.x, request.y, request.theta)
        return Client_pb2.MoveResponse(success=True)

    def Ping(self, request, context):
        return Client_pb2.PingResponse(robot_id=1)


def serve():
    port = "50051"
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    Client_pb2_grpc.add_ClientServicer_to_server(Tracker(), server)
    server.add_insecure_port("[::]:" + port)
    server.start()
    print("Server started, listening on " + port)
    server.wait_for_termination()

if __name__ == "__main__":
    logging.basicConfig()
    serve()

# while 1:
#     checksum = 0
#     for i in range(ACTUATORS_SIZE - 1):
#         checksum ^= actuators_data[i]
#     actuators_data[ACTUATORS_SIZE - 1] = checksum
#
#     update_robot_sensors_and_actuators()
#
#     move_to(0.1,0.1,math.pi/2)
#     move_wheels(0,0)
#     print("stopp")
#     break



