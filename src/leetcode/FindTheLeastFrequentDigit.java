package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class FindTheLeastFrequentDigit {
    /*
    Given an integer n, find the digit that occurs least frequently in its decimal representation.
    If multiple digits have the same frequency, choose the smallest digit.

    Return the chosen digit as an integer.
    The frequency of a digit x is the number of times it appears in the decimal representation of n.

    Example 1:
    Input: n = 1553322
    Output: 1
    Explanation:
    The least frequent digit in n is 1, which appears only once. All other digits appear twice.

    Example 2:
    Input: n = 723344511
    Output: 2
    Explanation:
    The least frequent digits in n are 7, 2, and 5; each appears only once.

    Constraints:
        1 <= n <= 2^31 - 1
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1, 1553322}, {2, 723344511},
                });
    }

    public int getLeastFrequentDigit(int n) {
        int count[] = new int[10], result = -1, max = 33;
        for (; n != 0; n /= 10) count[n % 10]++;
        for (int digit = 0; digit < 10; digit++)
            if (count[digit] > 0 && count[digit] < max) {
                max = count[digit];
                result = digit;
            }
        return result;
    }
}
