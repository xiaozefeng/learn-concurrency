package org.dark.concurrency.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.annotations.NotThreadSafe;

import java.util.Map;

/**
 * 不可变对象
 *
 * @author xiaozefeng
 * @date 2018/4/21 下午10:28
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;

    private final static String b = "2";

    /**
     * 不安全的
     */
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
    }

    public static void main(String[] args) {
        // 编译错误
        //a = 2;
        //b = "3";
        //map = new HashMap<>();
        map.put(1, 4);
        log.info("map:{}",map);
    }

    public void test(final int a) {
        // 编译报错
        //a = 1;
    }
}
