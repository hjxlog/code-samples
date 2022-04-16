package com.hjxlog.thread.state;

/**
 * @author Huang JX
 * @date 2022/4/15
 * @description 模拟网络延迟，放大问题的发生性
 */
public class TestSleep implements Runnable {
    // 票数
    private int ticketNum = 10;

    public void run() {
        while (true) {
            if (ticketNum <= 0) {
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
        TestSleep ticket = new TestSleep();
        new Thread(ticket, "小明").start();
        new Thread(ticket, "小黄").start();
        new Thread(ticket, "黄牛").start();
    }
}
