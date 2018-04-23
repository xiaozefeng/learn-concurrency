package org.dark.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Fork Join 学习
 *
 * @author xiaozefeng
 * @date 2018/4/23 下午5:59
 */
@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer> {

    private final static int threshold = 2;

    private int start;

    private int end;

    public ForkJoinTaskExample(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阀值，就分裂成两个子任务去计算
            int middle = (start + end) / 2;
            ForkJoinTaskExample leftTask = new ForkJoinTaskExample(start, middle);
            ForkJoinTaskExample rightTask = new ForkJoinTaskExample(middle + 1, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待任务完成并获取结果
            Integer leftResult = leftTask.join();
            Integer rightResult = rightTask.join();

            // 合并结果
            sum = leftResult + rightResult;

        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // 生成一个计算任务
        ForkJoinTaskExample task = new ForkJoinTaskExample(1, 10000);

        // 提交执行任务
        ForkJoinTask<Integer> result = forkJoinPool.submit(task);

        try {
            log.info("{}", result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
