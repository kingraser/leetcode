package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class LargestNumberAfterDigitSwapsByParity {
    /*
    You are given a positive integer num. You may swap any two digits of num that have the same parity (i.e. both odd digits or both even digits).
    Return the largest possible value of num after any number of swaps.

    Example 1:
    Input: num = 1234
    Output: 3412
    Explanation: Swap the digit 3 with the digit 1, this results in the number 3214.
    Swap the digit 2 with the digit 4, this results in the number 3412.
    Note that there may be other sequences of swaps but it can be shown that 3412 is the largest possible number.
    Also note that we may not swap the digit 4 with the digit 1 since they are of different parities.

    Example 2:
    Input: num = 65875
    Output: 87655
    Explanation: Swap the digit 8 with the digit 6, this results in the number 85675.
    Swap the first digit 5 with the digit 7, this results in the number 87655.
    Note that there may be other sequences of swaps but it can be shown that 87655 is the largest possible number.

    Constraints:
    1 <= num <= 10^9
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {427, 247},
                {3412, 1234},
                {87655, 65875}
        });
    }

    public int largestInteger(int num) {
        int result = 0, digits[] = new int[10], size = 0, parity[] = new int[10];
        for (int val; num > 0; num /= 10) {
            digits[val = num % 10]++;
            parity[size++] = val & 1;
        }
        for (int oddMax = 9, evenMax = 8; size-- > 0; )
            if (parity[size] == 0) {
                while (evenMax >= 0 && digits[evenMax]-- == 0) evenMax -= 2;
                result = result * 10 + evenMax;
            } else {
                while (oddMax >= 0 && digits[oddMax]-- == 0) oddMax -= 2;
                result = result * 10 + oddMax;
            }
        return result;
    }
}
