#https://github.com/yorkrobotlab/pi-puck-ros/blob/master/src/pi_puck_driver/src/pi_puck_motors/motor_server.py
#

import math

from smbus2 import SMBus, i2c_msg
import sys
import time

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




def update_robot_sensors_and_actuators():
    global sensors_data
    global actuators_data
    try:
        write = i2c_msg.write(ROB_ADDR, actuators_data)
        read = i2c_msg.read(ROB_ADDR, SENSORS_SIZE)
        bus.i2c_rdwr(write, read)
        sensors_data = list(read)
    except:
        sys.exit(1)


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
    global _left_overflows, _right_overflows
    # Get current steps
    for i in range(2):
        mot_steps[i] = sensors_data[41 + i * 2 + 1] * 256 + sensors_data[41 + i * 2]
    left_steps = int(mot_steps[0])
    right_steps = int(mot_steps[1])

    # Check for overflows and underflows
    if left_steps + (MAX_MOTOR_STEPS_RAW / 2.0) < _left_steps_previous:
        _left_overflows = _left_overflows + 1
    elif left_steps > _left_steps_previous + (MAX_MOTOR_STEPS_RAW / 2.0):
        _left_overflows -= 1

    if right_steps + (MAX_MOTOR_STEPS_RAW / 2.0) < _right_steps_previous:
        _right_overflows += 1
    elif right_steps > _right_steps_previous + (MAX_MOTOR_STEPS_RAW / 2.0):
        _right_overflows -= 1

    # Calculate the real steps left and right accounting for overflows
    real_left_steps = left_steps + _left_overflows * MAX_MOTOR_STEPS_RAW
    real_right_steps = right_steps + _right_overflows * MAX_MOTOR_STEPS_RAW

    return real_left_steps, real_right_steps


def turn(theta):
    print(theta)

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
    print("to goal", theta_to_goal)

    steps_for_turn = (((theta_to_goal * WHEEL_DISTANCE) / 2) * MOTOR_STEP_DISTANCE)

    print("steps to goal", steps_for_turn)

    step_goal_left = steps_for_turn + real_left_steps

    print(step_goal_left, real_left_steps, steps_for_turn)

    # TODO zählt der die Schritte immer Hoch auch wenn der Rückwärts fährt?
    while real_left_steps < step_goal_left:
        real_left_steps, _ = calculate_real_steps()
        move_wheels(turn_direction * 2, (256 - turn_direction * 2) % 256)
        print(real_left_steps)

    print("reached")

def move_straight(distance):
    # Update the estimate for x, y, and rotation
    real_left_steps, _ = calculate_real_steps()

    step_goal_left = distance/ MOTOR_STEP_DISTANCE + real_left_steps

    print("distance_steps:", distance/ MOTOR_STEP_DISTANCE)

    while real_left_steps < step_goal_left:
        real_left_steps, _ = calculate_real_steps()
        move_wheels(2, 2)
        print(real_left_steps)


def ros_geklaut(x, y, theta_final):

    theta_to_goal = math.atan2(y, x)

    turn(theta_to_goal)

    move_straight(math.sqrt(x ** 2 + y ** 2) )

    turn(theta_final - theta_to_goal)




while 1:

    start = time.time()

    checksum = 0
    for i in range(ACTUATORS_SIZE - 1):
        checksum ^= actuators_data[i]
    actuators_data[ACTUATORS_SIZE - 1] = checksum

    update_robot_sensors_and_actuators()

    #ros_geklaut(0.1,0.1,math.pi/2)
    move_wheels(0,0)
    print("stopp")
    break

    # if len(sensors_data) < 0:
    #	sys.exit(1)

    # Verify the checksum (Longitudinal Redundancy Check) before interpreting the received sensors data.
    checksum = 0
    for i in range(SENSORS_SIZE - 1):
        checksum ^= sensors_data[i]
    if (checksum == sensors_data[SENSORS_SIZE - 1]):
        for i in range(8):
            prox[i] = sensors_data[i * 2 + 1] * 256 + sensors_data[i * 2]
        # print(
        #     "prox: {0:4d}, {1:4d}, {2:4d}, {3:4d}, {4:4d}, {5:4d}, {6:4d}, {7:4d}\r\n".format(prox[0], prox[1], prox[2],
        #                                                                                       prox[3], prox[4], prox[5],
        #                                                                                       prox[6], prox[7]))
        #
        # for i in range(8):
        #     prox_amb[i] = sensors_data[16 + i * 2 + 1] * 256 + sensors_data[16 + i * 2]
        # print("ambient: {0:4d}, {1:4d}, {2:4d}, {3:4d}, {4:4d}, {5:4d}, {6:4d}, {7:4d}\r\n".format(prox_amb[0],
        #                                                                                            prox_amb[1],
        #                                                                                            prox_amb[2],
        #                                                                                            prox_amb[3],
        #                                                                                            prox_amb[4],
        #                                                                                            prox_amb[5],
        #                                                                                            prox_amb[6],
        #                                                                                            prox_amb[7]))
        #
        # for i in range(4):
        #     mic[i] = sensors_data[32 + i * 2 + 1] * 256 + sensors_data[32 + i * 2]
        # print("mic: {0:4d}, {1:4d}, {2:4d}, {3:4d}\r\n".format(mic[0], mic[1], mic[2], mic[3]))
        #
        # print("sel: {0:2d}\r\n".format(sensors_data[40] & 0x0F))
        # print("button: {0:1d}\r\n".format(sensors_data[40] >> 4))
        for i in range(2):
            mot_steps[i] = sensors_data[41 + i * 2 + 1] * 256 + sensors_data[41 + i * 2]
        print("steps: {0:4d}, {1:4d}\r\n".format(mot_steps[0], mot_steps[1]))
        # print("tv: {0:2d}\r\n".format(sensors_data[45]))
        #
        # print("\r\n")
    else:
        print("wrong checksum ({0:#x} != {0:#x})\r\n".format(sensors_data[ACTUATORS_SIZE - 1], checksum))

    # Communication frequency @ 20 Hz.
    time_diff = time.time() - start
    if time_diff < 0.050:
        time.sleep(0.050 - time_diff);
