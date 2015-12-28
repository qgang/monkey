package concurrency;

/**
 * Created by gang.qin on 2015/10/8.
 * 发生死锁例子
 */
public class DeadLockDemo {
    private static String A = "a";
    private static String B = "b";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                synchronized(A) {
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized(B) {
                        System.out.println(1);
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                synchronized(B) {
                    synchronized(A) {
                        System.out.println(2);
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
