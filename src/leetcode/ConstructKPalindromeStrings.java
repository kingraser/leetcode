package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class ConstructKPalindromeStrings {
    /*
    Given a string s and an integer k, return true if you can use all the characters in s to construct k palindrome strings or false otherwise.

    Example 1:
    Input: s = "annabelle", k = 2
    Output: true
    Explanation: You can construct two palindromes using all characters in s.
    Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"

    Example 2:
    Input: s = "leetcode", k = 3
    Output: false
    Explanation: It is impossible to construct 3 palindromes using all the characters of s.

    Example 3:
    Input: s = "true", k = 4
    Output: true
    Explanation: The only possible solution is to put each character in a separate string.

    Constraints:
        1 <= s.length <= 10^5
        s consists of lowercase English letters.
        1 <= k <= 10^5
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, "annabelle", 2},
                {false, "leetcode", 3},
                {true, "true", 4},
        });
    }

    public boolean canConstruct(String s, int k) {
        if (s.length() < k) return false;
        int map[] = new int['z' + 1], oddCount = 0;
        for (byte c : s.getBytes()) map[c]++;
        for (int c = 'a'; c <= 'z'; ) if ((map[c++] & 1) == 1) oddCount++;
        return oddCount <= k;
    }
}
