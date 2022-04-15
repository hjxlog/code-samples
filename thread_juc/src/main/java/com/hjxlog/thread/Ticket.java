package com.hjxlog.thread;

/**
 * @author Huang JX
 * @date 2022/4/15
 * @description 多线程买票程序
 * 多个线程操作同一个资源的情况下，线程不安全，数据紊乱
 */
public class Ticket implements Runnable {

    // 票数
    private int ticketNum = 10;

    public void run() {
        while (true) {
            if (ticketNum < 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNum-- + "票");
        }
    }

    public static void main(String[] args) {
        /**
         * 存在超卖和重复现象
         * 黄牛拿到了第1票
         * 小黄拿到了第2票
         * 小黄拿到了第0票
         * 小明拿到了第-1票
         * 黄牛拿到了第-2票
         */
        Ticket ticket = new Ticket();
        new Thread(ticket, "小明").start();
        new Thread(ticket, "小黄").start();
        new Thread(ticket, "黄牛").start();
    }
}
