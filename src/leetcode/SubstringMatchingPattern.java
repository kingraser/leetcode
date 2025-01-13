package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class SubstringMatchingPattern {
    /*
    You are given a string s and a pattern string p, where p contains exactly one '*' character.
    The '*' in p can be replaced with any sequence of zero or more characters.
    Return true if p can be made a substring of s, and false otherwise.

    Example 1:
    Input: s = "leetcode", p = "ee*e"
    Output: true
    Explanation:
    By replacing the '*' with "tcod", the substring "eetcode" matches the pattern.

    Example 2:
    Input: s = "car", p = "c*v"
    Output: false
    Explanation:
    There is no substring matching the pattern.

    Example 3:
    Input: s = "luck", p = "u*"
    Output: true
    Explanation:
    The substrings "u", "uc", and "uck" match the pattern.

    Constraints:
        1 <= s.length <= 50
        1 <= p.length <= 50
        s contains only lowercase English letters.
        p contains only lowercase English letters and exactly one '*'
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, "leetcode", "ee*e"},
                {false, "car", "c*v"},
                {true, "luck", "u*"},
                {true, "kvb", "k*v"},
        });
    }

    public boolean hasMatch(String s, String p) {
        int starIndex = p.indexOf('*');
        if (starIndex == -1) return s.contains(p);
        else if (starIndex == 0) return p.length() == 1 || s.contains(p.substring(1));
        else if (starIndex == p.length() - 1) return s.contains(p.substring(0, p.length() - 1));
        String first = p.substring(0, starIndex), second = p.substring(starIndex + 1);
        for (int firstIndex = -1; ; ) {
            firstIndex = s.indexOf(first, firstIndex + 1);
            if (firstIndex == -1) return false;
            if (s.indexOf(second, firstIndex + first.length()) != -1) return true;
        }
    }
}
