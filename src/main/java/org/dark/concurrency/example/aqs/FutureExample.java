package org.dark.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future 学习
 *
 * @author xiaozefeng
 * @date 2018/4/23 下午5:44
 */
@Slf4j
public class FutureExample {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(() -> {
            log.info("do something in callable");
            try {
                Thread.sleep(3000L);
                log.info("do something Done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Done";

        });

        log.info("do something in main");

        Thread.sleep(1000L);

        String result = future.get();
        log.info("result:{}",result);
        executorService.shutdown();

    }
}
