package leetcode.demo;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {
    public static void main(String[] args) {
        Cache cache = new Cache();
        cache.put(1, 1);
        new Thread(() -> cache.get(1)).start();
        new Thread(() -> cache.put(1, 100)).start();
    }

    public static class Cache {
        final StampedLock stampedLock = new StampedLock();
        final Map<Integer, Integer> map = new HashMap<>();

        @SneakyThrows
        public Integer get(Integer key) {
            // 乐观读
            long stamp = stampedLock.tryOptimisticRead();
            // 读入方法局部变量
            Thread.sleep(1000);
            Integer value = map.get(key);
            // 校验stamp
            if (stampedLock.validate(stamp)) return value;
            System.out.println("Try read lock!");
            // 升级为悲观读锁
            stamp = stampedLock.readLock();
            try {
                Integer result = map.get(key);
                System.out.println("get value:" + result);
                return result;
            } finally {
                //释放悲观读锁
                stampedLock.unlockRead(stamp);
            }
        }

        public void put(Integer key, Integer value) {
            long stamp = stampedLock.writeLock();
            try {
                map.put(key, value);
            } finally {
                stampedLock.unlockWrite(stamp);
            }
        }
    }
}
