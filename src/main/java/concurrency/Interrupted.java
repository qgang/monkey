package concurrency;


/**
 * Created by gang.qin on 2015/10/26.
 * 理解中断
 * 1. 中断可以理解为线程的一个标识位属性
 * 2. 其它线程调用该线程的interrupt()方法对其进行中断
 * 3. 线程通过isInterrupted()方法来判断是否被中断
 * 4. 如果该线程处于终结状态，即使该线程被中断过，isInterrupted()返回false
 * 5. 抛出InterruptException之前，JVM会先将该线程的中断标识位清除，然后抛出InterruptException，isInterrupted()返回false
 */
public class Interrupted {
    public static void main(String[] args) {
        // sleepThread 不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);

        // busyThread 不停的运行
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);
        busyThread.start();

        // 休息5秒，让sleepThread 和 busyThread 充分运行
        SleepUtils.second(5);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted()); // false，InterruptException 会清除中断标识位
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted()); // true

        // 防止sleepThread 和busyThread 立刻退出
        SleepUtils.second(2);
    }

    static class SleepRunner implements Runnable {
        public void run() {
            while (true) {
                SleepUtils.second(10); // InterruptException 会清除中断标志位
            }
        }
    }

    static class BusyRunner implements Runnable {
        public void run() {
            while (true) {
            }
        }
    }
}
