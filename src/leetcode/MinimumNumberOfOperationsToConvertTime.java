package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimumNumberOfOperationsToConvertTime {
    /*
    You are given two strings current and correct representing two 24-hour times.
    24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00 and 59. The earliest 24-hour time is 00:00, and the latest is 23:59.
    In one operation you can increase the time current by 1, 5, 15, or 60 minutes. You can perform this operation any number of times.
    Return the minimum number of operations needed to convert current to correct.

    Example 1:
    Input: current = "02:30", correct = "04:35"
    Output: 3
    Explanation:
    We can convert current to correct in 3 operations as follows:
    - Add 60 minutes to current. current becomes "03:30".
    - Add 60 minutes to current. current becomes "04:30".
    - Add 5 minutes to current. current becomes "04:35".
    It can be proven that it is not possible to convert current to correct in fewer than 3 operations.

    Example 2:
    Input: current = "11:00", correct = "11:01"
    Output: 1
    Explanation: We only have to add one minute to current, so the minimum number of operations needed is 1.

    Constraints:
    current and correct are in the format "HH:MM"
    current <= correct
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {3, "02:30", "04:35"},
                {1, "11:00", "11:01"}
        });
    }

    static final int[] STEPS = new int[]{15, 5, 1};

    public int convertTime(String current, String correct) {
        int result = getDiff(current, correct, 0), minutes = getDiff(current, correct, 3);
        if (minutes < 0 && result-- > 0) minutes += 60;
        for (int i = 0; i < STEPS.length; minutes %= STEPS[i++]) result += minutes / STEPS[i];
        return result;
    }

    int getDiff(String current, String correct, int index) {
        return (correct.charAt(index) - current.charAt(index++)) * 10 + correct.charAt(index) - current.charAt(index);
    }
}
