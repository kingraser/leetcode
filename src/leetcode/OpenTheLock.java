package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;

/**
 * @author Wit
 */
public class OpenTheLock {
    /*
    You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
    The lock initially starts at '0000', a string representing the state of the 4 wheels.
    You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
    Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

    Example 1:
    Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
    Output: 6
    Explanation:
    A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
    Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
    because the wheels of the lock become stuck after the display becomes the dead end "0102".

    Example 2:
    Input: deadends = ["8888"], target = "0009"
    Output: 1
    Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".

    Example 3:
    Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
    Output: -1
    Explanation: We cannot reach the target without getting stuck.

    Constraints:
    1 <= deadends.length <= 500
    deadends[i].length == 4
    target.length == 4
    target will not be in the list deadends.
    target and deadends[i] consist of digits only.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {5, new String[]{"2111", "2202", "2210", "0201", "2210"}, "2201"},
                {6, new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"},
                {1, new String[]{"8888"}, "0009"},
                {-1, new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"}, "8888"}
        });
    }

    static final int SIZE = 10_000;

    public int openLock(String[] deadEnds, String target) {
        int step = 0, targetInt = Integer.parseInt(target), reached[] = new int[SIZE];
        if (targetInt == 0) return 0;
        for (String deadEnd : deadEnds) reached[Integer.parseInt(deadEnd)]++;
        if (reached[targetInt]++ > 0) return -1;
        Set begins = new Set(SIZE) {{add(0);}}, ends = new Set(SIZE) {{add(targetInt);}};
        for (reached[0]++; !begins.deque.isEmpty() && !ends.deque.isEmpty(); step++) {
            for (int size = begins.deque.size(), value, digit, times, pow10, newValue; size-- > 0; )
                for (value = begins.pop(), times = 4, pow10 = 1; times-- > 0; pow10 *= 10) {
                    if (ends.array[newValue = value + (((digit = (value / pow10) % 10) + 9) % 10 - digit) * pow10] > 0) return ++step;
                    if (reached[newValue]++ == 0) begins.add(newValue);
                    if (ends.array[newValue = value + ((digit + 11) % 10 - digit) * pow10] > 0) return ++step;
                    if (reached[newValue]++ == 0) begins.add(newValue);
                }
            if (ends.deque.size() < begins.deque.size()) { // search from smaller set
                Set tmp = begins;
                begins = ends;
                ends = tmp;
            }
        }
        return -1;
    }

    public static class Set {
        int array[], v;
        ArrayDeque<Integer> deque;

        public Set(int n) {
            this.array = new int[n];
            deque = new ArrayDeque<>(n);
        }

        public void add(int v) {
            array[v]++;
            deque.addLast(v);
        }

        public int pop() {
            array[v = deque.pollFirst()] = 0;
            return v;
        }
    }
}
