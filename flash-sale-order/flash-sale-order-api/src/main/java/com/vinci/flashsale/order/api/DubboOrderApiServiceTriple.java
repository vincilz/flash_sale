/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vinci.flashsale.order.api;

import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.PathResolver;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.ServerService;
import org.apache.dubbo.rpc.TriRpcStatus;
import org.apache.dubbo.rpc.model.MethodDescriptor;
import org.apache.dubbo.rpc.model.ServiceDescriptor;
import org.apache.dubbo.rpc.model.StubMethodDescriptor;
import org.apache.dubbo.rpc.model.StubServiceDescriptor;
import org.apache.dubbo.rpc.service.Destroyable;
import org.apache.dubbo.rpc.stub.BiStreamMethodHandler;
import org.apache.dubbo.rpc.stub.ServerStreamMethodHandler;
import org.apache.dubbo.rpc.stub.StubInvocationUtil;
import org.apache.dubbo.rpc.stub.StubInvoker;
import org.apache.dubbo.rpc.stub.StubMethodHandler;
import org.apache.dubbo.rpc.stub.StubSuppliers;
import org.apache.dubbo.rpc.stub.UnaryStubMethodHandler;

import com.google.protobuf.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.concurrent.CompletableFuture;

public final class DubboOrderApiServiceTriple {

    public static final String SERVICE_NAME = OrderApiService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME, OrderApiService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME, OrderSerivce.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboOrderApiServiceTriple::newStub);
        StubSuppliers.addSupplier(OrderApiService.JAVA_SERVICE_NAME,  DubboOrderApiServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(OrderApiService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("unchecked")
    public static OrderApiService newStub(Invoker<?> invoker) {
        return new OrderApiServiceStub((Invoker<OrderApiService>)invoker);
    }

    private static final StubMethodDescriptor decreaseMethod = new StubMethodDescriptor("Decrease",
    com.vinci.flashsale.order.dto.OrderCreateRequest.class, com.vinci.flashsale.common.dto.CommonResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.vinci.flashsale.order.dto.OrderCreateRequest::parseFrom,
    com.vinci.flashsale.common.dto.CommonResponse::parseFrom);

    private static final StubMethodDescriptor decreaseAsyncMethod = new StubMethodDescriptor("Decrease",
    com.vinci.flashsale.order.dto.OrderCreateRequest.class, CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.vinci.flashsale.order.dto.OrderCreateRequest::parseFrom,
    com.vinci.flashsale.common.dto.CommonResponse::parseFrom);

    private static final StubMethodDescriptor decreaseProxyAsyncMethod = new StubMethodDescriptor("DecreaseAsync",
    com.vinci.flashsale.order.dto.OrderCreateRequest.class, com.vinci.flashsale.common.dto.CommonResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.vinci.flashsale.order.dto.OrderCreateRequest::parseFrom,
    com.vinci.flashsale.common.dto.CommonResponse::parseFrom);

    static{
        serviceDescriptor.addMethod(decreaseMethod);
        serviceDescriptor.addMethod(decreaseProxyAsyncMethod);
    }

    public static class OrderApiServiceStub implements OrderApiService, Destroyable {
        private final Invoker<OrderApiService> invoker;

        public OrderApiServiceStub(Invoker<OrderApiService> invoker) {
            this.invoker = invoker;
        }

        @Override
        public void $destroy() {
              invoker.destroy();
         }

        @Override
        public com.vinci.flashsale.common.dto.CommonResponse decrease(com.vinci.flashsale.order.dto.OrderCreateRequest request){
            return StubInvocationUtil.unaryCall(invoker, decreaseMethod, request);
        }

        public CompletableFuture<com.vinci.flashsale.common.dto.CommonResponse> decreaseAsync(com.vinci.flashsale.order.dto.OrderCreateRequest request){
            return StubInvocationUtil.unaryCall(invoker, decreaseAsyncMethod, request);
        }

        public void decrease(com.vinci.flashsale.order.dto.OrderCreateRequest request, StreamObserver<com.vinci.flashsale.common.dto.CommonResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, decreaseMethod , request, responseObserver);
        }
    }

    public static abstract class OrderApiServiceImplBase implements OrderApiService, ServerService<OrderApiService> {
        private <T, R> BiConsumer<T, StreamObserver<R>> syncToAsync(java.util.function.Function<T, R> syncFun) {
            return new BiConsumer<T, StreamObserver<R>>() {
                @Override
                public void accept(T t, StreamObserver<R> observer) {
                    try {
                        R ret = syncFun.apply(t);
                        observer.onNext(ret);
                        observer.onCompleted();
                    } catch (Throwable e) {
                        observer.onError(e);
                    }
                }
            };
        }

        @Override
        public CompletableFuture<com.vinci.flashsale.common.dto.CommonResponse> decreaseAsync(com.vinci.flashsale.order.dto.OrderCreateRequest request){
                return CompletableFuture.completedFuture(decrease(request));
        }

        // This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
        // It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.

        public void decrease(com.vinci.flashsale.order.dto.OrderCreateRequest request, StreamObserver<com.vinci.flashsale.common.dto.CommonResponse> responseObserver){
            decreaseAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }

        @Override
        public final Invoker<OrderApiService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String, StubMethodHandler<?, ?>> handlers = new HashMap<>();
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/Decrease");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/DecreaseAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/Decrease");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/DecreaseAsync");
            BiConsumer<com.vinci.flashsale.order.dto.OrderCreateRequest, StreamObserver<com.vinci.flashsale.common.dto.CommonResponse>> decreaseFunc = this::decrease;
            handlers.put(decreaseMethod.getMethodName(), new UnaryStubMethodHandler<>(decreaseFunc));
            BiConsumer<com.vinci.flashsale.order.dto.OrderCreateRequest, StreamObserver<com.vinci.flashsale.common.dto.CommonResponse>> decreaseAsyncFunc = syncToAsync(this::decrease);
            handlers.put(decreaseProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(decreaseAsyncFunc));

            return new StubInvoker<>(this, url, OrderApiService.class, handlers);
        }

        @Override
        public com.vinci.flashsale.common.dto.CommonResponse decrease(com.vinci.flashsale.order.dto.OrderCreateRequest request){
            throw unimplementedMethodException(decreaseMethod);
        }

        @Override
        public final ServiceDescriptor getServiceDescriptor() {
            return serviceDescriptor;
        }
        private RpcException unimplementedMethodException(StubMethodDescriptor methodDescriptor) {
            return TriRpcStatus.UNIMPLEMENTED.withDescription(String.format("Method %s is unimplemented",
                "/" + serviceDescriptor.getInterfaceName() + "/" + methodDescriptor.getMethodName())).asException();
        }
    }
}
