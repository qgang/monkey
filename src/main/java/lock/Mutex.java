package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by gang.qin on 2015/11/13.
 * 自定义同步组件，（非重入锁）
 */
public class Mutex implements Lock{
    // 静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        // 是否处于占用状态
        protected boolean isHeldExclusively () {
            return getState() == 1;
        }

        // 当状态为0的时候获取锁
        public boolean tryAcquire (int acquires) {
            if (compareAndSetState(0, 1)) { // 非重入，相同线程再次进入会被自己阻塞
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 释放锁，将状态置为0
        public boolean tryRelease (int releases) {
            if (getState() == 0) {
                throw  new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 返回一个 Condition，每个 condition 都包含了一个 condition 实例
        Condition newCondition () {
            return new ConditionObject();
        }
    }

    // 仅需要将操作代理到 Sync 上即可
    private final Sync sync = new Sync();

    public void lock () {
        sync.acquire(1);
    }

    public boolean tryLock () {
        return sync.tryAcquire(1);
    }

    public void unlock () {
        sync.release(1);
    }

    public Condition newCondition () {
        return sync.newCondition();
    }

    public boolean isLocked () {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads () {
        return sync.hasQueuedThreads();
    }

    public  void lockInterruptibly () throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock (long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }
}
