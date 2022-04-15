package com.hjxlog.proxy.cglib;

/**
 * @author Huang JX
 * @date 2022/4/3
 */
public class Demo {
    public static void main(String[] args) {
        AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.send("java");
    }
}
