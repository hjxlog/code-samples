package com.hjxlog.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Huang JX
 * @date 2022/4/16
 * @description
 */
public class ListTest {

    public static void main(String[] args) {
        /**
         * java.util.ConcurrentModificationException 并发修改异常
         */
//        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        // private transient volatile Object[] array;
        /**
         * public boolean add(E e) {
         *         final ReentrantLock lock = this.lock;
         *         lock.lock();
         *         try {
         *             Object[] elements = getArray();
         *             int len = elements.length;
         *             Object[] newElements = Arrays.copyOf(elements, len + 1);
         *             newElements[len] = e;
         *             setArray(newElements);
         *             return true;
         *         } finally {
         *             lock.unlock();
         *         }
         *     }
         *
         *     使用了 ReentrantLock
         */
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

}
