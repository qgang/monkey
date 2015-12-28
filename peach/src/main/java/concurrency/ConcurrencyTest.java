package concurrency;

/**
 * Created by gang.qin on 2015/10/8.
 * 多线程不一定不比串行执行快，线程直接的上下文切换带来的消耗不容小视
 */
public class ConcurrencyTest {
    private static final long count = 100001;

    public static void main(String[] args) throws InterruptedException{
        concurrency();
        serial();
    }

    public static void concurrency() throws InterruptedException{
        long begin = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();// 开启子线程

        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        thread.join();// join等待子线程执行完成后，再继续执行。
        long time = System.currentTimeMillis() - begin;
        System.out.println("concurrency:" + time + "ms,b=" + b);
    }

    public static void serial() {
        long begin = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }

        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }

        long time = System.currentTimeMillis() - begin;
        System.out.println("concurrency:" + time + "ms,b=" + b);
    }
}
