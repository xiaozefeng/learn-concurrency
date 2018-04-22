package org.dark.concurrency.example.syncContainer;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @author xiaozefeng
 * @date 2018/4/22 上午11:28
 */
@Slf4j
public class VectorExample3 {
    /**
     * java.util.ConcurrentModificationException
     *
     * @param list
     */
    private static void test1(List<Integer> list) {
        for (Integer integer : list) {
            if (integer.equals(3)) {
                list.remove(integer);
            }
        }

    }

    /**
     * java.util.ConcurrentModificationException
     *
     * @param list
     */
    private static void test2(List<Integer> list) {
        //list.removeIf(next -> next.equals(3));
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next.equals(3)) {
                list.remove(next);
            }
        }
    }

    /**
     * success
     *
     * @param list
     */
    private static void test3(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(3)) {
                list.remove(i);
            }
        }
    }


    /**
     * success
     *
     * @param list
     */
    private static void test4(List<Integer> list) {
        list.removeIf(next -> next.equals(3));
    }


    public static void main(String[] args) {
        List<Integer> list = new Vector<>();
        list.add(1);
        list.add(2);
        list.add(3);

        test4(list);
    }
}
