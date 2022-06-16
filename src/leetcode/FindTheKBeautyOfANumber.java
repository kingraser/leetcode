package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindTheKBeautyOfANumber {
    /*
    The k-beauty of an integer num is defined as the number of substrings of num when it is read as a string that meet the following conditions:
    It has a length of k.
    It is a divisor of num.
    Given integers num and k, return the k-beauty of num.
    Note:
    Leading zeros are allowed.
    0 is not a divisor of any value.
    A substring is a contiguous sequence of characters in a string.

    Example 1:
    Input: num = 240, k = 2
    Output: 2
    Explanation: The following are the substrings of num of length k:
    - "24" from "240": 24 is a divisor of 240.
    - "40" from "240": 40 is a divisor of 240.
    Therefore, the k-beauty is 2.

    Example 2:
    Input: num = 430043, k = 2
    Output: 2
    Explanation: The following are the substrings of num of length k:
    - "43" from "430043": 43 is a divisor of 430043.
    - "30" from "430043": 30 is not a divisor of 430043.
    - "00" from "430043": 0 is not a divisor of 430043.
    - "04" from "430043": 4 is not a divisor of 430043.
    - "43" from "430043": 43 is a divisor of 430043.
    Therefore, the k-beauty is 2.

    Constraints:
    1 <= num <= 10^9
    1 <= k <= num.length (taking num as a string)
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, 240, 2},
                {2, 430043, 2}
        });
    }

    static int[] pow10 = new int[]{1, 10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000};

    public int divisorSubstrings(int num, int k) {
        if (k == 10) return 1;
        int result = 0, mask = pow10[k], limit = pow10[--k];
        for (int n = num, divisor; n >= limit; n /= 10)
            if ((divisor = n % mask) != 0 && num % divisor == 0) result++;
        return result;
    }

}
