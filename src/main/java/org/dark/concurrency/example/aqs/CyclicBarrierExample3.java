package org.dark.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * AQS 同步组件CyclicBarrier
 *
 * @author xiaozefeng
 * @date 2018/4/23 下午1:47
 */
@Slf4j
public class CyclicBarrierExample3 {

    /***
     * 定义有多少个线程要等待
     * 定义到达屏障时先调用的回调方法
     */
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, ()->{
        log.info("callback method is called");
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        cyclicBarrier.await();
        log.info("continue:{}", threadNum);


    }
}
