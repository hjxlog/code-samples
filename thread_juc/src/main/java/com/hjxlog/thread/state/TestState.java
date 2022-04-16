package com.hjxlog.thread.state;

/**
 * @author Huang JX
 * @date 2022/4/16
 * @description 观察线程状态
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

        while (state != Thread.State.TERMINATED){
            Thread.sleep(1000);
            state = thread.getState();
            System.out.println(state);
        }

//        thread.start(); // TERMINATED 的线程不能再启动了
    }

}
