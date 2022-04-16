package com.hjxlog.designPattern;

/**
 * @author: Huang JX
 * @date: 2022/4/16
 * 单例模式
 * <p>
 * 说明：
 * 1. 获取单例模式的方法要用static，属于类，不可能实例化对象再获取这个单例对象吧
 * 2. 对象也是static，属于类
 * 3. volatile保证可见性
 * <p>
 * 步骤：
 * 1. 单例对象、获取单例对象的方法要是static，单例对象保证volatile
 * 2. 如果不为空，锁住当前类，再看此时是否真的不为空，再创建对象返回
 */
public class Singleton {

    /**
     * volatile 保证可见性，因为可能有多个线程同时对这个Singleton进行操作
     * 如果不做这个可见性，可能两个线程同时初始化，就可能初始化多个对象
     * 就是比如A创建了对象，但是还没回写到主内存，B以为还没创建，又创建一个对象
     */
    private volatile static Singleton uniqueInstance;

    public Singleton() {
    }

    public static Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                Singleton uniqueInstance = Singleton.getUniqueInstance();
                System.out.println(Thread.currentThread().getName() + "===>" + uniqueInstance.hashCode());
            }, String.valueOf(i)).start();
        }
    }

}
