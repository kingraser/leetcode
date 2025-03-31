package leetcode.demo;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionServiceDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 创建CompletionService
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        // 异步向电商S1询价
        cs.submit(() -> {
            Thread.sleep(1000);
            return 1;
        });
        // 异步向电商S2询价
        cs.submit(() -> {
            Thread.sleep(2000);
            return 2;
        });
        // 异步向电商S3询价
        cs.submit(() -> {
            Thread.sleep(3000);
            return 3;
        });
        // 将询价结果异步保存到数据库
        for (int i = 0; i < 3; i++) {
            Integer r = cs.take().get();
            System.out.println("result is:" + r);
        }
        executor.close();
    }
}
