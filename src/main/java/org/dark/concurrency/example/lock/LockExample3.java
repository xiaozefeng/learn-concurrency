package org.dark.concurrency.example.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock 学习
 *
 * @author xiaozefeng
 * @date 2018/4/23 下午4:37
 */
public class LockExample3 {

    class Point {
        private double x, y;
        private final StampedLock sl = new StampedLock();

        /**
         * 排它锁案例
         *
         * @param deltaX
         * @param deltaY
         */
        void move(double deltaX, double deltaY) {
            long stamp = sl.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                sl.unlockWrite(stamp);
            }
        }

        /**
         * 乐观锁案例
         */
        double distanceFromOrigin() {
            // 获取一个乐观锁
            long stamp = sl.tryOptimisticRead();
            // 将两个字段读入本地局部变量
            double currentX = x, currentY = y;
            // 检查发出乐观锁后同时是否有其他写锁发生
            if (!sl.validate(stamp)) {
                // 如果没有，我们再次获得一个读悲观锁
                stamp = sl.readLock();
                // 再次将两个字段读入本地局部变量
                try {
                    currentX = x;
                    currentY = y;
                } finally {
                    sl.unlockRead(stamp);
                }

            }
            return Math.sqrt(currentX * currentX + currentY * currentY);
        }


        /**
         * 悲观锁案例
         */
        void moveIfAtOrigin(double newX, double newY) {
            long stamp = sl.readLock();
            try {
                // 循环检查当前状态是否符合
                while (x == 0.0 && y == 0.0) {
                    // 尝试将读锁转换成写锁
                    long ws = sl.tryConvertToWriteLock(stamp);
                    // 确认转换是否成功
                    if (ws != 0L) {
                        // 如果成功替换票据
                        stamp = ws;
                        // 进行状态改变
                        x = newX;
                        y = newY;
                        break;
                    } else {
                        // 转换失败, 显示去释放锁
                        sl.unlockRead(stamp);
                        // 显示的直接进行写锁，不再循环尝试
                        stamp = sl.writeLock();
                    }
                }
            } finally {
                // 释放读锁或者写锁
                sl.unlock(stamp);
            }
        }


    }

    public static void main(String[] args) {

    }
}
