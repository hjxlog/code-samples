package com.hjxlog.juc.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Huang JX
 * @date 2022/4/16
 * @description
 */
public class SaleTicketDemo02 {

    public static void main(String[] args) {
        Ticket2 ticket2 = new Ticket2();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                ticket2.sale();
            }
        },"A").start();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                ticket2.sale();
            }
        },"B").start();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                ticket2.sale();
            }
        },"C").start();
    }
}

class Ticket2 {
    private int num = 30;

    /**
     * public ReentrantLock() {
     * sync = new NonfairSync();
     * }
     * 默认是非公平锁，也就是抢占式的
     *
     */
    Lock lock = new ReentrantLock();

    /**
     * 1、Synchronized 内置的Java关键字，Lock是一个接口
     * 2、Synchronized无法判断获取锁的状态，Lock 可以判断是否获取到了锁
     * 3、Synchronized 会自动释放锁，lock 必须要手动释放锁!如果不释放锁，死锁
     * 4、Synchronized线程1(获得锁，阻塞)、线程2(等待，傻傻的等） ; Lock锁就不一定会等待下去;
     * 5、Synchronized 可重入锁，不可以中断的，非公平;Lock，可重入锁，可判断锁，默认非公平(可以自己设置）;
     * 6、Synchronized适合锁少量的代码同步问题，Lock适合锁大量的同步代码!
     */

    public void sale() {
        // 快捷键 ctrl+alt+t
        try {
            lock.lock();
//            lock.tryLock(); // 尝试获取锁
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (num--) + "票，剩余：" + num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /**
             * lock必须要手动释放锁，否则会死锁
             * synchronized 自动释放锁
             */
            lock.unlock();
        }
    }
}