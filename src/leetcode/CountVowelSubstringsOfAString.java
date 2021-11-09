package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountVowelSubstringsOfAString {
    /*
    A substring is a contiguous (non-empty) sequence of characters within a string.
    A vowel substring is a substring that only consists of vowels ('a', 'e', 'i', 'o', and 'u') and has all five vowels present in it.
    Given a string word, return the number of vowel substrings in word.

    Example 1:
    Input: word = "aeiouu"
    Output: 2
    Explanation: The vowel substrings of word are as follows (underlined):
    - "aeiouu"
    - "aeiouu"

    Example 2:
    Input: word = "unicornarihan"
    Output: 0
    Explanation: Not all 5 vowels are present, so there are no vowel substrings.

    Example 3:
    Input: word = "cuaieuouac"
    Output: 7
    Explanation: The vowel substrings of word are as follows (underlined):
    - "cuaieuouac"
    - "cuaieuouac"
    - "cuaieuouac"
    - "cuaieuouac"
    - "cuaieuouac"
    - "cuaieuouac"
    - "cuaieuouac"

    Example 4:
    Input: word = "bbaeixoubb"
    Output: 0
    Explanation: The only substrings that contain all five vowels also contain consonants, so there are no vowel substrings.

    Constraints:
    1 <= word.length <= 100
    word consists of lowercase English letters only.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {0, "bbaeixoubb"},
                {2, "aeiouu"},
                {0, "unicornarihan"},
                {7, "cuaieuouac"},
                {0, "bbaeixoubb"}
        });
    }

    public int countVowelSubstrings(String word) {
        int result = 0;
        for (int i = 0, c, left = i, right = i, len = word.length(), vow = 0, counts[] = new int[128]; i < len; result += right - left)
            if (!isVowel(c = word.charAt(i++))) {
                counts['a'] = counts['e'] = counts['i'] = counts['o'] = counts['u'] = vow = 0;
                left = right = i;
            } else for (vow += counts[c]++ == 0 ? 1 : 0; vow == 5; ) if (--counts[word.charAt(right++)] == 0) vow--;
        return result;
    }

    public static boolean isVowel(int c) {return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';}
}


