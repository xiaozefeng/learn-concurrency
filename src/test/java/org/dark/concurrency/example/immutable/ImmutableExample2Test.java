package org.dark.concurrency.example.immutable;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;

@Slf4j
public class ImmutableExample2Test {


    @Test(expected = UnsupportedOperationException.class)
    public void test() {
        Map<Integer, Integer> map = ImmutableExample2.map;
        map.put(1, 4);
        log.info("map:{}", map);
    }

}