package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class SecondLargestDigitinaString {
    /*
    Given an alphanumeric string s, return the second largest numerical digit that appears in s, or -1 if it does not exist.
    An alphanumeric string is a string consisting of lowercase English letters and digits.

    Example 1:
    Input: s = "dfa12321afd"
    Output: 2
    Explanation: The digits that appear in s are [1, 2, 3]. The second largest digit is 2.

    Example 2:
    Input: s = "abc1111"
    Output: -1
    Explanation: The digits that appear in s are [1]. There is no second largest digit.

    Constraints:
    1 <= s.length <= 500
    s consists of only lowercase English letters and/or digits.
    */
    public int secondHighest(String s) {
        int digit, max = -1, second = -1;
        for (char c : s.toCharArray())
            if ((digit = c - '0') > 9 || digit == max) continue;
            else if (digit > max) {
                second = max;
                max = digit;
            } else if (digit > second) second = digit;
        return second;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, secondHighest("a12321b"));
    }
}
