package org.dark.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 多线程原子性example
 *
 * @author xiaozefeng
 * @date 2018/4/20 下午11:16
 */
@Slf4j
@ThreadSafe
public class AtomicExample3 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        // 2
        count.compareAndSet(0, 2);
        count.compareAndSet(0, 1);
        // 3
        count.compareAndSet(2, 3);
        count.compareAndSet(3, 4);
        log.info("result:{}", count.get());
    }
}

