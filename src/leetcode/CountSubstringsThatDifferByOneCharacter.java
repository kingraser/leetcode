package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class CountSubstringsThatDifferByOneCharacter {
    /*
    Given two strings s and t, find the number of ways you can choose a non-empty substring of s and replace a single character by a different character such that the resulting substring is a substring of t.
    In other words, find the number of substrings in s that differ from some substring in t by exactly one character.
    For example, the underlined substrings in "computer" and "computation" only differ by the 'e'/'a', so this is a valid way.
    Return the number of substrings that satisfy the condition above.
    A substring is a contiguous sequence of characters within a string.

    Example 1:
    Input: s = "aba", t = "baba"
    Output: 6
    Explanation: The following are the pairs of substrings from s and t that differ by exactly 1 character:
    ("aba", "baba")
    ("aba", "baba")
    ("aba", "baba")
    ("aba", "baba")
    ("aba", "baba")
    ("aba", "baba")
    The underlined portions are the substrings that are chosen from s and t.

    Example 2:
    Input: s = "ab", t = "bb"
    Output: 3
    Explanation: The following are the pairs of substrings from s and t that differ by 1 character:
    ("ab", "bb")
    ("ab", "bb")
    ("ab", "bb")
    The underlined portions are the substrings that are chosen from s and t.

    Constraints:
        1 <= s.length, t.length <= 100
        s and t consist of lowercase English letters only.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {6, "aba", "baba"},
                {3, "ab", "bb"},
        });
    }

    public int countSubstrings(String s, String t) {
        char[] sChars = s.toCharArray(), tt = t.toCharArray();
        int result = 0, sLength = s.length(), tLength = t.length(),
                same[][] = new int[sLength + 1][tLength + 1], // number of exact same substrings ending at s[i] and t[j].
                diff1[][] = new int[sLength + 1][tLength + 1]; // number of substrings having 1 different character ending at s[i] and t[j].
        for (int i = 1; i <= sLength; i++)
            for (int j = 1; j <= tLength; result += diff1[i][j++])
                if (sChars[i - 1] == tt[j - 1]) {
                    same[i][j] = same[i - 1][j - 1] + 1;
                    diff1[i][j] = diff1[i - 1][j - 1];
                } else diff1[i][j] = same[i - 1][j - 1] + 1;
        return result;
    }
}
