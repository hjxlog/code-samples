package com.hjxlog.juc.demo;

import java.util.concurrent.TimeUnit;

/**
 * @author Huang JX
 * @date 2022/4/16
 * @description
 */
public class Test1 {
    public static void main(String[] args) {
        // 获取CPU 的核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        /**
         * NEW
         * RUNNABLE
         * BLOCKED
         * WAITING
         * TIMED_WAITING
         * TERMINATED
         */
        Thread.State state = Thread.currentThread().getState();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
