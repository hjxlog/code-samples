package com.hjxlog.collection.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Huang JX
 * @date: 2022/4/16
 * <p>
 * 如何选用集合?
 * <p>
 * 需要键值对，选用Map接口下的集合：
 * 1. 需要排序时选择 TreeMap
 * 2. 不需要排序时就选择 HashMap
 * 3. 需要保证线程安全就选用 ConcurrentHashMap
 * <p>
 * 只需要存放元素值时，就选择实现Collection 接口的集合
 * 1. 需要保证元素唯一时选择实现 Set 接口的集合，需要排序TreeSet，不需要排序HashSet
 * 2. 不需要就选择实现 List 接口的比如 ArrayList 或 LinkedList，然后再根据实现这些接口的集合的特点来选用。
 */
public class MapDemo {

    public static void main(String[] args) {
        Map map = new HashMap<>();
    }

}
