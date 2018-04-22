package org.dark.concurrency.example.unsafe;

import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.annotations.NotThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * String中线程不安全的类
 *
 * @author xiaozefeng
 * @date 2018/4/22 上午11:21
 */
@Slf4j
@NotThreadSafe
public class StringExample1 {

    private final static int clientTotal = 5000;

    private final static int threadTotal = 200;

    private final static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    sb.append("a");
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
        log.info("{}", sb.length());
    }
}
