package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CheckIfAStringCanBreakAnotherString {
    /*
    Given two strings: s1 and s2 with the same size, check if some permutation of string s1 can break some permutation of string s2 or vice-versa. In other words s2 can break s1 or vice-versa.
    A string x can break string y (both of size n) if x[i] >= y[i] (in alphabetical order) for all i between 0 and n-1.

    Example 1:
    Input: s1 = "abc", s2 = "xya"
    Output: true
    Explanation: "ayx" is a permutation of s2="xya" which can break to string "abc" which is a permutation of s1="abc".

    Example 2:
    Input: s1 = "abe", s2 = "acd"
    Output: false
    Explanation: All permutations for s1="abe" are: "abe", "aeb", "bae", "bea", "eab" and "eba" and all permutation for s2="acd" are: "acd", "adc", "cad", "cda", "dac" and "dca". However, there is not any permutation from s1 which can break some permutation from s2 and vice-versa.

    Example 3:
    Input: s1 = "leetcodee", s2 = "interview"
    Output: true

    Constraints:
    s1.length == n
    s2.length == n
    1 <= n <= 10^5
    All strings consist of lowercase English letters.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, "abc", "xya"},
                {false, "abe", "acd"},
                {true, "leetcodee", "interview"}
        });
    }

    public boolean checkIfCanBreak(String s1, String s2) {
        int map1[] = new int[128], map2[] = new int[128];
        s1.chars().forEach(c -> map1[c]++);
        s2.chars().forEach(c -> map2[c]++);
        boolean f1 = false, f2 = false;
        for (int index = 'a', count1 = 0, count2 = 0; index <= 'z'; index++)
            if ((count1 += map1[index]) > (count2 += map2[index]) && (f2 & (f1 = true))) return false;
            else if (count2 > count1 && (f1 & (f2 = true))) return false;
        return true;
    }
}
