package org.dark.concurrency.example.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.annotations.ThreadSafe;

import java.util.List;
import java.util.concurrent.*;

/**
 * 并发容器 CopyOnWriteArrayList
 *
 * @author xiaozefeng
 * @date 2018/4/22 下午11:24
 */
@Slf4j
@ThreadSafe
public class CopyOnWriteArrayListExample {
    private static int clientTotal = 5000;
    private static int threadTotal = 200;

    private static List<Integer> list = new CopyOnWriteArrayList<>();

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
