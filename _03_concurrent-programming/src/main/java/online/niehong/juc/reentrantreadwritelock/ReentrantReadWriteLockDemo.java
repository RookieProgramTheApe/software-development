package online.niehong.juc.reentrantreadwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 可重入读写锁演示
 *
 * @author NieHong
 * @date 2023/11/27
 */
public class ReentrantReadWriteLockDemo {

    private volatile static int count;
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        ReentrantReadLock reentrantReadLockThread = new ReentrantReadLock(lock);
        ReentrantWriteLock reentrantWriteLockThread = new ReentrantWriteLock(lock);

        for (int i = 0; i < 15; i++) {
            new Thread(reentrantWriteLockThread).start();
        }

        for (int i = 0; i < 150; i++) {
            new Thread(reentrantReadLockThread).start();
        }
    }

    /**
     * 可重入读锁
     *
     * @author NieHong
     * @date 2023/11/27
     */
    static class ReentrantReadLock implements Runnable {
        public ReentrantReadWriteLock lock;

        public ReentrantReadLock(ReentrantReadWriteLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.readLock().lock();
            System.out.println("读锁：" + count);
            lock.readLock().unlock();
        }
    }

    /**
     * 可重入写入锁
     *
     * @author NieHong
     * @date 2023/11/27
     */
    static class ReentrantWriteLock implements Runnable {
        public ReentrantReadWriteLock lock;

        public ReentrantWriteLock(ReentrantReadWriteLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.writeLock().lock();
            count++;
            System.out.println("写锁：" + count);
            lock.writeLock().unlock();
        }
    }
}
