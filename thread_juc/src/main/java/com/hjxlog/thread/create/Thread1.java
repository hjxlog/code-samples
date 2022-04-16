package com.hjxlog.thread.create;

/**
 * @author Huang JX
 * @date 2022/4/16
 * @description
 */
public class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println("启动线程");
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        /**
         * 问题：run()和start()有什么区别？
         *
         * 1. run(); 就是一个普通方法
         *
         * 2. start();
         * 首先检查线程状态 if (threadStatus != 0) throw new IllegalThreadStateException();
         * 加入线程组 group.add(this); ThreadGroup
         * 调用本地方法 private native void start0();由操作系统来启动线程
         */
        thread1.start();
//        thread1.run(); // 相当于一个普通方法
        // wait(); 要在同步代码块中使用，配合notify使用
        // public final native void wait(long timeout) throws InterruptedException;
//        thread1.wait();
    }
}
