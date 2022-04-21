package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindPalindromeWithFixedLength {
    /*
    Given an integer array queries and a positive integer intLength, return an array answer where answer[i] is either the queries[i]th smallest positive palindrome of length intLength or -1 if no such palindrome exists.
    A palindrome is a number that reads the same backwards and forwards. Palindromes cannot have leading zeros.

    Example 1:
    Input: queries = [1,2,3,4,5,90], intLength = 3
    Output: [101,111,121,131,141,999]
    Explanation:
    The first few palindromes of length 3 are:
    101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 202, ...
    The 90th palindrome of length 3 is 999.

    Example 2:
    Input: queries = [2,4,6], intLength = 4
    Output: [1111,1331,1551]
    Explanation:
    The first six palindromes of length 4 are:
    1001, 1111, 1221, 1331, 1441, and 1551.

    Constraints:
    1 <= queries.length <= 5 * 10^4
    1 <= queries[i] <= 10^9
    1 <= intLength <= 15
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new long[]{101, 111, 121, 131, 141, 999}, new int[]{1, 2, 3, 4, 5, 90}, 3},
                {new long[]{1111, 1331, 1551}, new int[]{2, 4, 6}, 4},
        });
    }

    static final int MAX_INT_LENGTH = 15;
    static final long[] POW10 = new long[(MAX_INT_LENGTH + 1) >> 1];

    static {
        POW10[0] = 1;
        for (int i = 1; i < POW10.length; i++) POW10[i] = POW10[i - 1] * 10;
    }

    public long[] kthPalindrome(int[] queries, int intLength) {
        boolean isOddLen = (intLength & 1) == 1;
        long res[] = new long[queries.length], left, right, begin = POW10[(intLength - 1) >> 1], end = begin * 10, shift = isOddLen ? begin : end;
        for (int i = 0; i < queries.length; i++)
            if ((left = begin + queries[i] - 1) < end) {
                res[i] = left * shift;
                if (isOddLen) left /= 10;
                for (right = 0; left > 0; left /= 10) right = right * 10 + left % 10;
                res[i] += right;
            } else res[i] = -1;
        return res;
    }
}
