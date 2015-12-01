package lock;

import concurrency.SleepUtils;
import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * Created by gang.qin on 2015/11/30.
 * 测试验证TwinsLock
 * 定义工作线程Worker，该线程执行过程中获取锁，然后睡眠一秒，打印当前线程名称，再睡眠一秒。
 * 可以看到线程名成对的出现，也就是同一时刻只有两个线程能够获取到锁
 */
public class TwinsLockTest {
    @Test
    public void Test() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run () {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        // 启动 10 个线程
        for (int i = 0; i < 10; i++) {
            Thread t = new Worker();
            t.setDaemon(true);
            t.start();
        }

        // 每隔一秒换行
        for (int i = 0; i < 100; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }
}
