package com.hjxlog.thread.state;

/**
 * @author Huang JX
 * @date 2022/4/16
 * @description 观察线程状态
 * <p>
 * 总结：
 * 1. 线程睡眠，sleep() public static native void sleep(long millis) throws InterruptedException;
 * 为啥要强制它抛出异常呢？原因只有一个，让它从睡眠中提前醒来；
 * <p>
 * 2. 线程中断异常：InterruptedException
 * 当线程等待、休眠或以其他方式被占用，并且线程在活动之前或期间被中断时抛出。
 */
public class TestState {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("//////");
        }

        // 观察状态
        Thread.State state = thread.getState();
        System.out.println(state);

        thread.start();
        state = thread.getState();
        System.out.println(state);

        while (state != Thread.State.TERMINATED) {
            Thread.sleep(1000);
            state = thread.getState();
            System.out.println(state);
        }

//        thread.start(); // TERMINATED 的线程不能再启动了
    }

}
