package org.dark.concurrency.example.threadLocal;

/**
 * RequestHolder
 *
 * @author xiaozefeng
 * @date 2018/4/21 下午11:41
 */
public class RequestHolder {
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }
}
