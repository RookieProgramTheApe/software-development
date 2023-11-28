package online.niehong.juc.collaborative;

import java.util.concurrent.CountDownLatch;
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
}
