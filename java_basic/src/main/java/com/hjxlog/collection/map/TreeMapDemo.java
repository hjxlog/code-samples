package com.hjxlog.collection.map;

import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Huang JX
 * @date: 2022/4/16
 * TreeMap
 * 备注：
 * 1. TreeMap<K,V> extends AbstractMap<K,V> implements NavigableMap<K,V>
 * 2. 红黑树实现，排序的
 */
public class TreeMapDemo {

    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap();
        treeMap.put(1, "hi");
        treeMap.put(3, "aa");
        treeMap.put(2, "bb");
        treeMap.put(-1, "dd");
        System.out.println(treeMap);
    }

}
