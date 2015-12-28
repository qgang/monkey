package concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gang,.qin on 2015/11/5.
 */
public class DefualtThreadPool <Job extends Runnable> implements ThreadPool<Job>{
    // 线程池最大限制数
    private static final int MAX_WORKER_NUMBERS = 10;

    // 线程池默认的数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    // 线程池的最小的数量
    private static final int MIN_WORKER_NUMBERS = 1;

    // 这是一个工作列表，将会向里面插入工作
    private final LinkedList<Job> jobs = new LinkedList<Job>();

    // 工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    // 工作者线程的数量
    private int workerNum = DEFAULT_WORKER_NUMBERS;

    // 线程编号生成
    private AtomicInteger threadNum = new AtomicInteger();

    public DefualtThreadPool () {
        initializeWorkers (DEFAULT_WORKER_NUMBERS);
    }

    public DefualtThreadPool (int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
        initializeWorkers(num);
    }

    public void execute (Job job) {
        if (job != null) {
            // 添加一个工作，然后进行通知
            synchronized (jobs) {
                jobs.add(job);
                jobs.notify();
            }
        }
    }

    public void shutdown () {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    public void addWorkers (int num) {
        synchronized (jobs) {
            // 限制新增加的 Worker 数量不能超过最大值
            if (num + workers.size() > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - workers.size();
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    public void removeWorker (int num) {
        synchronized (jobs) {
            if (num > this.workerNum) {
                throw new IllegalArgumentException("beyond worker num");
            }

            // 按照给定的数量停止 Worker
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                }
            }
            this.workerNum -= num;
        }
    }

    public int getJobSize() {
        return jobs.size();
    }

    // 初始化线程工作者
    private void initializeWorkers (int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    // 工作者负责消费任务
    class Worker implements Runnable {
        // 是否工作
        private volatile boolean running = true;
        public void run () {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    // 如果工作列表是控的，那么就 wait
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            // 感知到外部对 WokerThread 的中断操作，返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    // 取出一个 Job
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {
                        // 忽略 Job 执行中的 Exception
                    }
                }
            }
        }

        public void shutdown () {
            running = false;
        }
    }
}
