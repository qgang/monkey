package concurrency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * Created by gang.qin on 2015/11/3.
 * 数据库连接池示例--动态代理构造 connect
 */
public class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler {
        public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
                SleepUtils.second(100);
            }
            return null;
        }
    }

    // 创建一个 Connection 的代理， 在 commit 时休眠100毫秒
    public static final Connection createConnection () {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(), new Class<?>[] {Connection.class}, new ConnectionHandler());
    }
}
