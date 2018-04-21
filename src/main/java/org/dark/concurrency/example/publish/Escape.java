package org.dark.concurrency.example.publish;

import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.annotations.NotRecommend;
import org.dark.concurrency.annotations.NotThreadSafe;

/**
 * 对象溢出
 *
 * @author xiaozefeng
 * @date 2018/4/21 下午7:05
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass{
        public InnerClass() {
            log.info("{}", Escape.this.thisCanEscape);
        }


    }

    public static void main(String[] args) {
        new Escape();
    }


}
