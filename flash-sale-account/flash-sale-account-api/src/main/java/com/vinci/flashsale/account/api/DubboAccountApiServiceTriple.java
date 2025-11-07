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

package com.vinci.flashsale.account.api;

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

public final class DubboAccountApiServiceTriple {

    public static final String SERVICE_NAME = AccountApiService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME, AccountApiService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME, AccountService.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboAccountApiServiceTriple::newStub);
        StubSuppliers.addSupplier(AccountApiService.JAVA_SERVICE_NAME,  DubboAccountApiServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(AccountApiService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("unchecked")
    public static AccountApiService newStub(Invoker<?> invoker) {
        return new AccountApiServiceStub((Invoker<AccountApiService>)invoker);
    }

    private static final StubMethodDescriptor reduceMethod = new StubMethodDescriptor("Reduce",
    com.vinci.flashsale.account.dto.AccountReduceRequest.class, com.vinci.flashsale.common.dto.CommonResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.vinci.flashsale.account.dto.AccountReduceRequest::parseFrom,
    com.vinci.flashsale.common.dto.CommonResponse::parseFrom);

    private static final StubMethodDescriptor reduceAsyncMethod = new StubMethodDescriptor("Reduce",
    com.vinci.flashsale.account.dto.AccountReduceRequest.class, CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.vinci.flashsale.account.dto.AccountReduceRequest::parseFrom,
    com.vinci.flashsale.common.dto.CommonResponse::parseFrom);

    private static final StubMethodDescriptor reduceProxyAsyncMethod = new StubMethodDescriptor("ReduceAsync",
    com.vinci.flashsale.account.dto.AccountReduceRequest.class, com.vinci.flashsale.common.dto.CommonResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.vinci.flashsale.account.dto.AccountReduceRequest::parseFrom,
    com.vinci.flashsale.common.dto.CommonResponse::parseFrom);

    private static final StubMethodDescriptor compensateReduceMethod = new StubMethodDescriptor("CompensateReduce",
    com.vinci.flashsale.account.dto.AccountReduceRequest.class, com.vinci.flashsale.common.dto.CommonResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.vinci.flashsale.account.dto.AccountReduceRequest::parseFrom,
    com.vinci.flashsale.common.dto.CommonResponse::parseFrom);

    private static final StubMethodDescriptor compensateReduceAsyncMethod = new StubMethodDescriptor("CompensateReduce",
    com.vinci.flashsale.account.dto.AccountReduceRequest.class, CompletableFuture.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.vinci.flashsale.account.dto.AccountReduceRequest::parseFrom,
    com.vinci.flashsale.common.dto.CommonResponse::parseFrom);

    private static final StubMethodDescriptor compensateReduceProxyAsyncMethod = new StubMethodDescriptor("CompensateReduceAsync",
    com.vinci.flashsale.account.dto.AccountReduceRequest.class, com.vinci.flashsale.common.dto.CommonResponse.class, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.vinci.flashsale.account.dto.AccountReduceRequest::parseFrom,
    com.vinci.flashsale.common.dto.CommonResponse::parseFrom);

    static{
        serviceDescriptor.addMethod(reduceMethod);
        serviceDescriptor.addMethod(reduceProxyAsyncMethod);
        serviceDescriptor.addMethod(compensateReduceMethod);
        serviceDescriptor.addMethod(compensateReduceProxyAsyncMethod);
    }

    public static class AccountApiServiceStub implements AccountApiService, Destroyable {
        private final Invoker<AccountApiService> invoker;

        public AccountApiServiceStub(Invoker<AccountApiService> invoker) {
            this.invoker = invoker;
        }

        @Override
        public void $destroy() {
              invoker.destroy();
         }

        @Override
        public com.vinci.flashsale.common.dto.CommonResponse reduce(com.vinci.flashsale.account.dto.AccountReduceRequest request){
            return StubInvocationUtil.unaryCall(invoker, reduceMethod, request);
        }

        public CompletableFuture<com.vinci.flashsale.common.dto.CommonResponse> reduceAsync(com.vinci.flashsale.account.dto.AccountReduceRequest request){
            return StubInvocationUtil.unaryCall(invoker, reduceAsyncMethod, request);
        }

        public void reduce(com.vinci.flashsale.account.dto.AccountReduceRequest request, StreamObserver<com.vinci.flashsale.common.dto.CommonResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, reduceMethod , request, responseObserver);
        }

        @Override
        public com.vinci.flashsale.common.dto.CommonResponse compensateReduce(com.vinci.flashsale.account.dto.AccountReduceRequest request){
            return StubInvocationUtil.unaryCall(invoker, compensateReduceMethod, request);
        }

        public CompletableFuture<com.vinci.flashsale.common.dto.CommonResponse> compensateReduceAsync(com.vinci.flashsale.account.dto.AccountReduceRequest request){
            return StubInvocationUtil.unaryCall(invoker, compensateReduceAsyncMethod, request);
        }

        public void compensateReduce(com.vinci.flashsale.account.dto.AccountReduceRequest request, StreamObserver<com.vinci.flashsale.common.dto.CommonResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, compensateReduceMethod , request, responseObserver);
        }
    }

    public static abstract class AccountApiServiceImplBase implements AccountApiService, ServerService<AccountApiService> {
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
        public CompletableFuture<com.vinci.flashsale.common.dto.CommonResponse> reduceAsync(com.vinci.flashsale.account.dto.AccountReduceRequest request){
                return CompletableFuture.completedFuture(reduce(request));
        }

        @Override
        public CompletableFuture<com.vinci.flashsale.common.dto.CommonResponse> compensateReduceAsync(com.vinci.flashsale.account.dto.AccountReduceRequest request){
                return CompletableFuture.completedFuture(compensateReduce(request));
        }

        // This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
        // It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.

        public void reduce(com.vinci.flashsale.account.dto.AccountReduceRequest request, StreamObserver<com.vinci.flashsale.common.dto.CommonResponse> responseObserver){
            reduceAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }

        public void compensateReduce(com.vinci.flashsale.account.dto.AccountReduceRequest request, StreamObserver<com.vinci.flashsale.common.dto.CommonResponse> responseObserver){
            compensateReduceAsync(request).whenComplete((r, t) -> {
                if (t != null) {
                    responseObserver.onError(t);
                } else {
                    responseObserver.onNext(r);
                    responseObserver.onCompleted();
                }
            });
        }

        @Override
        public final Invoker<AccountApiService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String, StubMethodHandler<?, ?>> handlers = new HashMap<>();
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/Reduce");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/ReduceAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/Reduce");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/ReduceAsync");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/CompensateReduce");
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/CompensateReduceAsync");
            // for compatibility
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/CompensateReduce");
            pathResolver.addNativeStub( "/" + JAVA_SERVICE_NAME + "/CompensateReduceAsync");
            BiConsumer<com.vinci.flashsale.account.dto.AccountReduceRequest, StreamObserver<com.vinci.flashsale.common.dto.CommonResponse>> reduceFunc = this::reduce;
            handlers.put(reduceMethod.getMethodName(), new UnaryStubMethodHandler<>(reduceFunc));
            BiConsumer<com.vinci.flashsale.account.dto.AccountReduceRequest, StreamObserver<com.vinci.flashsale.common.dto.CommonResponse>> reduceAsyncFunc = syncToAsync(this::reduce);
            handlers.put(reduceProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(reduceAsyncFunc));
            BiConsumer<com.vinci.flashsale.account.dto.AccountReduceRequest, StreamObserver<com.vinci.flashsale.common.dto.CommonResponse>> compensateReduceFunc = this::compensateReduce;
            handlers.put(compensateReduceMethod.getMethodName(), new UnaryStubMethodHandler<>(compensateReduceFunc));
            BiConsumer<com.vinci.flashsale.account.dto.AccountReduceRequest, StreamObserver<com.vinci.flashsale.common.dto.CommonResponse>> compensateReduceAsyncFunc = syncToAsync(this::compensateReduce);
            handlers.put(compensateReduceProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(compensateReduceAsyncFunc));

            return new StubInvoker<>(this, url, AccountApiService.class, handlers);
        }

        @Override
        public com.vinci.flashsale.common.dto.CommonResponse reduce(com.vinci.flashsale.account.dto.AccountReduceRequest request){
            throw unimplementedMethodException(reduceMethod);
        }

        @Override
        public com.vinci.flashsale.common.dto.CommonResponse compensateReduce(com.vinci.flashsale.account.dto.AccountReduceRequest request){
            throw unimplementedMethodException(compensateReduceMethod);
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
