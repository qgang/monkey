package lock;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gang.qin on 2015/11/30.
 * 测试观察公平和非公平锁在获取锁时的区别
 */
public class FairAndUnfairTest {

    private static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2 (boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThread () {
            return null;
        }
    }
}
