package com.hjxlog.collection.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Huang JX
 * @date: 2022/4/16
 * <p>
 * 备注：
 * 1. ConcurrentHashMap<K,V> extends AbstractMap<K,V> key-value形式
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap();
        map.put(1, "hi");
        map.put(2, "bb");
        System.out.println(map);
    }

}
