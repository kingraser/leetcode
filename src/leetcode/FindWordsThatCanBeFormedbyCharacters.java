package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindWordsThatCanBeFormedbyCharacters {
    /*
    You are given an array of strings words and a string chars.
    A string is good if it can be formed by characters from chars (each character can only be used once).
    Return the sum of lengths of all good strings in words.

    Example 1:
    Input: words = ["cat","bt","hat","tree"], chars = "atach"
    Output: 6
    Explanation:
    The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.

    Example 2:
    Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
    Output: 10
    Explanation:
    The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.

    Note:
    1 <= words.length <= 1000
    1 <= words[i].length, chars.length <= 100
    All strings contain lowercase English letters only.
    */

    @Test
    public void test() {
        Assert.assertEquals(6, countCharacters(new String[]{"cat", "bt", "hat", "tree"}, "atach"));
    }

    public int countCharacters(String[] words, String chars) {
        int result = 0, count[][] = new int[26][2], i, j;
        for (i = 0; i < chars.length(); i++) count[chars.charAt(i) - 'a'][0]++;
        for (String s : words) {
            for (i = 0; i < s.length(); i++) if (++count[j = (s.charAt(i) - 'a')][1] > count[j][0]) break;
            if (i == s.length()) result += s.length();
            for (i = 0; i < 26; i++) count[i][1] = 0;
        }
        return result;
    }
}
