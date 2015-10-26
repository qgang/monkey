package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by gang.qin on 2015/10/16.
 * TimeUnit可读性更好的线程暂停操作
 * 1. 通常用来替换Thread.sleep()，(在Thread.sleep()上的封装)
 * 2. Java枚举应用场景中最好的例子之一
 * 3. 并发编程中也是一个关键的API
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            // 替代Thread.sleep()
            // Thread.sleep() 静态方法，暂停线程时不会释放锁，中断抛出InterruptedException
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
