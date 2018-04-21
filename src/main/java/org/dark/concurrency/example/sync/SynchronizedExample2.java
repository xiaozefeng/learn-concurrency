package org.dark.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.annotations.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 对synchronized关键字的演示
 *
 * @author xiaozefeng
 * @date 2018/4/21 下午5:45
 */
@Slf4j
@ThreadSafe
public class SynchronizedExample2 {

    private static void test1(int j) {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {}, {}", j, i);
            }
        }
    }

    /**
     * 静态 synchronized 修饰的方法作用域所有对象
     * @param j
     */
    private static synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 {}, {}", j, i);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(() -> {
            SynchronizedExample2.test1(1);
        });

        executorService.submit(() -> {
            SynchronizedExample2.test2(2);
        });


        executorService.shutdown();

    }
}
