package org.dark.concurrency.example.publish;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.annotations.NotRecommend;
import org.dark.concurrency.annotations.NotThreadSafe;

import java.util.Arrays;

/**
 * 不安全的发布对象
 *
 * @author xiaozefeng
 * @date 2018/4/21 下午7:01
 */
@Slf4j
@NotRecommend
@NotThreadSafe
public class UnsafePublish {

    @Getter
    private String[] states = {"a", "b", "c"};


    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        String[] states = unsafePublish.getStates();
        log.info("states:{}",Arrays.toString(states));
        states[0] = "d";
        log.info("states:{}",Arrays.toString(states));

    }

}
