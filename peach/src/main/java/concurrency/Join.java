package concurrency;

/**
 * Created by gang.qin on 2015/11/2.
 * Thread.join  当线程终止时，会调用线程自身的 notifyAll() 方法，会通知所有等待在该线程对象上的线程
 * jion() 方法的逻辑结构与 等待/通知 经典范式一致，即加锁、循环和处理三个步骤
 * Thread.join 方法的源码大致结构如下
 *
 * // 加锁当前线程对象
 * public final synchronized void jion () throws InterruptedException {
 *     // 条件不满足，继续等待
 *     while (isAlive()) { // isAlive() 判断的是当前线程 即例子中的 Domino 线程
 *         wait (0); // this.wait(0) 也就是例子中 Domino 线程等待在 previous 线程对象上
 *     }
 *     // 条件满足，理解返回
 * }
 */
public class Join {
    public static void main (String[] args) throws Exception {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            // 每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }

        SleepUtils.second(3);
        System.out.println(Thread.currentThread().getName() + " terminate."); // 线程终止时，会调用线程自身的 notifyAll() 方法，会通知所有等待在该线程对象上的线程
    }

    static class Domino implements Runnable {
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        public void run () {
            try {
                thread.join(); // 当前线程等待该线程执行完成后返回
            } catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread().getName() + " terminate."); // 线程终止时，会调用线程自身的 notifyAll() 方法，会通知所有等待在该线程对象上的线程
        }
    }
}
