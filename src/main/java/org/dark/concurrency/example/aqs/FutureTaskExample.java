package org.dark.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * FutureTask学习
 *
 * @author xiaozefeng
 * @date 2018/4/23 下午5:51
 */
@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            log.info("do something in callable");
            Thread.sleep(3000L);
            return "Done";
        });
        executorService.submit(futureTask);
        log.info("do something in main");
        Thread.sleep(1000L);
        String result = futureTask.get();
        log.info("result:{}",result);

    }
}
