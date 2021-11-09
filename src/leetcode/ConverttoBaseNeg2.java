package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ConverttoBaseNeg2 {
    /*
    Given an integer n, return a binary string representing its representation in base -2.
    Note that the returned string should not have leading zeros unless the string is "0".

    Example 1:
    Input: n = 2
    Output: "110"
    Explanation: (-2)^2 + (-2)^1 = 2

    Example 2:
    Input: n = 3
    Output: "111"
    Explanation: (-2)^2 + (-2)^1 + (-2)^0 = 3

    Example 3:
    Input: n = 4
    Output: "100"
    Explanation: (-2)^2 = 4

    Constraints:
    0 <= n <= 10^9
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{{"110", 2}, {"111", 3}, {"100", 4}});
    }

    public String baseNeg2(int n) {
        int length = 32, result[] = new int[length], index;
        for (index = length; n != 0; n = -(n >> 1)) result[--index] = '0' + (n & 1);
        return index == length ? "0" : new String(result, index, length - index);
    }
}
