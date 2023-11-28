package online.niehong.juc.collaborative;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * JUC的线程协作工具类
 *
 * @author NieHong
 * @date 2023/11/28
 */
public class Tools {
    public static void main(String[] args) {
        countDownLatch(new CountDownLatch(5));
        semaphore(new Semaphore(2), 5);
    }

    /**
     * 计数门闩的使用
     * - 让老板必须加班到最晚
     *
     * @param downLatch 计数门闩
     */
    private static void countDownLatch(CountDownLatch downLatch) {
        for (long i = 0; i < downLatch.getCount(); i++) {
            new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "下班走人了");
                downLatch.countDown();
            }, "员工 " + i).start();
        }

        new Thread(() -> {
            try {
                downLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "最后下班，无敌的存在");
        }, "boss").start();
    }

    /**
     * 信号量
     * 信号量的使用
     * - 模拟停车场车位的抢占情况，即指定的车位，只能停指定的车，多余的车则处于等待状态
     *
     * @param semaphore 信号量(具体能停多少俩车)
     * @param carNumber 车的数量
     */
    private static void semaphore(Semaphore semaphore, int carNumber) {
        for (int i = 0; i < carNumber; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到了车位");
                    TimeUnit.MILLISECONDS.sleep(300);
                    System.out.println(Thread.currentThread().getName() + "离开了车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }, "汽车" + i + " ").start();
        }
    }
}
