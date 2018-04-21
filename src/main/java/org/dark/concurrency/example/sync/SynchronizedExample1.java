package org.dark.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.annotations.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 对synchronized关键字的演示
 *
 *
 *
 * @author xiaozefeng
 * @date 2018/4/21 下午5:45
 */
@Slf4j
@ThreadSafe
public class SynchronizedExample1 {

    /**
     * synchronized 代码块作用于调用对象
     */
    private void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {}, {}", j, i);
            }
        }
    }

    /**
     * @param j
     */
    private synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 {}, {}", j, i);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final SynchronizedExample1 example1 = new SynchronizedExample1();
        final SynchronizedExample1 example2 = new SynchronizedExample1();

        executorService.submit(() -> {
            example1.test2(1);
        });

        executorService.submit(() -> {
            example1.test2(2);
        });


        // 注释掉分别执行
        //executorService.submit(() -> {
        //    example1.test2(1);
        //});
        //
        //executorService.submit(() -> {
        //    example2.test2(2);
        //});

        executorService.shutdown();

    }
}
