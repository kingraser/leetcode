package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountBinarySubstrings {

    /*
    Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's,
    and all the 0's and all the 1's in these substrings are grouped consecutively.

    Substrings that occur multiple times are counted the number of times they occur.

    Example 1:
    Input: "00110011"
    Output: 6
    Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".

    Notice that some of these substrings repeat and are counted the number of times they occur.

    Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.

    Example 2:
    Input: "10101"
    Output: 4
    Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.

    Note:
    s.length will be between 1 and 50,000.
    s will only consist of "0" or "1" characters.
    */

    @Test
    public void test() {
        assertEquals(6, countBinarySubstrings("00110011"));
        assertEquals(4, countBinarySubstrings("10101"));
    }

    public int countBinarySubstrings(String s) {
        int result = 0;
        for (int idx = 1, prev = 0, cur = 1; idx < s.length(); idx++) {
            if (s.charAt(idx) == s.charAt(idx - 1)) {
                cur++;
            } else {
                prev = cur;
                cur = 1;
            }
            if (prev >= cur) {result++;}
        }
        return result;
    }
}