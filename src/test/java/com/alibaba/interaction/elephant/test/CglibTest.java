package com.alibaba.interaction.elephant.test;

import com.alibaba.interaction.elephant.test.provider.HelloWorldService;
import com.alibaba.interaction.elephant.test.provider.HelloWorldServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

@Slf4j
public class CglibTest {

    @Test
    public void testProxy() throws IllegalAccessException, InstantiationException {


        Enhancer enhancer = new Enhancer();


        enhancer.setSuperclass(HelloWorldService.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

                log.info("intercept {} {}", method, args);
                return null;
            }
        });


        HelloWorldService helloWorldService = (HelloWorldService) enhancer.create();


        helloWorldService.sayHello("ljinshuan", 200L);
    }
}
