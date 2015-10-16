package concurrency;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by gang.qin on 2015/10/16.
 * Java程序天生就是多线程。
 * 以下例子可以看出一个Java程序的运行不仅仅是main()方法的运行，而是main()方法和多个线程同时运行。
 */
public class MultiThread {

    public static void main(String[] args) {
        // 获取Java线程管理
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        // 不需要获取同步的monitor和synchronized信息，仅获取线程和线程进程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

        //遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo info : threadInfos) {
            System.out.println(info.getThreadId() + "\t" + info.getThreadName());
        }
    }
}
