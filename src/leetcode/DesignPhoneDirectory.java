package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class DesignPhoneDirectory {
    /*
    Design a Phone Directory which supports the following operations:
        get: Provide a number which is not assigned to anyone.
        check: Check if a number is available or not.
        release: Recycle or release a number.

    Example:
    // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
    PhoneDirectory directory = new PhoneDirectory(3);
    // It can return any available phone number. Here we assume it returns 0.
    directory.get();
    // Assume it returns 1.
    directory.get();
    // The number 2 is available, so return true.
    directory.check(2);
    // It returns 2, the only number that is left.
    directory.get();
    // The number 2 is no longer available, so return false.
    directory.check(2);
    // Release number 2 back to the pool.
    directory.release(2);
    // Number 2 is available again, return true.
    directory.check(2);
    */

    @Test
    public void test() {
        TestUtil.testEquals(new PhoneDirectory(3),
                new String[]{"get", "get", "check", "get", "check", "release", "check"},
                new Object[]{0, 1, true, 2, false, null, true},
                new Object[][]{{}, {}, {2}, {}, {2}, {2}, {2}}
        );
    }

    public static class PhoneDirectory {
        Set<Integer> used = new HashSet<>();
        Deque<Integer> available = new ArrayDeque<>();
        int max;

        public PhoneDirectory(int maxNumbers) {IntStream.range(0, max = maxNumbers).forEach(available::add);}

        public int get() {
            if (available.isEmpty()) return -1;
            int val = available.pollFirst();
            used.add(val);
            return val;
        }

        public boolean check(int number) {
            return number >= 0 && number < max && !used.contains(number);
        }

        public void release(int number) {
            if (used.remove(number)) available.addFirst(number);
        }
    }
}
