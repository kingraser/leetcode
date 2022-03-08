package leetcode;

import leetcode.util.ArrayUtil;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class FindFirstPalindromicStringInTheArray {
    /*
    Given an array of strings words, return the first palindromic string in the array. If there is no such string, return an empty string "".
    A string is palindromic if it reads the same forward and backward.

    Example 1:
    Input: words = ["abc","car","ada","racecar","cool"]
    Output: "ada"
    Explanation: The first string that is palindromic is "ada".
    Note that "racecar" is also palindromic, but it is not the first.

    Example 2:
    Input: words = ["notapalindrome","racecar"]
    Output: "racecar"
    Explanation: The first and only string that is palindromic is "racecar".

    Example 3:
    Input: words = ["def","ghi"]
    Output: ""
    Explanation: There are no palindromic strings, so the empty string is returned.

    Constraints:
    1 <= words.length <= 100
    1 <= words[i].length <= 100
    words[i] consists only of lowercase English letters.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"ada", ArrayUtil.of("abc", "car", "ada", "racecar", "cool")},
                {"racecar", ArrayUtil.of("notapalindrome", "racecar")},
                {"", ArrayUtil.of("def", "ghi")}
        });
    }

    public String firstPalindrome(String[] words) {
        return Arrays.stream(words).filter(this::isPalindrome).findFirst().orElse("");
    }

    private boolean isPalindrome(String word) {
        for (int left = 0, right = word.length() - 1; left < right; )
            if (word.charAt(left++) != word.charAt(right--)) return false;
        return true;
    }
}
