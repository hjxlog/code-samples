package com.hjxlog.proxy.dynamicProxy;

/**
 * @author Huang JX
 * @date 2022/4/5
 * @description
 */
public class JdkProxyDemo {

    public static void main(String[] args) {
        // 获取代理对象
        SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
        // 传入被代理的对象
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(smsServiceImpl);
        // 调用send的时候，被invoke拦截执行
        smsService.send("java");
    }

}
