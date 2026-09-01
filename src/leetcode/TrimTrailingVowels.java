package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import static leetcode.CountVowelSubstringsOfAString.isVowel;

public class TrimTrailingVowels {
    /*
    You are given a string s that consists of lowercase English letters.
    Return the string obtained by removing all trailing vowels from s.
    The vowels consist of the characters 'a', 'e', 'i', 'o', and 'u'.

    Example 1:
    Input: s = "idea"
    Output: "id"
    Explanation:
    Removing "idea", we obtain the string "id".

    Example 2:
    Input: s = "day"
    Output: "day"
    Explanation:
    There are no trailing vowels in the string "day".

    Example 3:
    Input: s = "aeiou"
    Output: ""
    Explanation:
    Removing "aeiou", we obtain the string "".

    Constraints:
    1 <= s.length <= 100
    s consists of only lowercase English letters.
    */
    @Test
    public void test() {
        TestUtil.testEquals(
                new Object[]{"id", "day", ""},
                new Object[][]{
                        {"idea"},
                        {"day"},
                        {"aeiou"}
                }
        );
    }

    public String trimTrailingVowels(String s) {
        char[] result = s.toCharArray();
        int length = result.length;
        while (length > 0 && isVowel(s.charAt(length - 1))) {
            length--;
        }
        return new String(result, 0, length);
    }
}
