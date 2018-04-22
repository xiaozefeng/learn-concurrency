package org.dark.concurrency.example.syncContainer;

import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.annotations.NotThreadSafe;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 线程不安全的List
 *
 * @author xiaozefeng
 * @date 2018/4/22 上午11:28
 */
@Slf4j
@NotThreadSafe
public class VectorExample1 {

    private static int clientTotal = 5000;
    private static int threadTotal = 200;

    private static List<Integer> list = new Vector<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        final Semaphore semaphore = new Semaphore(threadTotal);

        for (int i = 0; i < clientTotal; i++) {
            final int temp = i;
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    update(temp);
                    semaphore.release();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();

        log.info("list size :{}",list.size());
    }

    private static void update(int temp) {
        list.add(temp);
    }

}
