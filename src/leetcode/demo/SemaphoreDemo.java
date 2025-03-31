package leetcode.demo;

import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class SemaphoreDemo {
    static class IntegerPool {
        final List<Integer> pool;
        // 用信号量实现限流器
        final Semaphore semaphore;

        // 构造函数
        IntegerPool(int size) {
            semaphore = new Semaphore(size);
            pool = IntStream
                    .range(0, size)
                    .boxed()
                    .collect(Collectors.toCollection(Vector::new));
        }

        // 利用对象池的对象，调用func
        @SneakyThrows
        void exec() {
            int t = -1;
            semaphore.acquire();
            try {
                t = pool.removeFirst();
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().threadId() + " Value is:" + t + " Time is:" + System.currentTimeMillis());
            } finally {
                if (t != -1) pool.add(t);
                semaphore.release();
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        int size = 3;
        // 创建对象池
        IntegerPool pool = new IntegerPool(size);
        ExecutorService service = Executors.newFixedThreadPool(size << 1);
        for (int i = 0; i < (size << 1); i++)
            service.submit(pool::exec);
        service.close();
    }
}