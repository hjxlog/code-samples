package com.hjxlog.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Huang JX
 * @date 2022/4/5
 * @description jdk的动态代理类
 */
public class JdkSmsInvocationHandler implements InvocationHandler {
    // InvocationHandler是反射包下的一个接口，只有一个方法invoke

    // 被代理的真实对象
    private final Object target;

    // 和静态代理一样，需要把被代理对象注入
    public JdkSmsInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理开始，代理的方法" + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("jdk动态代理结束，代理的方法" + method.getName());
        return result;
    }
}
