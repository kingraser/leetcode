package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class CountSubstringsThatCanBeRearrangedToContainAStringI {
    /*
    You are given two strings word1 and word2.
    A string x is called valid if x can be rearranged to have word2 as a `prefix`.
    Return the total number of valid `substrings` of word1.

    Example 1:
    Input: word1 = "bcca", word2 = "abc"
    Output: 1
    Explanation:
    The only valid substring is "bcca" which can be rearranged to "abcc" having "abc" as a prefix.

    Example 2:
    Input: word1 = "abcabc", word2 = "abc"
    Output: 10
    Explanation:
    All the substrings except substrings of size 1 and size 2 are valid.

    Example 3:
    Input: word1 = "abcabc", word2 = "aaabc"
    Output: 0

    Constraints:
        1 <= word1.length <= 10^5
        1 <= word2.length <= 10^4
        word1 and word2 consist only of lowercase English letters.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1L, "bcca", "abc"},
                {10L, "abcabc", "abc"},
                {0L, "abcabc", "aaabc"},
        });
    }

    public long validSubstringCount(String word1, String word2) {
        long result = 0;
        int count2[] = new int[128], word1Length = word1.length(), word2Length = word2.length();
        for (byte c : word2.getBytes()) count2[c]++;
        for (int left = 0, right = 0, size = 0, count1[] = new int[128], rightChar; right <= word1Length; ) {
            for (char leftChar; size == word2Length && left <= right; result += word1Length - right + 1)
                if (--count1[leftChar = word1.charAt(left++)] < count2[leftChar]) size--;
            if (right == word1Length) break;
            if (count1[rightChar = word1.charAt(right++)]++ < count2[rightChar]) size++;
        }
        return result;
    }
}