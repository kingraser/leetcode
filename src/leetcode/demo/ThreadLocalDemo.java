package leetcode.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ThreadLocalDemo {
    public static class SafeDateFormat {
        //定义ThreadLocal变量
        static final ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        static DateFormat get() {
            return threadLocal.get();
        }
    }

    public static void main(String[] args) {
        //不同线程执行下面代码
        //返回的df是不同的
        DateFormat df = SafeDateFormat.get();
    }
}
