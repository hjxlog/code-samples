package com.hjxlog.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author: Huang JX
 * @date: 2022/4/17
 * <p>
 * AQS源码分析：
 * 1. abstract class AbstractQueuedSynchronizer 是一个抽象类
 *
 * 2. AQS的成员变量：
 *  private volatile int state; 判断共享资源是否正在被占用的那个标记位；volatile保证线程之间的可见性；
 *  为什么是 Int 而不是boolean，线程占有锁有两种模式：独占和共享
 *
 * 总结：
 * 1. AQS底层使用了很多CAS操作；
 */
public class AQSDemo {

    public static void main(String[] args) {
        /**
         * 1. 尝试获取锁，获取不到也没关系，arg代表了对state的修改；
         *    子类可以重写这个方法，尝试获取锁，获取失败了之后要怎么处理，都由上层子类自己定义处理
         *     protected boolean tryAcquire(int arg) {
         *         throw new UnsupportedOperationException();
         *     }
         *
         * 2. 如果选择等待锁，那么可以直接调用 public final void acquire(int arg){} 方法
         *    而不用自己去做复杂的排队处理；
         *    public final修饰符，其他所有继承者都可以直接调用这个方法，但是不能够被修改；也就是说，调用这个方法一定能获得锁；
         *
         *    public final void acquire(int arg) {
         *         if (!tryAcquire(arg) &&    // !tryAcquire(arg) 为false直接跳出
         *             acquireQueued(addWaiter(Node.EXCLUSIVE), arg)) // acquireQueued 进行排队等待锁
         *             selfInterrupt();
         *     }
         *
         *    Node addWaiter(Node mode) 将当前线程封装成Node结点后加入等待队列
         *
         *    private Node addWaiter(Node mode) {
         *          // 根据mode（）独占模式 将当前线程封装成Node
         *         Node node = new Node(Thread.currentThread(), mode);
         *
         *         // 试试快速入队，如果失败了再进行完整入队
         *         // Try the fast path of enq; backup to full enq on failure
         *         Node pred = tail; // 获得尾节点，尾部进队
         *         if (pred != null) { // 尾节点不为空
         *             node.prev = pred; // 将尾节点置为当前节点的前置节点
         *             if (compareAndSetTail(pred, node)) { // CAS操作
         *                 pred.next = node; // 设置到尾节点中
         *                 return node;
         *             }
         *         }
         *         // 如果当前队列尾节点为空或者第一次尝试CAS操作失败，那就进入完整入队方法 enq(node);
         *         enq(node);
         *         return node;
         *     }
         *
         *     // 将节点插入队列，必要时进行初始化
         *     private Node enq(final Node node) {
         *         for (;;) { // 自旋操作
         *             Node t = tail;
         *             if (t == null) { // Must initialize  当前尾节点为空，进入初始化
         *                 if (compareAndSetHead(new Node())) //CAS
         *                     tail = head;
         *             } else {
         *                 node.prev = t;
         *                 if (compareAndSetTail(t, node)) { //CAS
         *                     t.next = node;
         *                     return t;
         *                 }
         *             }
         *         }
         *     }
         *
         *     // 对线程进行挂起和响应
         *     // 以独占不间断模式获取已在队列中的线程。由条件等待方法和获取使用。
         *     final boolean acquireQueued(final Node node, int arg) {
         *         boolean failed = true;
         *         try {
         *             boolean interrupted = false; // interrupted 被打断，初始值为false
         *             for (;;) { // 自旋操作
         *                 final Node p = node.predecessor(); // 获取当前节点的前置节点
         *                 if (p == head && tryAcquire(arg)) { // 如果前置节点是头节点并且尝试获取锁成功了
         *                     // AQS的双向链表中，头节点是虚节点，充当占位摆设，方便操作
         *                     // 当第二个节点拿到锁之后，它就成为了头节点，然后头节点出队
         *                     setHead(node);
         *                     p.next = null; // help GC  设置为空，方便GC回收
         *                     failed = false;
         *                     return interrupted;
         *                 }
         *                 // 如果当前节点需要挂起并且成功挂起
         *                 // 避免一直自旋，自旋是让CPU空转
         *                 // 需要将那些还没有轮到它出队的那个线程挂起，再在适合的时间把它们唤醒
         *                 // 如果shouldParkAfterFailedAcquire返回true，则执行真正的挂起
         *                 // 如果shouldParkAfterFailedAcquire返回false，则进行下一轮的判断
         *                 if (shouldParkAfterFailedAcquire(p, node) &&
         *                     parkAndCheckInterrupt()) // 真正的挂起操作
         *                     interrupted = true;
         *             }
         *         } finally {
         *             if (failed)
         *                // node.thread = null; 将线程置为空
         *                // node.waitStatus = Node.CANCELLED; 将节点的等待状态置为取消
         *                // 其他的一些清理操作
         *                 cancelAcquire(node); // 取消正在进行的获取尝试。
         *         }
         *     }
         *
         *     // 判断哪种条件下需要挂起
         *     private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
         *         int ws = pred.waitStatus;
         *         if (ws == Node.SIGNAL) // 如果当前节点的前置节点是SIGNAL，说明前置节点也在等待拿锁；
         *             return true; // 所以当前节点可以挂起休息
         *         if (ws > 0) { // 如果大于0，状态只能是 CANCEL，枚举值，只有它大于0
         *             do {
         *                 node.prev = pred = pred.prev; // 将其从队列中删除
         *             } while (pred.waitStatus > 0);
         *             pred.next = node;
         *         } else {
         *             // 否则 CAS操作将前置节点置为SIGNAL，让其被唤醒
         *             compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
         *         }
         *         return false; // 返回false进行下一轮的判断
         *     }
         *
         *
         *    什么时候释放锁呢？
         *        public final boolean release(int arg) {
         *         if (tryRelease(arg)) { // 尝试唤醒成功
         *             Node h = head;
         *             if (h != null && h.waitStatus != 0) // 唤醒等待队列里的其他节点
         *                 unparkSuccessor(h); // 从尾部搜索，唤醒
         *             return true;
         *         }
         *         return false;
         *     }
         */
        AbstractQueuedSynchronizer abstractQueuedSynchronizer = null;
    }

}
