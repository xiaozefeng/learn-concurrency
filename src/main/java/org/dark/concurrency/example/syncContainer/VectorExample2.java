package org.dark.concurrency.example.syncContainer;

import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.annotations.NotRecommend;
import org.dark.concurrency.annotations.NotThreadSafe;

import java.util.Vector;

/**
 *
 * @author xiaozefeng
 * @date 2018/4/22 上午11:28
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class VectorExample2 {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            vector.add(i);
        }

        new Thread(() -> {
            for (int i = 0; i < vector.size(); i++) {
                vector.remove(i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < vector.size(); i++) {
                vector.get(i);
            }
        }).start();
    }

}
