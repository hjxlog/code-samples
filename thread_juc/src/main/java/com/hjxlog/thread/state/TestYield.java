package com.hjxlog.thread.state;

/**
 * @author Huang JX
 * @date 2022/4/15
 * @description 礼让线程，不一定成功，看CPU心情
 */
public class TestYield {

    public static void main(String[] args) {
        MyYield myYield = new MyYield();

        /**
         * b线程开始执行
         * a线程开始执行
         * b线程停止执行
         * a线程停止执行
         */
        new Thread(myYield, "a").start();
        new Thread(myYield, "b").start();
    }

}

class MyYield implements Runnable {

    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        Thread.yield(); // 线程礼让
        System.out.println(Thread.currentThread().getName() + "线程停止执行");
    }
}