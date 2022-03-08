package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ConstructStringWithRepeatLimit {
    /*
    You are given a string s and an integer repeatLimit. Construct a new string repeatLimitedString using the characters of s such that no letter appears more than repeatLimit times in a row. You do not have to use all characters from s.
    Return the lexicographically largest repeatLimitedString possible.
    A string a is lexicographically larger than a string b if in the first position where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b. If the first min(a.length, b.length) characters do not differ, then the longer string is the lexicographically larger one.

    Example 1:
    Input: s = "cczazcc", repeatLimit = 3
    Output: "zzcccac"
    Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
    The letter 'a' appears at most 1 time in a row.
    The letter 'c' appears at most 3 times in a row.
    The letter 'z' appears at most 2 times in a row.
    Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
    The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
    Note that the string "zzcccca" is lexicographically larger but the letter 'c' appears more than 3 times in a row, so it is not a valid repeatLimitedString.

    Example 2:
    Input: s = "aababab", repeatLimit = 2
    Output: "bbabaa"
    Explanation: We use only some of the characters from s to construct the repeatLimitedString "bbabaa".
    The letter 'a' appears at most 2 times in a row.
    The letter 'b' appears at most 2 times in a row.
    Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
    The string is the lexicographically largest repeatLimitedString possible so we return "bbabaa".
    Note that the string "bbabaaa" is lexicographically larger but the letter 'a' appears more than 2 times in a row, so it is not a valid repeatLimitedString.

    Constraints:
    1 <= repeatLimit <= s.length <= 10^5
    s consists of lowercase English letters.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"zzcccac", "cczazcc", 3},
                {"bbabaa", "aababab", 2}
        });
    }

    public String repeatLimitedString(String s, int repeatLimit) {
        char[] result = new char[s.length()];
        int freq[] = new int[128], index = 0, count;
        for (char c : s.toCharArray()) freq[c]++;
        for (char max = 'z', second = 'y'; ; ) {
            while (max >= 'a' && freq[max] == 0) max--;
            if (max < 'a') break;
            second = (char) Math.min(max - 1, second);
            for (freq[max] -= (count = Math.min(freq[max], repeatLimit)); count-- > 0; ) result[index++] = max;
            if (freq[max] == 0) {
                max = second--;
                continue;
            }
            while (second >= 'a' && freq[second] == 0) second--;
            if (second < 'a') break;
            --freq[result[index++] = second];
        }
        return new String(result, 0, index);
    }
}
