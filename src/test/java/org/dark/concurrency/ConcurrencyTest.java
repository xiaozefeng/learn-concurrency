package org.dark.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.annotations.NotThreadSafe;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 使用代码模拟并发场景
 *
 * @author xiaozefeng
 * @date 2018/4/20 下午5:51
 */
@Slf4j
@NotThreadSafe
public class ConcurrencyTest {

    // 请求总数
    private static int clientTotal =5000;

    // 同时并发执行的线程数
    private static int threadTotal = 200;

    private static int count = 0;

    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);

    }

    private void add() {
        count++;
    }
}
