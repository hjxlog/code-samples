package com.hjxlog.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author Huang JX
 * @date 2022/4/16
 * @description
 */
public class Test01 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            phone.call();
        }, "B").start();
    }


}

class Phone {
    //synchronized 锁的对象是方法的调用者！
    public synchronized void sendSms() {
        try {
            // sleep() 没有释放锁，同一把锁
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("发信息");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }

    // 这里没有锁
    public void Hello() {
        System.out.println("hello");
    }

}