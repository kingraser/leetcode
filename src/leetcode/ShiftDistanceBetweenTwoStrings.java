package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class ShiftDistanceBetweenTwoStrings {
    /*
    You are given two strings s and t of the same length, and two integer arrays nextCost and previousCost.
    In one operation, you can pick any index i of s, and perform either one of the following actions:
        Shift s[i] to the next letter in the alphabet. If s[i] == 'z', you should replace it with 'a'. This operation costs nextCost[j] where j is the index of s[i] in the alphabet.
        Shift s[i] to the previous letter in the alphabet. If s[i] == 'a', you should replace it with 'z'. This operation costs previousCost[j] where j is the index of s[i] in the alphabet.
    The shift distance is the minimum total cost of operations required to transform s into t.
    Return the shift distance from s to t.

    Example 1:
    Input: s = "abab", t = "baba", nextCost = [100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0], previousCost = [1,100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
    Output: 2
    Explanation:
        We choose index i = 0 and shift s[0] 25 times to the previous character for a total cost of 1.
        We choose index i = 1 and shift s[1] 25 times to the next character for a total cost of 0.
        We choose index i = 2 and shift s[2] 25 times to the previous character for a total cost of 1.
        We choose index i = 3 and shift s[3] 25 times to the next character for a total cost of 0.

    Example 2:
    Input: s = "leet", t = "code", nextCost = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1], previousCost = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
    Output: 31
    Explanation:
        We choose index i = 0 and shift s[0] 9 times to the previous character for a total cost of 9.
        We choose index i = 1 and shift s[1] 10 times to the next character for a total cost of 10.
        We choose index i = 2 and shift s[2] 1 time to the previous character for a total cost of 1.
        We choose index i = 3 and shift s[3] 11 times to the next character for a total cost of 11.

    Constraints:
        1 <= s.length == t.length <= 10^5
        s and t consist only of lowercase English letters.
        nextCost.length == previousCost.length == 26
        0 <= nextCost[i], previousCost[i] <= 10^9
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2L, "abab", "baba", new int[]{100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{1, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}},
                {31L, "leet", "code", new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}},
        });
    }

    int ALPHABET_SIZE = 26;

    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        long result = 0, prefixSumNext[] = new long[ALPHABET_SIZE + 1], prefixSumPrevious[] = new long[ALPHABET_SIZE + 1], nsum, psum;
        for (int i = 0; i < ALPHABET_SIZE; ) prefixSumNext[i + 1] = prefixSumNext[i] + nextCost[i++];
        for (int i = ALPHABET_SIZE - 1; i >= 0; ) prefixSumPrevious[i] = prefixSumPrevious[i + 1] + previousCost[i--];
        for (int i = 0, length = s.length(), start, end; i < length; i++) {
            if ((start = s.charAt(i) - 'a') < (end = t.charAt(i) - 'a')) {
                nsum = (prefixSumNext[end] - prefixSumNext[start]);
                psum = (prefixSumPrevious[end + 1] + (prefixSumPrevious[0] - prefixSumPrevious[start + 1]));
            } else if (start > end) {
                nsum = (prefixSumNext[ALPHABET_SIZE] - prefixSumNext[start] + prefixSumNext[end]);
                psum = (prefixSumPrevious[end + 1] - prefixSumPrevious[start + 1]);
            } else continue;
            result += Math.min(nsum, psum);
        }
        return result;
    }
}
