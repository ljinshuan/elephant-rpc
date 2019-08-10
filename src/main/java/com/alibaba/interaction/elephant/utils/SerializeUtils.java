package com.alibaba.interaction.elephant.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

public class SerializeUtils {


    /**
     * @param data
     * @param typeName
     * @return
     */
    public static Object deserializeParam(Object data, String typeName) {

        if (data == null) {
            return null;
        }

        if (StringUtils.equalsIgnoreCase(typeName, "java.lang.Void")) {
            return null;
        }

        switch (typeName) {

            case "java.lang.Boolean": {
                return Boolean.valueOf(data.toString());
            }
            case "java.lang.Character": {
                return data.toString().charAt(0);
            }
            case "java.lang.Byte": {
                return Byte.valueOf(data.toString());
            }
            case "java.lang.Short": {
                return Short.valueOf(data.toString());
            }
            case "java.lang.Integer": {
                return Integer.valueOf(data.toString());
            }
            case "java.lang.Long": {
                return Long.valueOf(data.toString());
            }
            case "java.lang.Float": {
                return Float.valueOf(data.toString());
            }
            case "java.lang.Double": {
                return Double.valueOf(data.toString());
            }
            case "java.lang.String": {
                return data.toString();
            }

        }
        Class<?> clazz;
        try {
            clazz = Class.forName(typeName);
        } catch (ClassNotFoundException e) {

            throw new IllegalArgumentException(e);
        }

        Object object = JSON.toJavaObject((JSON) data, clazz);

        return object;
    }
}
