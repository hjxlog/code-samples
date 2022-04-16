package com.hjxlog.communicate;

/**
 * @author Huang JX
 * @date 2022/4/16
 * @description 测试生产者消费者问题：信号灯法，通过标志位解决
 */
public class TestPC2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
        new Watcher(tv).start();
        new Watcher(tv).start();
    }
}

//生产者
class Player extends Thread {
    TV tv;

    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                this.tv.play("节目:快乐大本营播放中");
            } else {
                this.tv.play("广告:抖音,记录美好生活");
            }
        }
    }
}

//电视
class TV {
    //演员说话 , 观众等待
    //观众观看 , 演员等待
    boolean flag = true;

    //说话
    String voice;

    //表演
    public synchronized void play(String voice) {
        //演员等待
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("表演了" + voice);
        this.voice = voice;
        //让观众观看
        this.notifyAll();
        this.flag = !this.flag;

    }

    //观看
    public synchronized void watch() {
        //观众等待
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众听到了: " + voice);
        //通知演员说话
        this.notifyAll();
        this.flag = !this.flag;
    }

}

//消费者
class Watcher extends Thread {
    TV tv;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}
