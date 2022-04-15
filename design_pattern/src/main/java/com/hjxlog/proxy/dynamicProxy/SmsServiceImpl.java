package com.hjxlog.proxy.dynamicProxy;


/**
 * @author Huang JX
 * @date 2022/4/5
 * @description
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("发送短信：" + message);
        return message;
    }
}
