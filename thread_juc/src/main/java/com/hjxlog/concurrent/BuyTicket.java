package com.hjxlog.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Huang JX
 * @date 2022/4/16
 * @description
 */
public class BuyTicket implements Runnable {
    private int num = 200;

    private final ReentrantLock lock = new ReentrantLock();

    public void run() {
        while (true) {
            try {
                lock.lock();
//                System.out.println(lock.hashCode());
                if (num > 0) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "====>" + num--);
                    /**
                     * 线程的让步，就是 原来执行的这个线程从执行状态到 就绪状态
                     * 但是还是有可能被紧接着执行到的，不保证一定会接着执行的是别的线程
                     */
                    Thread.yield();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket, "小明").start();
        new Thread(buyTicket, "小黄").start();
        new Thread(buyTicket, "黄牛").start();
    }
}
