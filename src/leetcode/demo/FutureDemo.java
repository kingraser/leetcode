package leetcode.demo;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureDemo {
    @SneakyThrows
    public static void main(String[] args) {
        // 创建任务T2的FutureTask
        FutureTask<String> futureTask2 = new FutureTask<>(new T2Task());
        // 创建任务T1的FutureTask
        FutureTask<String> futureTask1 = new FutureTask<>(new T1Task(futureTask2));
        // 线程T1执行任务ft1
        Thread T1 = new Thread(futureTask1);
        T1.start();
        // 线程T2执行任务ft2
        Thread T2 = new Thread(futureTask2);
        T2.start();
        // 等待线程T1执行结果
        System.out.println(futureTask1.get());
        // 一次执行结果：
        //    T1:洗水壶...
        //    T2:洗茶壶...
        //    T1:烧开水...
        //    T2:洗茶杯...
        //    T2:拿茶叶...
        //    T1:拿到茶叶:龙井
        //    T1:泡茶...
        //    上茶:龙井
    }


    // T1Task需要执行的任务：
    // 洗水壶、烧开水、泡茶
    public static class T1Task implements Callable<String> {
        FutureTask<String> futureTask2;

        // T1任务需要T2任务的FutureTask
        T1Task(FutureTask<String> futureTask2) {
            this.futureTask2 = futureTask2;
        }

        @Override
        public String call() throws Exception {
            System.out.println("T1:洗水壶...");
            Thread.sleep(1000);
            System.out.println("T1:烧开水...");
            Thread.sleep(4000);
            // 获取T2线程的茶叶
            String tea = futureTask2.get();
            System.out.println("T1:拿到茶叶:" + tea);
            System.out.println("T1:泡茶...");
            return "上茶:" + tea;
        }
    }

    // T2Task需要执行的任务:
// 洗茶壶、洗茶杯、拿茶叶
    public static class T2Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("T2:洗茶壶...");
            Thread.sleep(1000);
            System.out.println("T2:洗茶杯...");
            Thread.sleep(2000);
            System.out.println("T2:拿茶叶...");
            Thread.sleep(1000);
            return "龙井";
        }
    }
}
