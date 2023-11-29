package online.niehong.juc.arrayblockingqueue;

import com.sun.org.apache.regexp.internal.REUtil;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列演示
 *
 * @author NieHong
 * @date 2023/11/30
 */
public class ArrayBlockingQueueDemo {

    /**
     * 完成状态
     */
    public static final String FINISH = "finish";

    public static void main(String[] args) {
        ArrayBlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
        Thread producerThread = new Thread(new Producer(bq), "包子铺生产者");
        Thread customerThread = new Thread(new Customer(bq), "包子铺消费者");

        producerThread.start();
        customerThread.start();
    }

    /**
     * 生产者
     *
     * @author NieHong
     * @date 2023/11/30
     */
    static class Producer implements Runnable {

        private final ArrayBlockingQueue<String> queue;

        public Producer(ArrayBlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        @SneakyThrows
        public void run() {
            for (int i = 0; i < 10; i++) {
                queue.put("第" + (i + 1) + "个面包");
                System.out.println("卖家：第" + (i + 1) + "个面包生产好了");
                TimeUnit.MILLISECONDS.sleep(500);
            }
            queue.put(FINISH);
        }
    }

    /**
     * 消费者
     *
     * @author NieHong
     * @date 2023/11/30
     */
    static class Customer implements Runnable {

        ArrayBlockingQueue<String> queue;

        public Customer(ArrayBlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        @SneakyThrows
        public void run() {
            // 如果队列没有数据直接返回
            String take = queue.take();
            while (!FINISH.equals(take)){
                System.out.println("买家：" + take + " 被买走了");
                TimeUnit.SECONDS.sleep(1);
                take = queue.take();
            }

            System.out.println("\n\n今天的包子已卖完，收工\n\n");
        }
    }
}
