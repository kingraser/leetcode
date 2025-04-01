package leetcode.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;

public class SimpleLimiterDemo {
    static volatile long prev;

    public static void main(String[] args) {
        //限流器流速：2个请求/秒
        SimpleLimiter limiter = new SimpleLimiter();
        //执行任务的线程池
        ExecutorService es = Executors.newFixedThreadPool(1);
        //记录上一次执行时间
        prev = System.nanoTime();
        //测试执行20次
        for (int i = 0; i < 20; i++) {
            //限流器限流
            limiter.acquire();
            //提交任务异步执行
            es.execute(() -> {
                long cur = System.nanoTime();
                //打印时间间隔：毫秒
                System.out.println((cur - prev) / 1000_000);
                prev = cur;
            });
        }
        es.close();

        // 输出结果：
        // 1005
        // 995
        // 1000
        // 994
        // 1003
    }

    public static class SimpleLimiter {
        //当前令牌桶中的令牌数量
        long storedPermits = 0;
        //令牌桶的容量
        long maxPermits = 3;
        //下一令牌产生时间
        long next = System.nanoTime();
        //发放令牌间隔：纳秒
        long interval = 1000_000_000;

        //请求时间在下一令牌产生时间之后,则
        // 1.重新计算令牌桶中的令牌数
        // 2.将下一个令牌发放时间重置为当前时间
        void resync(long now) {
            if (now <= next) return;
            //新产生的令牌数
            long newPermits = (now - next) / interval;
            //新令牌增加到令牌桶
            storedPermits = Math.min(maxPermits, storedPermits + newPermits);
            //将下一个令牌发放时间重置为当前时间
            next = now;
        }

        //预占令牌，返回能够获取令牌的时间
        synchronized long reserve(long now) {
            resync(now);
            //能够获取令牌的时间
            long avaliableTime = next;
            //令牌桶中能提供的令牌
            long fb = Math.min(1, storedPermits);
            //令牌净需求：首先减掉令牌桶中的令牌
            long nr = 1 - fb;
            //重新计算下一令牌产生时间
            next = next + nr * interval;
            //重新计算令牌桶中的令牌
            this.storedPermits -= fb;
            return avaliableTime;
        }

        //申请令牌
        @SneakyThrows
        void acquire() {
            //申请令牌时的时间
            long now = System.nanoTime();
            //预占令牌
            long at = reserve(now);
            long waitTime = Math.max(at - now, 0);
            //按照条件等待
            if (waitTime > 0) {
                TimeUnit.NANOSECONDS.sleep(waitTime);
            }
        }
    }
}
