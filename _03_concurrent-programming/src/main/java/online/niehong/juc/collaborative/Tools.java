package online.niehong.juc.collaborative;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JUC的线程协作工具类
 *
 * @author NieHong
 * @date 2023/11/28
 */
public class Tools {
    public static void main(String[] args) {
//        countDownLatch(new CountDownLatch(5));
//        semaphore(new Semaphore(2), 5);
//        cyclicBarrier(new CyclicBarrier(3, () -> System.out.println("存入银行")));
        condition();
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

    /**
     * 循环栅栏
     * - 模拟存钱，存到多少钱了就存到银行里去
     *
     * @param cyclicBarrier 循环栅栏
     */
    private static void cyclicBarrier(CyclicBarrier cyclicBarrier) {
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println("存了" + finalI + "元钱");
                    TimeUnit.MILLISECONDS.sleep(10);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    /**
     * 条件
     * - 模拟五个人一天的作息顺序
     */
    private static void condition() {
        Work work = new Work();

        // 起床
        for (int i = 0; i < 5; i++) {
            new Thread(work::getUp).start();
        }

        // 去工作
        for (int i = 0; i < 5; i++) {
            new Thread(work::gotoWork).start();
        }

        // 下班了
        for (int i = 0; i < 5; i++) {
            new Thread(work::goHome).start();
        }

    }

    /**
     * 工作
     *
     * @author NieHong
     * @date 2023/11/28
     */
    static class Work {
        private volatile int step = 1;
        Lock lock = new ReentrantLock();
        private final Condition c1 = lock.newCondition();
        private final Condition c2 = lock.newCondition();
        private final Condition c3 = lock.newCondition();

        public Work() {
        }

        /**
         * 起床
         */
        public void getUp() {
            lock.lock();
            try {
                while (step != 1) {
                    c1.await();
                }
                System.out.println("起床");
                step = 2;
                c2.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        /**
         * 去工作
         */
        public void gotoWork() {
            lock.lock();
            try {
                while (step != 2) {
                    c2.await();
                }

                System.out.println("去工作");
                step = 3;
                c3.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        public void goHome() {
            lock.lock();

            try {
                while (step != 3) {
                    c3.await();
                }
                System.out.println("下班回家了");
                step = 1;
                c1.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
