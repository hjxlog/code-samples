package com.hjxlog.thread;

/**
 * @author: Huang JX
 * @date: 2022/4/17
 * <p>
 * ThreadLocal
 * <p>
 * 使用：
 * 1. get(), set(), remove()
 *
 * 底层：
 * 存储在ThreadLocalMap
 *
 * 在调用 set()、get()、remove() 方法的时候，会清理掉 key 为 null 的记录。
 */
public class ThreadLocalDemo implements Runnable {

    /**
     * static 对于程序来说，是同一个对象
     * 用了ThreadLocal，对于线程来说，同一个对象，可能是不同的值
     * 就比如，小明是现实世界的一个人，但是在他的朋友A和朋友B中，对小明的印象是不一样的
     *
     * // 弱引用
     * static class Entry extends WeakReference<ThreadLocal<?>> {
     *             Object value;
     *
     *             Entry(ThreadLocal<?> k, Object v) {
     *                 super(k);
     *                 value = v;
     *             }
     *         }
     * ThreadLocalMap 中的key是弱引用，在threadLocal被回收之后，如果key值是强引用的话，将不会被gc清理，弱引用的话，就可以被gc清理
     */
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();


    @Override
    public void run() {

    }

}
