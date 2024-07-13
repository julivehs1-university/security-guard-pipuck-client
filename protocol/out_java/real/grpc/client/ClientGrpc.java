package real.grpc.client;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.65.0)",
    comments = "Source: Client.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ClientGrpc {

  private ClientGrpc() {}

  public static final java.lang.String SERVICE_NAME = "real.grpc.client.Client";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<real.grpc.client.Empty,
      real.grpc.client.PingResponse> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Ping",
      requestType = real.grpc.client.Empty.class,
      responseType = real.grpc.client.PingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<real.grpc.client.Empty,
      real.grpc.client.PingResponse> getPingMethod() {
    io.grpc.MethodDescriptor<real.grpc.client.Empty, real.grpc.client.PingResponse> getPingMethod;
    if ((getPingMethod = ClientGrpc.getPingMethod) == null) {
      synchronized (ClientGrpc.class) {
        if ((getPingMethod = ClientGrpc.getPingMethod) == null) {
          ClientGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<real.grpc.client.Empty, real.grpc.client.PingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  real.grpc.client.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  real.grpc.client.PingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ClientMethodDescriptorSupplier("Ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<real.grpc.client.MoveRelativeCommand,
      real.grpc.client.MoveResponse> getMoveToMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MoveTo",
      requestType = real.grpc.client.MoveRelativeCommand.class,
      responseType = real.grpc.client.MoveResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<real.grpc.client.MoveRelativeCommand,
      real.grpc.client.MoveResponse> getMoveToMethod() {
    io.grpc.MethodDescriptor<real.grpc.client.MoveRelativeCommand, real.grpc.client.MoveResponse> getMoveToMethod;
    if ((getMoveToMethod = ClientGrpc.getMoveToMethod) == null) {
      synchronized (ClientGrpc.class) {
        if ((getMoveToMethod = ClientGrpc.getMoveToMethod) == null) {
          ClientGrpc.getMoveToMethod = getMoveToMethod =
              io.grpc.MethodDescriptor.<real.grpc.client.MoveRelativeCommand, real.grpc.client.MoveResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MoveTo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  real.grpc.client.MoveRelativeCommand.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  real.grpc.client.MoveResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ClientMethodDescriptorSupplier("MoveTo"))
              .build();
        }
      }
    }
    return getMoveToMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClientStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientStub>() {
        @java.lang.Override
        public ClientStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientStub(channel, callOptions);
        }
      };
    return ClientStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClientBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientBlockingStub>() {
        @java.lang.Override
        public ClientBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientBlockingStub(channel, callOptions);
        }
      };
    return ClientBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClientFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientFutureStub>() {
        @java.lang.Override
        public ClientFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientFutureStub(channel, callOptions);
        }
      };
    return ClientFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void ping(real.grpc.client.Empty request,
        io.grpc.stub.StreamObserver<real.grpc.client.PingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    default void moveTo(real.grpc.client.MoveRelativeCommand request,
        io.grpc.stub.StreamObserver<real.grpc.client.MoveResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMoveToMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Client.
   */
  public static abstract class ClientImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ClientGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Client.
   */
  public static final class ClientStub
      extends io.grpc.stub.AbstractAsyncStub<ClientStub> {
    private ClientStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientStub(channel, callOptions);
    }

    /**
     */
    public void ping(real.grpc.client.Empty request,
        io.grpc.stub.StreamObserver<real.grpc.client.PingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void moveTo(real.grpc.client.MoveRelativeCommand request,
        io.grpc.stub.StreamObserver<real.grpc.client.MoveResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMoveToMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Client.
   */
  public static final class ClientBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ClientBlockingStub> {
    private ClientBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientBlockingStub(channel, callOptions);
    }

    /**
     */
    public real.grpc.client.PingResponse ping(real.grpc.client.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public real.grpc.client.MoveResponse moveTo(real.grpc.client.MoveRelativeCommand request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMoveToMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Client.
   */
  public static final class ClientFutureStub
      extends io.grpc.stub.AbstractFutureStub<ClientFutureStub> {
    private ClientFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<real.grpc.client.PingResponse> ping(
        real.grpc.client.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<real.grpc.client.MoveResponse> moveTo(
        real.grpc.client.MoveRelativeCommand request) {
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
          serviceImpl.ping((real.grpc.client.Empty) request,
              (io.grpc.stub.StreamObserver<real.grpc.client.PingResponse>) responseObserver);
          break;
        case METHODID_MOVE_TO:
          serviceImpl.moveTo((real.grpc.client.MoveRelativeCommand) request,
              (io.grpc.stub.StreamObserver<real.grpc.client.MoveResponse>) responseObserver);
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
              real.grpc.client.Empty,
              real.grpc.client.PingResponse>(
                service, METHODID_PING)))
        .addMethod(
          getMoveToMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              real.grpc.client.MoveRelativeCommand,
              real.grpc.client.MoveResponse>(
                service, METHODID_MOVE_TO)))
        .build();
  }

  private static abstract class ClientBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClientBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return real.grpc.client.ClientOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Client");
    }
  }

  private static final class ClientFileDescriptorSupplier
      extends ClientBaseDescriptorSupplier {
    ClientFileDescriptorSupplier() {}
  }

  private static final class ClientMethodDescriptorSupplier
      extends ClientBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ClientMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ClientGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClientFileDescriptorSupplier())
              .addMethod(getPingMethod())
              .addMethod(getMoveToMethod())
              .build();
        }
      }
    }
    return result;
  }
}
