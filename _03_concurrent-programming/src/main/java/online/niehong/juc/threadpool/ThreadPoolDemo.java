package online.niehong.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池演示
 *
 * @author NieHong
 * @date 2023/11/30
 */
public class ThreadPoolDemo {

    // 定义线程任务，为了方便后续不同线程池的调用
    private static final Thread THREAD = new Thread(() -> {
        try {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });

    public static void main(String[] args) {
        newFixedThreadPool(5);
        newSingleThreadExecutor();
        newCachedThreadPool();
        newScheduledThreadPool();
    }

    /**
     * 新建固定线程池
     * - 每十秒创建一个新线程，给的fixedPoolSize是五，那就意味着每次最多同时执行5个线程
     *
     * @param fixedPoolSize 固定池大小
     */
    private static void newFixedThreadPool(int fixedPoolSize) {
        ExecutorService executorService = Executors.newFixedThreadPool(fixedPoolSize);
        for (int i = 0; i < 100; i++) {
            executorService.execute(THREAD);
        }
    }

    /**
     * 单线程线程池
     */
    private static void newSingleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            executorService.execute(THREAD);
        }
    }

    /**
     * 缓存型线程池
     */
    private static void newCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(THREAD);
        }
    }

    /**
     * 计划任务(定时任务)线程池
     */
    private static void newScheduledThreadPool() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

        // 在到达指定的延迟时间后执行给定的线程任务
        executorService.schedule(THREAD, 3, TimeUnit.SECONDS);
        // 在到达指定的初始时间(initialDelay)后开始执行给定的线程任务，后续以period参数指定的延迟时间循环执行
        executorService.scheduleAtFixedRate(THREAD, 1, 3, TimeUnit.SECONDS);
    }
}
