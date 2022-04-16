package com.hjxlog.proxy.dynamicProxy;


/**
 * @author Huang JX
 * @date 2022/4/5
 * @description
 */
public class LawServiceImpl implements LawService {
    @Override
    public String prosecute(String message) {
        System.out.println("起诉请求：" + message);
        return message;
    }
}
