package com.alibaba.interaction.elephant.rpc.consumer;

import com.alibaba.interaction.elephant.rpc.client.ElephantClient;
import com.alibaba.interaction.elephant.rpc.meta.ParamMeta;
import com.alibaba.interaction.elephant.rpc.meta.RpcRequestMeta;
import com.alibaba.interaction.elephant.rpc.meta.RpcResult;
import com.alibaba.interaction.elephant.utils.SerializeUtils;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsumerFactory {


    /**
     * @param clazz
     * @param serviceVersion
     * @param elephantClient
     * @param <T>
     * @return
     */
    public static <T> T createConsumer(Class<T> clazz, String serviceVersion, ElephantClient elephantClient) {


        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(clazz);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {

            RpcRequestMeta rpcRequestMeta = buildInvokeRequest(clazz.getName(), serviceVersion, method, args);
            RpcResult rpcResult = (RpcResult) elephantClient.invoke(rpcRequestMeta);


            Object data = rpcResult.getData();


            return SerializeUtils.deserializeParam(data, method.getReturnType().getName());

        });

        return (T) enhancer.create();
    }


    private static RpcRequestMeta buildInvokeRequest(String serviceName, String serviceVersion, Method method, Object[] args) {

        RpcRequestMeta serviceRequestMeta = new RpcRequestMeta();
        serviceRequestMeta.setRequestId(UUID.randomUUID().toString());
        serviceRequestMeta.setServiceName(serviceName);
        serviceRequestMeta.setSessionId(UUID.randomUUID().toString());
        serviceRequestMeta.setTimeoutMs(3000);
        serviceRequestMeta.setVersion(serviceVersion);
        serviceRequestMeta.setMethodName(method.getName());


        Class<?>[] parameterTypes = method.getParameterTypes();


        List<ParamMeta> paramMetas = IntStream.range(0, parameterTypes.length).mapToObj(i -> {

            Class<?> parameterType = parameterTypes[i];
            Object arg = args[i];
            return ParamMeta.of(arg, parameterType);
        }).collect(Collectors.toList());

        serviceRequestMeta.setParamMetas(paramMetas);

        return serviceRequestMeta;
    }
}
