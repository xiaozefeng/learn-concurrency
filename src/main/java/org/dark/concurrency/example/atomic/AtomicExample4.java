package org.dark.concurrency.example.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 原子地更新某个对象的某个字段
 * 这个字段必须是非static
 * 被volatile 修饰
 *
 * @author xiaozefeng
 * @date 2018/4/20 下午11:16
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {
    private static AtomicIntegerFieldUpdater<AtomicExample4> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample4.class, "count");

    /**
     * 必须使用volatile 修饰
     * 非static
     * 必须是基础类型
     */
    @Getter
    private volatile int count = 100;

    private static AtomicExample4 example4 = new AtomicExample4();

    public static void main(String[] args) {
        if (updater.compareAndSet(example4, 100, 200)) {
            log.info("update success, count:{}", example4.count);
        }
        if (updater.compareAndSet(example4, 100, 200)) {
            log.info("update success, count: {}", example4.count);
        } else {
            log.info("update failed, count: {}", example4.count);
        }
    }
}

