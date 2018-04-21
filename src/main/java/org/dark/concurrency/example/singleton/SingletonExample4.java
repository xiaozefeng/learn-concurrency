package org.dark.concurrency.example.singleton;

import lombok.Getter;
import org.dark.concurrency.annotations.Recommend;
import org.dark.concurrency.annotations.ThreadSafe;

/**
 * 使用枚举实现单例
 *
 * @author xiaozefeng
 * @date 2018/4/21 下午10:16
 */
@ThreadSafe
@Recommend
public class SingletonExample4 {

    /**
     * 私有构造函数
     */
    private SingletonExample4() {

    }

    public static SingletonExample4 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }


    private enum Singleton {
        INSTANCE;

        @Getter
        private SingletonExample4 singleton;

        /** JVM保证只会执行一次 */
        Singleton() {
            this.singleton = new SingletonExample4();
        }
    }

}
