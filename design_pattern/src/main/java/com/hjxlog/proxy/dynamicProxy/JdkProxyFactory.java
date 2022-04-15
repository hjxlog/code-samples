package com.hjxlog.proxy.dynamicProxy;

import java.lang.reflect.Proxy;

/**
 * @author Huang JX
 * @date 2022/4/5
 * @description 获取代理对象的工厂类
 */
public class JdkProxyFactory {

    public static Object getProxy(Object target) {
        JdkSmsInvocationHandler invocationHandler = new JdkSmsInvocationHandler(target);
        // 参数：被代理对象的classloader，需要代理的接口，代理的处理类
        // 你要代理谁？代理谁的什么方法？要怎么代理？
        Object instance = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                invocationHandler);
        return instance;
    }

}
