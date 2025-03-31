package leetcode.demo;

import lombok.SneakyThrows;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockedQueue<Integer> queue = new BlockedQueue<>(2);
        new Thread(() -> queue.enq(1)).start();
        new Thread(() -> queue.enq(2)).start();
        new Thread(() -> queue.enq(3)).start();
        Thread.sleep(1000);
        new Thread(queue::deq).start();
        new Thread(queue::deq).start();
        new Thread(queue::deq).start();
    }

    public static class BlockedQueue<T> {
        int sizeLimit;
        Queue<T> queue;
        final Lock lock = new ReentrantLock();
        // 条件变量：队列不满
        final Condition notFull = lock.newCondition();
        // 条件变量：队列不空
        final Condition notEmpty = lock.newCondition();

        public BlockedQueue(int size) {
            queue = new ArrayDeque<>(sizeLimit = size);
        }

        // 入队
        @SneakyThrows
        void enq(T x) {
            lock.lock();
            try {
                while (queue.size() == sizeLimit) {
                    // 等待队列不满
                    notFull.await();
                }
                queue.add(x);
                System.out.println("Add " + x);
                //入队后,通知可出队
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }

        // 出队
        @SneakyThrows
        void deq() {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    // 等待队列不空
                    notEmpty.await();
                }
                System.out.println("Value is:" + queue.poll());
                //出队后，通知可入队
                notFull.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
