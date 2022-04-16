package com.hjxlog.thread.state;

/**
 * @author Huang JX
 * @date 2022/4/16
 * @description 测试守护线程
 * 没有用户线程之后，守护线程也会退出
 */
public class TestDaemon {

    public static void main(String[] args) {
        DaemonThread daemonThread = new DaemonThread();
        UserThread userThread = new UserThread();

        Thread thread = new Thread(daemonThread);
        //默认false表示是用户线程,正常的线程都是用户线程...
        thread.setDaemon(true);
        //守护线程启动
        thread.start();
        //用户线程启动
        new Thread(userThread).start();
    }

}

//守护线程
class DaemonThread implements Runnable {
    static int num = 0;

    public void run() {
        while (true) {
            System.out.println("守护线程运行次数" + num++);
        }
    }
}

// 用户线程
class UserThread implements Runnable {
    static int num = 0;

    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("用户线程运行次数" + num++);
        }
        System.out.println("====用户线程退出====");
    }
}
