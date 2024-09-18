package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class CountSubstringsThatSatisfyKConstraintI {
    /*
    You are given a binary string s and an integer k.
    A binary string satisfies the k-constraint if either of the following conditions holds:
        The number of 0's in the string is at most k.
        The number of 1's in the string is at most k.
    Return an integer denoting the number of substrings of s that satisfy the k-constraint.

    Example 1:
    Input: s = "10101", k = 1
    Output: 12
    Explanation:
    Every substring of s except the substrings "1010", "10101", and "0101" satisfies the k-constraint.

    Example 2:
    Input: s = "1010101", k = 2
    Output: 25
    Explanation:
    Every substring of s except the substrings with a length greater than 5 satisfies the k-constraint.

    Example 3:
    Input: s = "11111", k = 1
    Output: 15
    Explanation:
    All substrings of s satisfy the k-constraint.

    Constraints:
        1 <= s.length <= 50
        1 <= k <= s.length
        s[i] is either '0' or '1'.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {12, "10101", 1},
                {25, "1010101", 2},
                {15, "11111", 1},
        });
    }

    public int countKConstraintSubstrings(String s, int k) {
        int result = 0;
        for (int left = 0, right = 0, length = s.length(), zeroCount = 0, oneCount = 0; right < length; result += right - left) {
            if (s.charAt(right++) == '0') zeroCount++;
            else oneCount++;
            while (zeroCount > k && oneCount > k)
                if (s.charAt(left++) == '0') zeroCount--;
                else oneCount--;
        }
        return result;
    }
}