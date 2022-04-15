package com.hjxlog.proxy.staticProxy;

/**
 * @author Huang JX
 * @date 2022/4/5
 * @description
 */
public class ProxyDemo {
    public static void main(String[] args) {
        /**
         * 静态代理
         */
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("进入谈判...");
    }
}
