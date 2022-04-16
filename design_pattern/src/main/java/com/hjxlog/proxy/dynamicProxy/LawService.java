package com.hjxlog.proxy.dynamicProxy;

/**
 * @author Huang JX
 * @date 2022/4/5
 * @description 动态代理
 * 法律服务接口
 */
public interface LawService {

    /**
     * 起诉
     *
     * @param message
     * @return
     */
    String prosecute(String message);

}
