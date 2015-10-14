package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gang.qin on 2015/10/14.
 */
public class Counter {
    private AtomicInteger atomicInt = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<Thread>();
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }

        //开始所以线程
        for (Thread t : ts) {
            t.start();
        }

        //等待所以线程执行完
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(cas.i);
        System.out.println(cas.atomicInt.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    //使用CAS实现线程安全计数
    private void safeCount() {
        while (true) {
            int i = atomicInt.get();
            boolean suc = atomicInt.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    //非线程安全计数
    private void count() {
        i++;
    }
}
