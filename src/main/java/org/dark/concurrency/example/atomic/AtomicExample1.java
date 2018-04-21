package org.dark.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.annotations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 多线程原子性example
 *
 * @author xiaozefeng
 * @date 2018/4/20 下午11:16
 */
@Slf4j
@ThreadSafe
public class AtomicExample1 {
    /**
     * 请求总数
     */
    private static final int clientTotal = 5000;

    /**
     * 同时允许的并发数
     */
    private static final int threadTotal = 200;

    private static AtomicLong count = new AtomicLong(0);


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("result:{}", count.get());
    }

    private static void add() {
        count.incrementAndGet();
    }
}
