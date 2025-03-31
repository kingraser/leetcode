package leetcode.demo;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class ForJoinDemo {

    public static void main(String[] args) {
        //创建分治任务线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        //创建分治任务
        Fibonacci fibonacci = new Fibonacci(30);
        //启动分治任务
        Integer result = forkJoinPool.invoke(fibonacci);
        //输出结果
        System.out.println(result);
        forkJoinPool.close();

        countWordFrequency();
    }

    //递归任务
    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        Fibonacci(int n) {
            this.n = n;
        }

        protected Integer compute() {
            if (n <= 1) return n;
            Fibonacci fibonacci1 = new Fibonacci(n - 1);
            //创建子任务
            fibonacci1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            //等待子任务结果，并合并结果
            return f2.compute() + fibonacci1.join();
        }
    }

    public static void countWordFrequency() {
        String[] fileContent = {"hello world",
                "hello me",
                "hello fork",
                "hello join",
                "fork join in world"};
        //创建ForkJoin线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        //创建任务
        MapReduce mr = new MapReduce(fileContent, 0, fileContent.length);
        //启动任务
        Map<String, Long> result = forkJoinPool.invoke(mr);
        //输出结果
        result.forEach((k, v) -> System.out.println(k + ":" + v));
        forkJoinPool.close();
    }

    //MR模拟类
    public static class MapReduce extends RecursiveTask<Map<String, Long>> {
        private final String[] fileContent;
        private final int start, end;

        //构造函数
        MapReduce(String[] fileContent, int from, int to) {
            this.fileContent = fileContent;
            this.start = from;
            this.end = to;
        }

        @Override
        protected Map<String, Long> compute() {
            if (end - start == 1) return calc(fileContent[start]);
            int mid = (start + end) / 2;
            MapReduce mr1 = new MapReduce(fileContent, start, mid);
            mr1.fork();
            MapReduce mr2 = new MapReduce(fileContent, mid, end);
            //计算子任务，并返回合并的结果
            return merge(mr2.compute(), mr1.join());
        }

        //合并结果
        private Map<String, Long> merge(Map<String, Long> result1, Map<String, Long> result2) {
            //合并结果
            result2.forEach((key, value) -> result1.merge(key, value, Long::sum));
            return result1;
        }

        //统计单词数量
        private Map<String, Long> calc(String line) {
            return Arrays.stream(line.split("\\s+")).collect(Collectors.toMap(s -> s, s -> 1L, Long::sum));
        }
    }
}
