package com.hjxlog.lock;

/**
 * @author: Huang JX
 * @date: 2022/4/16
 * <p>
 * synchronized三种用法
 * 1. 修饰实例方法：public synchronized void test(){}
 * 2. 修饰静态方法：public static synchronized void fun(){}
 * 3. 修饰代码块：synchronized (object) {}
 *
 * 注意点：
 * 1. 尽量不要使用 synchronized(String a) 因为 JVM 中，字符串常量池具有缓存功能！
 */
public class SyncDemo {

//    private synchronized static int num = 0;

    private static Object object = new Object();

    public static void main(String[] args) {
        synchronized (object) {
            System.out.println("=====");
        }
    }

    // 锁住当前类
    public static synchronized void fun() {

    }

    // 锁会锁住对象实例
    public synchronized void test() {
        System.out.println("=====");
    }

}
