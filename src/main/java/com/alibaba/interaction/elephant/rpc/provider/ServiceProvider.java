package com.alibaba.interaction.elephant.rpc.provider;

import com.alibaba.interaction.elephant.rpc.RpcContext;
import com.alibaba.interaction.elephant.rpc.meta.ParamMeta;
import com.alibaba.interaction.elephant.rpc.meta.RpcRequestMeta;
import com.alibaba.interaction.elephant.utils.SerializeUtils;
import com.alibaba.interaction.elephant.utils.StreamUtils;
import com.alibaba.interaction.elephant.utils.ThreadUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Slf4j
public class ServiceProvider {


    public static ExecutorService RPC_PROVIDER_EXECUTOR = ThreadUtils.createExecutorService(30, "rpc-provider");


    private static Map<String, ProviderMeta> providerMetas = Maps.newHashMap();


    /**
     * 注册服务提供者
     *
     * @param serviceName
     * @param serviceVersion
     * @param targetObj
     * @param clazz
     * @return
     */
    public static boolean registerProvider(String serviceName, String serviceVersion, Object targetObj, Class clazz) {

        ProviderMeta providerMeta = new ProviderMeta();
        providerMeta.setServiceClass(clazz);
        providerMeta.setServiceName(serviceName);
        providerMeta.setServiceVersion(serviceVersion);
        providerMeta.setTarget(targetObj);

        Map<String, Method> methodMap = Maps.newHashMap();
        providerMeta.setMethods(methodMap);
        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {

            methodMap.put(ProviderMeta.method2SimpleString(declaredMethod), declaredMethod);
        }
        providerMetas.put(String.format("%s:%s", serviceName, serviceVersion), providerMeta);

        return true;


    }


    /**
     * @param rpcContext
     * @param requestMeta
     * @return
     */
    public static Object invoke(RpcContext rpcContext, RpcRequestMeta requestMeta) throws Throwable {


        //找服务
        ProviderMeta providerMeta = findProviderMeta(rpcContext, requestMeta);
        List<ParamMeta> paramMetas = requestMeta.getParamMetas();

        List<String> collect = StreamUtils.ofNullable(paramMetas).map(d -> d.getType()).collect(Collectors.toList());

        //找方法
        Method invokeMethod = providerMeta.findMethod(requestMeta.getMethodName(), collect);

        Object[] args = getRequestArgs(requestMeta);
        //调用
        Object invoke = invokeMethod.invoke(providerMeta.getTarget(), args);
        return invoke;

    }

    /**
     * @param requestMeta
     * @return
     */
    private static Object[] getRequestArgs(RpcRequestMeta requestMeta) {


        //反序列化参数
        return StreamUtils.ofNullable(requestMeta.getParamMetas()).map(d -> {

            Object data = d.getData();
            String type = d.getType();
            return SerializeUtils.deserializeParam(data, type);


        }).toArray();

    }


    /**
     * @param rpcContext
     * @param requestMeta
     * @return
     */
    private static ProviderMeta findProviderMeta(RpcContext rpcContext, RpcRequestMeta requestMeta) {

        String uniqueServiceName = String.format("%s:%s", requestMeta.getServiceName(), requestMeta.getVersion());
        ProviderMeta providerMeta = providerMetas.get(uniqueServiceName);

        if (providerMeta == null) {
            throw new IllegalArgumentException("can't find service " + uniqueServiceName);
        }

        return providerMeta;
    }
}
