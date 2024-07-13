from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class Empty(_message.Message):
    __slots__ = ()
    def __init__(self) -> None: ...

class PingResponse(_message.Message):
    __slots__ = ("robot_id",)
    ROBOT_ID_FIELD_NUMBER: _ClassVar[int]
    robot_id: int
    def __init__(self, robot_id: _Optional[int] = ...) -> None: ...

class MoveResponse(_message.Message):
    __slots__ = ("success",)
    SUCCESS_FIELD_NUMBER: _ClassVar[int]
    success: bool
    def __init__(self, success: bool = ...) -> None: ...

class MoveRelativeCommand(_message.Message):
    __slots__ = ("x", "y", "theta")
    X_FIELD_NUMBER: _ClassVar[int]
    Y_FIELD_NUMBER: _ClassVar[int]
    THETA_FIELD_NUMBER: _ClassVar[int]
    x: float
    y: float
    theta: float
    def __init__(self, x: _Optional[float] = ..., y: _Optional[float] = ..., theta: _Optional[float] = ...) -> None: ...
