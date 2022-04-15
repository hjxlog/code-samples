package com.hjxlog.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Huang JX
 * @date 2022/4/15
 * @description 实现Callable接口，重写call方法，有返回值
 * Callable<String> 泛型
 */
public class CallableDemo implements Callable<Integer> {

    public Integer call() throws Exception {
        Random random = new Random();
        return random.nextInt();
    }

    /**
     * 步骤：
     * 实现Callable接口，需要返回值类型
     * 重写call方法，需要抛出异常
     * 创建目标对象
     * 创建执行服务：ExecutorService ser = Executors.newFixedThreadPool(1);
     * 提交执行：Future result1 = ser.submit(11);
     * 获取结果：boolean r1 = result1.get()
     * 关闭服务：ser.shutdownNow();
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDemo c1 = new CallableDemo();
        CallableDemo c2 = new CallableDemo();
        CallableDemo c3 = new CallableDemo();
        //创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);
        //提交执行
        Future<Integer> r = ser.submit(c1);
        Future<Integer> r1 = ser.submit(c2);
        Future<Integer> r2 = ser.submit(c3);
        //获取结果
        System.out.println(r.get());
        System.out.println(r1.get());
        System.out.println(r2.get());
        //关闭服务
        ser.shutdownNow();
    }

}
