package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class FindSpecialSubstringOfLengthK {
    /*
    You are given a string s and an integer k.
    Determine if there exists a substring of length exactly k in s that satisfies the following conditions:
        The substring consists of only one distinct character (e.g., "aaa" or "bbb").
        If there is a character immediately before the substring, it must be different from the character in the substring.
        If there is a character immediately after the substring, it must also be different from the character in the substring.
    Return true if such a substring exists. Otherwise, return false.

    Example 1:
    Input: s = "aaabaaa", k = 3
    Output: true
    Explanation:
    The substring s[4..6] == "aaa" satisfies the conditions.
        It has a length of 3.
        All characters are the same.
        The character before "aaa" is 'b', which is different from 'a'.
        There is no character after "aaa".

    Example 2:
    Input: s = "abc", k = 2
    Output: false
    Explanation:
    There is no substring of length 2 that consists of one distinct character and satisfies the conditions.

    Constraints:
        1 <= k <= s.length <= 100
        s consists of lowercase English letters only.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, "aaabaaa", 3},
                {false, "abc", 2},
        });
    }

    public boolean hasSpecialSubstring(String s, int k) {
        int count = 1;
        for (int i = 1, prev = s.charAt(0), length = s.length(); i < length; count++)
            if (prev != (prev = s.charAt(i++)))
                if (count == k) return true;
                else count = 0;
        return count == k;
    }
}
