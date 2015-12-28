package concurrency;

/**
 * Created by gang.qin on 2015/10/27.
 * 安全的终止线程
 * 1. 中断操作是一种简便的线程间交互方式，最适合用来取消暂停任务
 * 2. 可以用boolean 变量来控制要停止的任务
 * 3. 这两种方式能够使线程在终止时有机会去清理资源，而不是武断地将线程停止(stop())，因此这种方式更加安全。
 */
public class Shutdown {
    public static void main(String[] args) {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "countThread");
        countThread.start();

        // 睡眠1秒，main 线程对CountThread 线程进行中断，使CountThread 能够感知中断而结束
        SleepUtils.second(1);
        countThread.interrupt(); // 用中断方式停止任务

        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();

        // 睡眠1秒，main 线程对Runner two 进行取消，使CountThread 能够感知on为false而结束
        SleepUtils.second(1);
        two.cancel(); // 用Boolean 变量来停止任务
    }

    static class Runner implements Runnable {
        private long i;
        private volatile boolean on = true; // volatile 使一个线程对其值的改变，其它线程能感知到

        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel() {
            on = false;
        }
    }
}
