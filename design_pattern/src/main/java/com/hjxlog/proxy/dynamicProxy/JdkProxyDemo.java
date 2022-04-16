package com.hjxlog.proxy.dynamicProxy;

/**
 * @author Huang JX
 * @date 2022/4/5
 * 场景：法律诉讼
 * 客户找到律师，需要律师提供法律服务，帮助诉讼；
 * 此时，客户就是被代理对象，律师是代理对象
 * 先有一个客户，也就是 smsServiceImpl
 * 再通过 Proxy.newProxyInstance 获得律师对象（找到代理律师）
 * 这时候，由律师提供服务，当jvm调用方法的时候，实际上走的是 invocationHandler 的invoke()方法
 *
 * JDK动态代理特点：
 * 1. 需要共同继承接口
 */
public class JdkProxyDemo {

    public static void main(String[] args) {
        // 获取代理对象
        LawServiceImpl smsServiceImpl = new LawServiceImpl();
        // 传入被代理的对象
        LawService lawService = (LawService) JdkProxyFactory.getProxy(smsServiceImpl);
        // 调用send的时候，被invoke拦截执行
        lawService.prosecute("java");
    }

}
