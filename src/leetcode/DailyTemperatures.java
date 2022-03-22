package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class DailyTemperatures {
    /*
    Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

    Example 1:
    Input: temperatures = [73,74,75,71,69,72,76,73]
    Output: [1,1,4,2,1,1,0,0]

    Example 2:
    Input: temperatures = [30,40,50,60]
    Output: [1,1,1,0]

    Example 3:
    Input: temperatures = [30,60,90]
    Output: [1,1,0]

    Constraints:
    1 <= temperatures.length <= 10^5
    30 <= temperatures[i] <= 100
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[]{1, 1, 4, 2, 1, 1, 0, 0}, new int[]{73, 74, 75, 71, 69, 72, 76, 73}},
                {new int[]{1, 1, 1, 0}, new int[]{30, 40, 50, 60}},
                {new int[]{1, 1, 0}, new int[]{30, 60, 90}}
        });
    }

    public int[] dailyTemperatures(int[] temps) {
        int result[] = new int[temps.length];
        for (int day = 0, stack[] = new int[temps.length], idx = 0, prev; day < temps.length; stack[idx++] = day++)
            while (idx > 0 && temps[stack[idx - 1]] < temps[day]) result[prev = stack[--idx]] = day - prev;
        return result;
    }
}
