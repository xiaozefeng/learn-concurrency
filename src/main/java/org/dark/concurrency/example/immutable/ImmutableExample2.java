package org.dark.concurrency.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * 不可变对象
 *
 * @author xiaozefeng
 * @date 2018/4/21 下午10:28
 */
@Slf4j
public class ImmutableExample2 {


    /**
     * 不安全的
     */
    public   static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1, 4);
        // 报错
        log.info("map:{}",map);
    }

}
