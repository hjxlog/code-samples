package com.hjxlog.thread.state;

/**
 * @author Huang JX
 * @date 2022/4/15
 * @description 测试join方法，想象成插队
 */
public class TestJoin implements Runnable {
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            System.out.println("线程VIP来了" + i);
        }
    }

    public static void main(String[] args) {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin); // 静态代理模式
        thread.start();

        // 主线程
        for (int i = 0; i < 500; i++) {
            if (i == 200) {
                try {
                    thread.join(); // 插队
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main" + i);
        }
    }
}
