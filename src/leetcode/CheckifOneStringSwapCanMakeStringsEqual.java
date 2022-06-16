package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class CheckifOneStringSwapCanMakeStringsEqual {
    /*
    You are given two strings s1 and s2 of equal length. A string swap is an operation where you choose two indices in a string (not necessarily different) and swap the characters at these indices.
    Return true if it is possible to make both strings equal by performing at most one string swap on exactly one of the strings. Otherwise, return false.

    Example 1:
    Input: s1 = "bank", s2 = "kanb"
    Output: true
    Explanation: For example, swap the first character with the last character of s2 to make "bank".

    Example 2:
    Input: s1 = "attack", s2 = "defend"
    Output: false
    Explanation: It is impossible to make them equal with one string swap.

    Example 3:
    Input: s1 = "kelb", s2 = "kelb"
    Output: true
    Explanation: The two strings are already equal, so no string swap operation is required.

    Example 4:
    Input: s1 = "abcd", s2 = "dcba"
    Output: false

    Constraints:
    1 <= s1.length, s2.length <= 100
    s1.length == s2.length
    s1 and s2 consist of only lowercase English letters.
    */
    public boolean areAlmostEqual(String s1, String s2) {
        List<Integer> l = new ArrayList<>();
        for (int i = 0, len = s1.length(), c1, c2; i < len; i++)
            if ((c1 = s1.charAt(i)) == (c2 = s2.charAt(i))) continue;
            else if (l.isEmpty() || (l.size() == 1 && s1.charAt(l.get(0)) == c2 && s2.charAt(l.get(0)) == c1)) l.add(i);
            else return false;
        return l.size() != 1;
    }

    @Test
    public void test() {
        TestUtil.testEquals(
                new Object[][]{
                        {true, "bank", "kanb"},
                });
    }
}
