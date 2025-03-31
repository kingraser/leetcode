package leetcode.demo;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        //任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("T1:洗水壶...");
                    sleep(1000);
                    System.out.println("T1:烧开水...");
                    sleep(5000);
                });
        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("T2:洗茶壶...");
                    sleep(1000);
                    System.out.println("T2:洗茶杯...");
                    sleep(2000);
                    System.out.println("T2:拿茶叶...");
                    sleep(1000);
                    return "龙井";
                });
        //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 =
                f1.thenCombine(f2, (__, tf) -> {
                    System.out.println("T1:拿到茶叶:" + tf);
                    System.out.println("T1:泡茶...");
                    return "上茶:" + tf;
                });
        //等待任务3执行结果
        System.out.println(f3.join());
    }

    @SneakyThrows
    public static void sleep(int millis) {
        Thread.sleep(millis);
    }
    // 一次执行结果：
    // T1:洗水壶...
    // T2:洗茶壶...
    // T1:烧开水...
    // T2:洗茶杯...
    // T2:拿茶叶...
    // T1:拿到茶叶:龙井
    // T1:泡茶...
    // 上茶:龙井
}
