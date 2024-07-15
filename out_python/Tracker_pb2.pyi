from google.protobuf.internal import containers as _containers
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Iterable as _Iterable, Mapping as _Mapping, Optional as _Optional, Union as _Union

DESCRIPTOR: _descriptor.FileDescriptor

class Empty(_message.Message):
    __slots__ = ()
    def __init__(self) -> None: ...

class Robot(_message.Message):
    __slots__ = ("robot_id",)
    ROBOT_ID_FIELD_NUMBER: _ClassVar[int]
    robot_id: int
    def __init__(self, robot_id: _Optional[int] = ...) -> None: ...

class RobotList(_message.Message):
    __slots__ = ("robots",)
    ROBOTS_FIELD_NUMBER: _ClassVar[int]
    robots: _containers.RepeatedCompositeFieldContainer[Robot]
    def __init__(self, robots: _Optional[_Iterable[_Union[Robot, _Mapping]]] = ...) -> None: ...

class Position(_message.Message):
    __slots__ = ("x", "y", "orientation")
    X_FIELD_NUMBER: _ClassVar[int]
    Y_FIELD_NUMBER: _ClassVar[int]
    ORIENTATION_FIELD_NUMBER: _ClassVar[int]
    x: float
    y: float
    orientation: float
    def __init__(self, x: _Optional[float] = ..., y: _Optional[float] = ..., orientation: _Optional[float] = ...) -> None: ...
