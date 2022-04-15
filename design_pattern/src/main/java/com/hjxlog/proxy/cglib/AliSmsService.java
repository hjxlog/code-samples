package com.hjxlog.proxy.cglib;

/**
 * @author Huang JX
 * @date 2022/4/3
 * 实现一个使用阿里云发送短信的类
 */
public class AliSmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}

