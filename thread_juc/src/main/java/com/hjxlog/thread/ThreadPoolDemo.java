package com.hjxlog.thread;

import java.util.concurrent.*;

/**
 * @author Huang JX
 * @date 2022/4/3
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                3,
                5,
                1L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 9; i++) {
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName() + "====>在办理业务");
            });
        }
        executorService.shutdown(); // 关闭线程池
    }

}
