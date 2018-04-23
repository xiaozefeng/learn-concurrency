package org.dark.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务
 *
 * @author xiaozefeng
 * @date 2018/4/23 下午8:34
 */
@Slf4j
public class ScheduleExample {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);


        // 延迟1 秒后，每隔2秒执行
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.warn("schedule run");
        }, 1, 2, TimeUnit.SECONDS);

        //scheduledExecutorService.shutdown();
    }
}
