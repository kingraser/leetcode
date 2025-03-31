package leetcode.demo;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        Cache<Integer> cache = new Cache<>();
        new Thread(() -> cache.get(1)).start();
        new Thread(() -> cache.get(1)).start();
        new Thread(() -> cache.get(1)).start();
    }

    public static class Cache<K> {
        Random random = new Random();
        final Map<K, Integer> m = new HashMap<>();
        final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        final Lock readLock = readWriteLock.readLock();
        final Lock writeLock = readWriteLock.writeLock();

        @SneakyThrows
        public Integer get(K key) {
            System.out.println(Thread.currentThread().threadId() + " get key:" + key + ", at time:" + System.currentTimeMillis());
            Integer v;
            //读缓存
            readLock.lock();
            try {
                v = m.get(key);
            } finally {
                readLock.unlock();
            }
            //缓存中存在，返回
            if (v != null) {
                System.out.println(Thread.currentThread().threadId() + " got key:" + key + ", at time:" + System.currentTimeMillis());
                return v;
            }
            //缓存中不存在，查询数据库
            writeLock.lock();
            try {
                //再次验证
                //其他线程可能已经查询过数据库
                v = m.get(key);
                if (v == null) {
                    //查询数据库
                    Thread.sleep(1000);
                    v = random.nextInt(100);
                    m.put(key, v);
                }
            } finally {
                writeLock.unlock();
            }
            System.out.println(Thread.currentThread().threadId() + " got key:" + key + ", at time:" + System.currentTimeMillis());
            return v;
        }
    }
}
