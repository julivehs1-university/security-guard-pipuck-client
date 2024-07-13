package real.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.65.0)",
    comments = "Source: Client.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TrackerGrpc {

  private TrackerGrpc() {}

  public static final java.lang.String SERVICE_NAME = "real.grpc.Tracker";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<real.grpc.Empty,
      real.grpc.PingResponse> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Ping",
      requestType = real.grpc.Empty.class,
      responseType = real.grpc.PingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<real.grpc.Empty,
      real.grpc.PingResponse> getPingMethod() {
    io.grpc.MethodDescriptor<real.grpc.Empty, real.grpc.PingResponse> getPingMethod;
    if ((getPingMethod = TrackerGrpc.getPingMethod) == null) {
      synchronized (TrackerGrpc.class) {
        if ((getPingMethod = TrackerGrpc.getPingMethod) == null) {
          TrackerGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<real.grpc.Empty, real.grpc.PingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  real.grpc.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  real.grpc.PingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TrackerMethodDescriptorSupplier("Ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<real.grpc.MoveRelativeCommand,
      real.grpc.MoveResponse> getMoveToMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MoveTo",
      requestType = real.grpc.MoveRelativeCommand.class,
      responseType = real.grpc.MoveResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<real.grpc.MoveRelativeCommand,
      real.grpc.MoveResponse> getMoveToMethod() {
    io.grpc.MethodDescriptor<real.grpc.MoveRelativeCommand, real.grpc.MoveResponse> getMoveToMethod;
    if ((getMoveToMethod = TrackerGrpc.getMoveToMethod) == null) {
      synchronized (TrackerGrpc.class) {
        if ((getMoveToMethod = TrackerGrpc.getMoveToMethod) == null) {
          TrackerGrpc.getMoveToMethod = getMoveToMethod =
              io.grpc.MethodDescriptor.<real.grpc.MoveRelativeCommand, real.grpc.MoveResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MoveTo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  real.grpc.MoveRelativeCommand.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  real.grpc.MoveResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TrackerMethodDescriptorSupplier("MoveTo"))
              .build();
        }
      }
    }
    return getMoveToMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TrackerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrackerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrackerStub>() {
        @java.lang.Override
        public TrackerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrackerStub(channel, callOptions);
        }
      };
    return TrackerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TrackerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrackerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrackerBlockingStub>() {
        @java.lang.Override
        public TrackerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrackerBlockingStub(channel, callOptions);
        }
      };
    return TrackerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TrackerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrackerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrackerFutureStub>() {
        @java.lang.Override
        public TrackerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrackerFutureStub(channel, callOptions);
        }
      };
    return TrackerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void ping(real.grpc.Empty request,
        io.grpc.stub.StreamObserver<real.grpc.PingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    default void moveTo(real.grpc.MoveRelativeCommand request,
        io.grpc.stub.StreamObserver<real.grpc.MoveResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMoveToMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Tracker.
   */
  public static abstract class TrackerImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return TrackerGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Tracker.
   */
  public static final class TrackerStub
      extends io.grpc.stub.AbstractAsyncStub<TrackerStub> {
    private TrackerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrackerStub(channel, callOptions);
    }

    /**
     */
    public void ping(real.grpc.Empty request,
        io.grpc.stub.StreamObserver<real.grpc.PingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void moveTo(real.grpc.MoveRelativeCommand request,
        io.grpc.stub.StreamObserver<real.grpc.MoveResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMoveToMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Tracker.
   */
  public static final class TrackerBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TrackerBlockingStub> {
    private TrackerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrackerBlockingStub(channel, callOptions);
    }

    /**
     */
    public real.grpc.PingResponse ping(real.grpc.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public real.grpc.MoveResponse moveTo(real.grpc.MoveRelativeCommand request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMoveToMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Tracker.
   */
  public static final class TrackerFutureStub
      extends io.grpc.stub.AbstractFutureStub<TrackerFutureStub> {
    private TrackerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrackerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<real.grpc.PingResponse> ping(
        real.grpc.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<real.grpc.MoveResponse> moveTo(
        real.grpc.MoveRelativeCommand request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMoveToMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PING = 0;
  private static final int METHODID_MOVE_TO = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PING:
          serviceImpl.ping((real.grpc.Empty) request,
              (io.grpc.stub.StreamObserver<real.grpc.PingResponse>) responseObserver);
          break;
        case METHODID_MOVE_TO:
          serviceImpl.moveTo((real.grpc.MoveRelativeCommand) request,
              (io.grpc.stub.StreamObserver<real.grpc.MoveResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getPingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              real.grpc.Empty,
              real.grpc.PingResponse>(
                service, METHODID_PING)))
        .addMethod(
          getMoveToMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              real.grpc.MoveRelativeCommand,
              real.grpc.MoveResponse>(
                service, METHODID_MOVE_TO)))
        .build();
  }

  private static abstract class TrackerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TrackerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return real.grpc.Client.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Tracker");
    }
  }

  private static final class TrackerFileDescriptorSupplier
      extends TrackerBaseDescriptorSupplier {
    TrackerFileDescriptorSupplier() {}
  }

  private static final class TrackerMethodDescriptorSupplier
      extends TrackerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    TrackerMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TrackerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TrackerFileDescriptorSupplier())
              .addMethod(getPingMethod())
              .addMethod(getMoveToMethod())
              .build();
        }
      }
    }
    return result;
  }
}
