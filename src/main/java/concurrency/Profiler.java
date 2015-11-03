package concurrency;

/**
 * Created by gang.qin on 2015/11/3.
 * 线程变量，一个以线程对象为 key, 任意对象为 value 的存储结构
 * 1. 这个结构被附带在线程上，也就是说一个线程可以根据一个 ThreadLocal 对象查询到绑定在这个线程上的一个 value
 * 2. Profiler 可以复用到方法的调用耗时统计功能上，在方法的入口前执行 begin() 方法，在方法调用后执行 end() 方法，
 *    好处是两个方法的调用不用再一个方法或者类中，比如在 AOP 中，可以在方法调用前的切入点执行 begin() 方法，而在方法调用后的切入点执行 end() 方法。
 */
public class Profiler {
    // 第一次 get() 方法调用时会进行初始化（如果 set() 方法没有调用），每个线程会调用一次
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
        protected Long initialVulues() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin () {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end () {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main (String[] args) throws Exception {
        Profiler.begin();
        SleepUtils.second(1);
        System.out.println("Cost: " + Profiler.end() + " mills");
    }
}
