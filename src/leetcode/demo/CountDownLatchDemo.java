package leetcode.demo;

import lombok.SneakyThrows;

import java.util.concurrent.*;

public class CountDownLatchDemo {
    @SneakyThrows
    public static void main(String[] args) {
        // 计数器初始化为2
        CountDownLatch latch = new CountDownLatch(2);
        try (ExecutorService executor = Executors.newFixedThreadPool(2)/*创建2个线程的线程池*/) {
            long start = System.currentTimeMillis();
            executor.execute(() -> sleep(1000, latch));
            executor.execute(() -> sleep(2000, latch));
            latch.await();
            long end = System.currentTimeMillis();
            System.out.println("Time cost: " + (end - start));
        }
    }

    @SneakyThrows
    static void sleep(long millis, CountDownLatch latch) {
        TimeUnit.MILLISECONDS.sleep(millis);
        latch.countDown();
    }
}
