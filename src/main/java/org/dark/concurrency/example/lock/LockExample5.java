package org.dark.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 中的Condition
 *
 * @author xiaozefeng
 * @date 2018/4/23 下午5:15
 */
@Slf4j
public class LockExample5 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                 // 1
                log.info("1. wait signal");
                condition.await();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            // 4
            log.info("4. get signal");
            lock.unlock();
        }).start();

        new Thread(() -> {
            lock.lock();
            // 2
            log.info("2. get lock");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            condition.signalAll();
            // 3
            log.info("3. send signal ~");
            lock.unlock();
        }).start();
    }
}
