package org.dark.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 不可变对象
 *
 * @author xiaozefeng
 * @date 2018/4/21 下午10:28
 */
@Slf4j
public class ImmutableExample3 {
    public final static List<Integer> list = ImmutableList.of(1, 2, 3);

    public final static ImmutableSet set = ImmutableSet.copyOf(list);

    public final static ImmutableMap<Integer, Integer> map = ImmutableMap.<Integer,Integer>builder()
            .put(1, 2)
            .put(3, 4)
            .put(5, 6)
            .build();




}
