package com.hjxlog.communicate;

/**
 * @author Huang JX
 * @date 2022/4/16
 * @description 生产者 消费者模型，利用缓冲区解决：管程法
 */
public class TestPC {

    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Producer(container).start();
        new Consumer(container).start();
    }
}

// 生产者
class Producer extends Thread {
    private SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        // 生产100只鸡
        for (int i = 1; i <= 100; i++) {
            container.push(new Chicken(i));
        }
    }
}

// 消费者
class Consumer extends Thread {
    private SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            Chicken pop = container.pop();
        }
    }
}

// 产品
class Chicken {
    int id; //编号

    public Chicken(int id) {
        this.id = id;
    }
}

// 缓冲区
class SynContainer {
    // 容量，类似仓库大小，只能存放这么多
    private Chicken[] chickens = new Chicken[10];

    // 容器计数器
    int count = 0;

    // 生产者放入产品
    public synchronized boolean push(Chicken chicken) {
        // 如果容器满了，则释放锁，挂起，等待消费者消费
        if (chickens.length == count) {
            System.out.println("=========容器满了，等待消费者消费=========");
            try {
                this.wait(); // 挂起等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false; // 生产者放入失败
        } else {
            // 如果没有满，需要放入容器
            int num = count++;
            chickens[num] = chicken;
            System.out.println("=====生产者" + Thread.currentThread().getName() + "放入第" + chicken.id + "只鸡到容器=====");
            // 通知消费者消费
            this.notifyAll(); // 通知所有
            return true; // 生产者放入成功
        }
    }

    // 消费者消费产品
    public synchronized Chicken pop() {
        // 判断是否可以消费
        if (count <= 0) {
            // 等待生产者生产
            System.out.println("=========容器里没有产品，等待生产者生产=========");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            // 如果可以消费
            count--;
            Chicken chicken = chickens[count];
            System.out.println("消费者" + Thread.currentThread().getName() + "消费了第【" + chicken.id + "】只鸡");
            // 消费完了，通知生产者生产
            this.notifyAll();
            return chicken;
        }
    }


}