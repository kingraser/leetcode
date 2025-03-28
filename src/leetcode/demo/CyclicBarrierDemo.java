package leetcode.demo;

import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        checkAll(new CyclicBarrier(2, () -> System.out.println("A round done!")));
    }

    static void checkAll(CyclicBarrier barrier) {
        new Thread(() -> {
            for (int i = 0; i < 2; i++) sleep(500, barrier);
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 2; i++) sleep(1000, barrier);
        }).start();
    }

    @SneakyThrows
    static void sleep(long millis, CyclicBarrier barrier) {
        TimeUnit.MILLISECONDS.sleep(millis);
        barrier.await();
    }
}
