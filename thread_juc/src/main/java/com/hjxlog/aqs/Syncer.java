package com.hjxlog.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author: Huang JX
 * @date: 2022/4/17
 */
public class Syncer extends AbstractQueuedSynchronizer {

    /**
     * 子类重写尝试获取锁的方法 tryAcquire ，对其进行操作
     * 尝试获取锁，获取失败了之后要怎么处理，都由上层子类自己定义处理
     *
     * @param arg
     * @return
     */
    @Override
    protected boolean tryAcquire(int arg) {
        if (arg != 1) {
            return false;
        }
        if (getState() == 1) {
            return false;
        }
        return compareAndSetState(0, 1);
    }

    public static void main(String[] args) {
        Syncer syncer = new Syncer();
        boolean acquire = syncer.tryAcquire(1);
        System.out.println(acquire);
    }

}
