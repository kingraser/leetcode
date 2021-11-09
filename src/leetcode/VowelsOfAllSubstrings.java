package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import static leetcode.CountVowelSubstringsOfAString.isVowel;

/**
 * @author Wit
 */
public class VowelsOfAllSubstrings {
    /*
    Given a string word, return the sum of the number of vowels ('a', 'e', 'i', 'o', and 'u') in every substring of word.
    A substring is a contiguous (non-empty) sequence of characters within a string.
    Note: Due to the large constraints, the answer may not fit in a signed 32-bit integer. Please be careful during the calculations.

    Example 1:
    Input: word = "aba"
    Output: 6
    Explanation:
    All possible substrings are: "a", "ab", "aba", "b", "ba", and "a".
    - "b" has 0 vowels in it
    - "a", "ab", "ba", and "a" have 1 vowel each
    - "aba" has 2 vowels in it
    Hence, the total sum of vowels = 0 + 1 + 1 + 1 + 1 + 2 = 6.

    Example 2:
    Input: word = "abc"
    Output: 3
    Explanation:
    All possible substrings are: "a", "ab", "abc", "b", "bc", and "c".
    - "a", "ab", and "abc" have 1 vowel each
    - "b", "bc", and "c" have 0 vowels each
    Hence, the total sum of vowels = 1 + 1 + 1 + 0 + 0 + 0 = 3.

    Example 3:
    Input: word = "ltcd"
    Output: 0
    Explanation: There are no vowels in any substring of "ltcd".

    Example 4:
    Input: word = "noosabasboosa"
    Output: 237
    Explanation: There are a total of 237 vowels in all the substrings.

    Constraints:
    1 <= word.length <= 10^5
    word consists of lowercase English letters.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {6L, "aba"},
                {3L, "abc"},
                {0L, "ltcd"},
                {237L, "noosabasboosa"}
        });
    }

    public long countVowels(String s) {
        long res = 0, len = s.length();
        for (int i = 0; i < len; i++) if (isVowel(s.charAt(i))) res += (i + 1) * (len - i);
        return res;
    }
}
