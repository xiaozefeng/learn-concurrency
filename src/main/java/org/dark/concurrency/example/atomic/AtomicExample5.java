package org.dark.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.annotations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *  使某一段代码只执行一次
 *
 * @author xiaozefeng
 * @date 2018/4/20 下午11:16
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {
    /**
     * 请求总数
     */
    private static final int clientTotal = 5000;

    /**
     * 同时允许的并发数
     */
    private static final int threadTotal = 200;

    private static AtomicBoolean isHappened = new AtomicBoolean(false);


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    test();
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
        log.info("result:{}", isHappened.get());
    }

    private static void test() {
        if (isHappened.compareAndSet(false, true)) {
            log.info("execute()");
        }else {
            log.info("not execute()");
        }
    }

}
