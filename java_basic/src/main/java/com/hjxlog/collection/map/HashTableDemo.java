package com.hjxlog.collection.map;

import java.util.Hashtable;

/**
 * @author: Huang JX
 * @date: 2022/4/16
 * Hashtable demo
 *
 * 备注：
 * 1. Hashtable<K,V> implements Map<K,V> key-value的形式
 * 2. key和value都不能为空，
 * 3. 线程安全的，public synchronized V put(K key, V value)
 */
public class HashTableDemo {

    public static void main(String[] args) {
        Hashtable<String,String> hashtable = new Hashtable();
        hashtable.put("1","hi");
        hashtable.put("2","你好");
        System.out.println(hashtable);
    }

}
