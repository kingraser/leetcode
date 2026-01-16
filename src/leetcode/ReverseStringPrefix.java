package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class ReverseStringPrefix {
    /*
    You are given a string s and an integer k.
    Reverse the first k characters of s and return the resulting string.

    Example 1:
    Input: s = "abcd", k = 2
    Output: "bacd"
    Explanation:
    The first k = 2 characters "ab" are reversed to "ba". The final resulting string is "bacd".

    Example 2:
    Input: s = "xyz", k = 3
    Output: "zyx"
    Explanation:
    The first k = 3 characters "xyz" are reversed to "zyx". The final resulting string is "zyx".

    Example 3:
    Input: s = "hey", k = 1
    Output: "hey"
    Explanation:
    The first k = 1 character "h" remains unchanged on reversal. The final resulting string is "hey".

    Constraints:
        1 <= s.length <= 100
        s consists of lowercase English letters.
        1 <= k <= s.length
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"bacd", "abcd", 2},
                {"zyx", "xyz", 3},
                {"hey", "hey", 1},
        });
    }

    public String reversePrefix(String s, int k) {
        char[] result = s.toCharArray();
        for (int left = 0, right = k - 1; left < right; left++, right--) {
            result[right] ^= (result[left] ^= result[right]);
            result[left] ^= result[right];
        }
        return new String(result);
    }
}
