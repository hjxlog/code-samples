package com.hjxlog.thread.state;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Huang JX
 * @date: 2022/4/17
 * 验证Java中断的代码
 */
public class CarCrossDemo {

    public static void main(String[] args) {
        /**
         * 卡丁2号 准备过桥
         * 发现1号在过，等一下
         * 卡丁1号 开始过桥啦
         * 卡丁 1 号，过桥完毕
         * 卡丁 2 号，开始过桥啦
         * 卡丁 2 号，过桥完毕
         *
         * 卡丁一号过去之后，卡丁2号立马接着过桥，不用等待睡眠完成；
         */

        Thread carTwo = new Thread(() -> {
            System.out.println("卡丁2号 准备过桥");
            System.out.println("发现1号在过，等一下");

            try {
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                System.out.println("卡丁 2 号，开始过桥啦");
//                e.printStackTrace();
            }
            System.out.println("卡丁 2 号，过桥完毕");
        });

        Thread carOne = new Thread(() -> {
            System.out.println("卡丁1号 开始过桥啦");
            int timeSpend = new Random().nextInt(500) + 1000; //控制在 1500之内
            try {
                Thread.sleep(timeSpend);
            } catch (InterruptedException e) {
                System.out.println("卡丁1号 报错");
                e.printStackTrace();
            }
            System.out.println("卡丁 1 号，过桥完毕");

            carTwo.interrupt(); // 中断卡丁2号，跟它说你可以过啦！
        });

        carOne.start();
        carTwo.start();
    }

}
