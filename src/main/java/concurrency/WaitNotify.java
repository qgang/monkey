package concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gang.qin on 2015/10/29.
 * 等待/通知 机制
 * 一个线程A调用了对象 O 的 wait() 方法进入等待状态，而另外一个线程B调用了对象 O 的 notify() 方法， 线程A收到通知后从 O 的 wait() 方法返回，进而执行后续操作。
 * 一个线程修改了一个对象的值，而另外一个线程感知到了变化，然后进行相应的操作，整个过程开始于一个线程，而最终执行时另外一个线程。
 *
 * 需要注意的事项：
 * 1. 使用 wait() notify() notifyAll() 时要对调用对象加锁
 * 2. 调用 wait() 方法后，线程状态由 RUNNING 变为 WAITING, 并将当前线程放置到对象的等待队列
 * 3. notify() notifyAll() 方法调用后，等待线程不会从 wait() 返回，需要调用 notify() notifyAll() 的线程释放锁之后，等待线程才有机会从 wait() 返回。
 * 4. notify() 方法将等待队列中的一个等待线程移动到同步队列中。notifyAll() 是将等待队列中的所有线程移动到同步队列。
 * 5. 从 wait() 方法中返回的前提是获得了调用对象的锁
 *
 *
 * 等待/通知 经典范式
 * 该范式分为两部分，分别针对等待方（消费者）和通知方（生产者）
 *
 * 等待方规则如下：
 * 1. 获取对象锁
 * 2. 如果条件不满足，那么调用对象的 wait() 方法， 被通知后仍要检查条件
 * 3. 条件满足则执行对应的逻辑
 *
 * synchronized( object ) {
 *     while ( false ) {
 *         object.wait();
 *     }
 *     dosomething
 * }
 *
 * 通知方的规则如下：
 * 1. 获取对象的锁
 * 2. 改变条件
 * 3. 通知所有等待在对象上的线程
 *
 * synchronized( object) {
 *     change condition
 *     object.notifyAll();
 * }
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws Exception {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();

        SleepUtils.second(1);

        Thread notifyThread = new Thread(new Notity(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {
        public void run () {

            // 加锁， 拥有 lock 的 Monitor
            synchronized (lock) {
                // 当条件不满足时，继续 wait, 同时释放了 lock 的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true, wait @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {

                    }
                }

                // 条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is false, running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));

            }
        }
    }

    static class Notity implements Runnable {
        public void run () {
            // 加锁， 拥有 lock 的 Monitor
            synchronized (lock) {
                // 获取 lock 的锁，然后进行通知，通知时不会释放 lock 的锁
                // 直到当前线程释放了 lock 后，WaitThread 才能从 wait 方法中返回
                System.out.println(String.format(Thread.currentThread() + " hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date())));
                lock.notify();
                flag = false;
                SleepUtils.second(5);
            }

            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }
}
