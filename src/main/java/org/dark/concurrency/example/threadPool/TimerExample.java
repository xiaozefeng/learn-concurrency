package org.dark.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer
 *
 * @author xiaozefeng
 * @date 2018/4/23 下午8:38
 */
@Slf4j
public class TimerExample {

    public static void main(String[] args) {
        Timer timer = new Timer();
        // 从当前时间开始，每隔2秒执行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer run");
            }
        }, new Date(), 2 * 1000);
    }
}
