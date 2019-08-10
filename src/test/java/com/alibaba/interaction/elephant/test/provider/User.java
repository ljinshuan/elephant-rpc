package com.alibaba.interaction.elephant.test.provider;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = -9081376374978731790L;

    private String name;

    private Long age;
}
