package org.dark.concurrency.example.singleton;

import org.dark.concurrency.annotations.ThreadSafe;

/**
 * 单例: 懒汉模式
 *  Double Check
 * @author xiaozefeng
 * @date 2018/4/21 下午10:05
 */

@ThreadSafe
public class SingletonExample3 {

    /**
     * 私有构造函数
     */
    private SingletonExample3() {

    }

    /**
     * 单例对象
     * 必须要加上 volatile 禁止指令重排序
     */
    private static volatile SingletonExample3 instance = null;

    /**
     * 静态的工厂方法
     *
     * @return
     */
    public static SingletonExample3 getInstance() {
        // 双重检测检测
        if (instance == null) {
            synchronized (SingletonExample3.class) {
                if (instance == null) {
                    instance = new SingletonExample3();
                }
            }
        }
        return instance;
    }


}
