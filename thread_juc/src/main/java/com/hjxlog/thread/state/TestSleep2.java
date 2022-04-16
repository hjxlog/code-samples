package com.hjxlog.thread.state;

/**
 * @author Huang JX
 * @date 2022/4/15
 * @description 模拟倒计时
 */
public class TestSleep2 {

    public static void main(String[] args) {
        try {
            tenDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟倒计时
     * @throws InterruptedException
     */
    public static void tenDown() throws InterruptedException {
        int num = 10;
        while (true) {
            // sleep 不会释放锁
            Thread.sleep(1000);
            System.out.println(num--);
            if (num <= 0) {
                break;
            }
        }
    }

}
