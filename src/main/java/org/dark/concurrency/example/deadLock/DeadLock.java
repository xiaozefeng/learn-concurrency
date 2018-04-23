package org.dark.concurrency.example.deadLock;

import lombok.extern.slf4j.Slf4j;

/**
 * 死锁 example
 *
 * @author xiaozefeng
 * @date 2018/4/23 下午8:48
 */
@Slf4j
public class DeadLock implements Runnable {

    private int flag = 1;

    /**
     * 静态对象时类的所有对象共享使用的
     */
    private static Object o1 = new Object(), o2 = new Object();

    public DeadLock(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        log.info("flag:{}", flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
                synchronized (o2) {
                    log.info("1");
                }
            }
        }

        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(),e);
                }
                synchronized (o1) {
                    log.info("0");
                }
            }
        }

    }

    public static void main(String[] args) {
        DeadLock dl1 = new DeadLock(1);
        DeadLock dl2 = new DeadLock(0);

        new Thread(dl1).start();
        new Thread(dl2).start();

    }
}
