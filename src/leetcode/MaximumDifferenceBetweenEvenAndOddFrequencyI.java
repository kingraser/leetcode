package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class MaximumDifferenceBetweenEvenAndOddFrequencyI {
    /*
    You are given a string s consisting of lowercase English letters. Your task is to find the maximum difference between the frequency of two characters in the string such that:
        One of the characters has an even frequency in the string.
        The other character has an odd frequency in the string.
    Return the maximum difference, calculated as the frequency of the character with an odd frequency minus the frequency of the character with an even frequency.

    Example 1:
    Input: s = "aaaaabbc"
    Output: 3
    Explanation:
        The character 'a' has an odd frequency of 5, and 'b' has an even frequency of 2.
        The maximum difference is 5 - 2 = 3.

    Example 2:
    Input: s = "abcabcab"
    Output: 1
    Explanation:
        The character 'a' has an odd frequency of 3, and 'c' has an even frequency of 2.
        The maximum difference is 3 - 2 = 1.

    Constraints:
        3 <= s.length <= 100
        s consists only of lowercase English letters.
        s contains at least one character with an odd frequency and one with an even frequency.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {-1, "mmsmsym"},
                {3, "aaaaabbc"},
                {1, "abcabcab"},
                {-1, "tzt"},
                {1, "zgzaaa"}
        });
    }

    public int maxDifference(String s) {
        int count[] = new int[128], maxOdd = 0, minEven = 100;
        for (char c : s.toCharArray()) count[c]++;
        for (int i = 'a'; i <= 'z'; i++)
            if ((count[i] & 1) == 1) maxOdd = Math.max(maxOdd, count[i]);
            else if (count[i] != 0) minEven = Math.min(minEven, count[i]);
        return maxOdd - minEven;
    }
}
