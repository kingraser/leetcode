package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class StringCompression {
    /*
    Given an array of characters chars, compress it using the following algorithm:
    Begin with an empty string s. For each group of consecutive repeating characters in chars:
    If the group's length is 1, append the character to s.
    Otherwise, append the character followed by the group's length.
    The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
    After you are done modifying the input array, return the new length of the array.
    You must write an algorithm that uses only constant extra space.

    Example 1:
    Input: chars = ["a","a","b","b","c","c","c"]
    Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
    Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".

    Example 2:
    Input: chars = ["a"]
    Output: Return 1, and the first character of the input array should be: ["a"]
    Explanation: The only group is "a", which remains uncompressed since it's a single character.

    Example 3:
    Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
    Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
    Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".

    Example 4:
    Input: chars = ["a","a","a","b","b","a","a"]
    Output: Return 6, and the first 6 characters of the input array should be: ["a","3","b","2","a","2"].
    Explanation: The groups are "aaa", "bb", and "aa". This compresses to "a3b2a2". Note that each group is independent even if two groups have the same character.

    Constraints:
    1 <= chars.length <= 2000
    chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {6, "aabbccc".toCharArray()},
                {1, "a".toCharArray()},
                {4, "abbbbbbbbbbbb".toCharArray()},
                {6, "aaabbaa".toCharArray()}
        });
    }

    static char cur, D[] = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public int compress(char[] A) {
        int res = 0;
        for (int i = 0, count, len = A.length; i < len; ) {
            for (A[res++] = cur = A[i++], count = 1; i < len && cur == A[i]; i++) count++;
            if (count > 1) for (int end = (res += getLen(count)) - 1; count != 0; count /= 10) A[end--] = D[count % 10];
        }
        return res;
    }

    int getLen(int val) {return val < 10 ? 1 : val < 100 ? 2 : val < 1000 ? 3 : 4;}
}
