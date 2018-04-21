package org.dark.concurrency.example.immutable;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableExample3Test {


    @Test(expected = UnsupportedOperationException.class)
    public void testList() {
        ImmutableExample3.list.add(4);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSet() {
        ImmutableExample3.set.add(4);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMap() {
        ImmutableExample3.map.put(7, 8);
    }
}