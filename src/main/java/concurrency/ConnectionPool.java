package concurrency;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by gang.qin on 2015/11/3.
 * 数据库连接池示例--连接池
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<Connection>();

    // 初始化连接的最大上限
    public ConnectionPool (int initalSize) {
        if (initalSize > 0) {
            for (int i = 0; i < initalSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    // 将连接放回线程池
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                // 连接释放后需要进行通知，这样其它消费者能够感知到连接池中已经归还了一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    // 在 mills 内无法获取到连接，将会返回 null
    public Connection fetchConnection (long mills) throws InterruptedException {
        synchronized (pool) {
            // 完全超时
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
