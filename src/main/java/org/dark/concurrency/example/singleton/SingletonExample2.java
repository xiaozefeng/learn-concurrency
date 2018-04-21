package org.dark.concurrency.example.singleton;

import org.dark.concurrency.annotations.NotThreadSafe;

/**
 * 单例: 懒汉模式
 *
 * @author xiaozefeng
 * @date 2018/4/21 下午10:05
 */
@NotThreadSafe
public class SingletonExample1 {

    /**
     * 私有构造函数
     */
    private SingletonExample1(){

    }

    /**
     * 单例对象
     */
    private static SingletonExample1 instance = null;

    /**
     * 静态的工厂方法
     * @return
     */
    public static SingletonExample1 getInstance(){
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }



}
