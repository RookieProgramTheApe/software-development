package online.niehong.juc.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁分析
 *
 * @author NieHong
 * @date 2023/11/27
 */
public class ReentrantLockAnalyze {
    /**
     * 主要目标：理解AQS原理
     *  1.加锁，线程抢夺锁实现
     *  2.解锁，线程被唤醒实现
     */
    public void analyze() {
        Lock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
    }
}
