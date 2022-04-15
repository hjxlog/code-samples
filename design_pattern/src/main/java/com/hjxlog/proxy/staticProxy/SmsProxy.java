package com.hjxlog.proxy.staticProxy;

/**
 * @author Huang JX
 * @date 2022/4/5
 * @description
 */
public class SmsProxy implements SmsService{

    private SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public String send(String message) {
        System.out.println("进入静态代理类...");
        smsService.send(message);
        System.out.println("静态代理类结束...");
        return message;
    }
}
