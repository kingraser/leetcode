package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ReorganizeString {
    /*
    Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
    Return any possible rearrangement of s or return "" if not possible.

    Example 1:
    Input: s = "aab"
    Output: "aba"

    Example 2:
    Input: s = "aaab"
    Output: ""

    Constraints:
    1 <= s.length <= 500
    s consists of lowercase English letters.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"aba", "aab"},
                {"", "aaab"}
        });
    }

    public String reorganizeString(String s) {
        int len = s.length(), counts[] = new int[128], maxCount = 0, idx = 0;
        char res[] = new char[len], maxLetter = 0;
        for (char c : s.toCharArray()) if (++counts[c] > maxCount) maxCount = counts[maxLetter = c];
        if (maxCount > (len + 1) >> 1) return "";
        for (; counts[maxLetter] > 0; idx += 2) counts[res[idx] = maxLetter]--;
        for (char c = 'a'; c <= 'z'; c++) for (; counts[c] > 0; idx += 2) counts[res[idx = idx >= len ? 1 : idx] = c]--;
        return String.valueOf(res);
    }

}
