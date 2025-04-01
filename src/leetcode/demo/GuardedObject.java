package leetcode.demo;

import lombok.SneakyThrows;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class GuardedObject<T> {
    //受保护的对象
    T guardedObject;
    final Lock lock = new ReentrantLock();
    final Condition done = lock.newCondition();
    final int timeout = 2;
    //保存所有GuardedObject


    //获取受保护对象
    @SneakyThrows
    T get(Predicate<T> isValid) {
        lock.lock();
        try {
            //MESA管程推荐写法
            while (!isValid.test(guardedObject)) done.await(timeout, TimeUnit.SECONDS);
        } finally {
            lock.unlock();
        }
        //返回非空的受保护对象
        return guardedObject;
    }

    T get() {
        return get(Objects::nonNull);
    }

    //事件通知方法
    void onChanged(T guardedObject) {
        lock.lock();
        try {
            this.guardedObject = guardedObject;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("test start!");
        GuardedObject<String> guardedObject = new GuardedObject<>();
        new Thread(() -> System.out.println(guardedObject.get())).start();
        new Thread(() -> System.out.println(guardedObject.get())).start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            guardedObject.onChanged("sun");
        }).start();

    }
}
