package org.dark.concurrency.example.singleton;

import org.dark.concurrency.annotations.NotThreadSafe;

/**
 * 单例: 饿汉模式
 *
 *
 * @author xiaozefeng
 * @date 2018/4/21 下午10:05
 */
@NotThreadSafe
public class SingletonExample2 {

    /**
     * 私有构造函数
     */
    private SingletonExample2(){

    }

    /**
     * 单例对象
     */
    private static SingletonExample2 instance = new SingletonExample2();

    /**
     * 静态的工厂方法
     * @return
     */
    public static SingletonExample2 getInstance(){
        return instance;
    }



}
