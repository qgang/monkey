package concurrency;

import java.awt.*;

/**
 * Created by gang.qin on 2015/10/26.
 * 守护线程
 * 1. daemon线程，主要被用作程序中后台调度以及支持性工作
 * 2. JVM中不存在daemon线程时，将会退出
 * 3. 线程的daemon属性要在线程启动之前设置
 * 4. 构建daemon线程时，不能依靠finally块中的内容来确保执行关闭或者清理资源的逻辑
 */
public class Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        thread.setDaemon(true); // daemon属性要在start之前设置
        thread.start();
        // 程序将没有任何输出，随着main线程的结束，JVM中不存在daemon线程，将退出
    }

    static class DaemonRunner implements Runnable {
        public void run() {
            try {
                SleepUtils.second(10);
            } finally { // 没有任何输出
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
