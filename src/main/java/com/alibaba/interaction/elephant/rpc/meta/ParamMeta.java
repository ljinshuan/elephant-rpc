package com.alibaba.interaction.elephant.rpc.meta;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParamMeta implements Serializable {

    private static final long serialVersionUID = 5545954685722278950L;

    /**
     * 值
     */
    private Object data;

    /**
     * 类型
     */
    private String type;


    public static ParamMeta of(Object data, Class type) {

        ParamMeta meta = new ParamMeta();
        meta.setData(data);
        meta.setType(type.getName());

        return meta;
    }
}
