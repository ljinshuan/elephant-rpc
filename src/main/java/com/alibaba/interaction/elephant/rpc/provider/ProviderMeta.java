package com.alibaba.interaction.elephant.rpc.provider;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class ProviderMeta implements Serializable {
    private static final long serialVersionUID = -4044871972482107597L;


    private Object target;


    private Map<String, Method> methods;

    private Class serviceClass;


    private String serviceName;

    private String serviceVersion;

    /**
     * @param methodName
     * @param parameterTypes
     * @return
     */
    public Method findMethod(String methodName, List<String> parameterTypes) {

        String methodSign = method2SimpleString(methodName, parameterTypes);
        Method method = methods.get(methodSign);

        if (method == null) {

            throw new IllegalArgumentException("can't find method " + methodSign + " in " + serviceClass.getName());
        }

        return method;
    }

    public static String method2SimpleString(String methodName, List<String> parameterTypes) {


        if (parameterTypes == null) {
            parameterTypes = Lists.newArrayList();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(methodName);

        String collect = parameterTypes.stream().collect(Collectors.joining(","));

        sb.append("(").append(collect).append(")");

        return sb.toString();

    }

    public static String method2SimpleString(Method method) {


        String name = method.getName();

        StringBuilder sb = new StringBuilder();
        sb.append(name);

        String collect = Arrays.stream(method.getParameterTypes()).map(d -> d.getName()).collect(Collectors.joining(","));

        sb.append("(").append(collect).append(")");

        return sb.toString();

    }
}
