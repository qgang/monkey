package concurrency;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gang.qin on 2015/10/26.
 * 过期的suspend()、resume()、stop()
 * 1. 不建议使用原因，如suspend() 方法会占有资源进入睡眠状态，stop() 方法在终结一个线程时不会保证线程的资源正常释放，这些都容易发生死锁。
 * 2. 暂停恢复操作可以用等待/通知机制来代替
 */
public class Deprecated {
    public static void main(String[] args) {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Thread printThread = new Thread(new Runner(), "PrintThread");
        printThread.setDaemon(true);
        printThread.start();

        SleepUtils.second(3);

        // 将PrintThread 进行暂停，输出内容工作停止
        printThread.suspend();
        System.out.println("main suspend PrintThread at " + format.format(new Date()));

        SleepUtils.second(3);

        // 将PrintThread 进行恢复，输出内容继续
        printThread.resume();
        System.out.println("main resume PrintThread at " + format.format(new Date()));

        SleepUtils.second(3);

        // 将PrintThread 进行终止，输出内容停止
        printThread.stop();
        System.out.println("main stop PrintThread at " + format.format(new Date()));

        SleepUtils.second(3);
    }

    static class Runner implements Runnable {
        public void run() {
            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                System.out.println(Thread.currentThread().getName() + " Run at " + format.format(new Date()));
                SleepUtils.second(1);
            }
        }
    }
}
