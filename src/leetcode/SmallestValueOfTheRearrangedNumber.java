package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SmallestValueOfTheRearrangedNumber {
    /*
    You are given an integer num. Rearrange the digits of num such that its value is minimized and it does not contain any leading zeros.
    Return the rearranged number with minimal value.
    Note that the sign of the number does not change after rearranging the digits.

    Example 1:
    Input: num = 310
    Output: 103
    Explanation: The possible arrangements for the digits of 310 are 013, 031, 103, 130, 301, 310.
    The arrangement with the smallest value that does not contain any leading zeros is 103.

    Example 2:
    Input: num = -7605
    Output: -7650
    Explanation: Some possible arrangements for the digits of -7605 are -7650, -6705, -5076, -0567.
    The arrangement with the smallest value that does not contain any leading zeros is -7650.

    Constraints:
    -10^15 <= num <= 10^15
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {-7650L, -7605},
                {103L, 310}});
    }

    public long smallestNumber(long num) {
        long result = 0, flag = num < 0 ? -1 : 1;
        int[] digits = new int[10];
        for (num = flag == -1 ? -num : num; num != 0; num /= 10) digits[(int) (num % 10)]++;
        if (flag == -1) for (int i = 9; i >= 0; i--) while (digits[i]-- > 0) result = result * 10 + i;
        else {
            for (int i = 1; i < 10; i++)
                if (digits[i] > 0) {
                    result = i;
                    digits[i]--;
                    break;
                }
            for (int i = 0; i < 10; i++) while (digits[i]-- > 0) result = result * 10 + i;
        }
        return flag * result;
    }
}
