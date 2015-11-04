package concurrency;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gang.qin on 2015/11/3.
 * 线程池示例--模拟客户端 ConnectionRunner 获取、连接、释放连接的过程
 *
 */
public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);

    // 保证所有 ConnectionRunner 能够同时开始
    static CountDownLatch start = new CountDownLatch(1);

    // main 线程会等待所有 ConnectionRunner 结束后才能继续执行
    static CountDownLatch end;

    private static final int MILLIS = 1000;

    public static void main (String[] args) throws Exception {
        // 线程数量，可以修改线程数量进行观察
        int threadCount = 10;
        end = new CountDownLatch(threadCount);

        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger noGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, noGot), "ConnectionRunnerThread");
            thread.start();
        }

        // CountDownLatch.countDown() releasing all waiting threads if the count reaches zero.
        start.countDown(); // 唤醒所有等待在 start 对象上线程

        end.wait(); // main 线程等待所有 ConnectionRunner 线程结束后再执行
        System.out.println("totao invoke: " + (threadCount + count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection: " + noGot);
    }

    static class ConnectionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner (int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        public void run () {
            try {
                start.await(); // 等待所有 ConnectionRunner 线程建立完成
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (count > 0) {
                try {
                    // 从线程中获取连接， 如果 1000 秒内无法获取到，将会返回 null
                    Connection connection = pool.fetchConnection(MILLIS);

                    // 分别统计连接获取的数量 got 和未获取到的数量 notGot
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            pool.releaseConnection(connection); //  释放连接，放回连接池
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.getAndIncrement();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
