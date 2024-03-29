package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountOddNumbersinanIntervalRange {
    /*
    Given two non-negative integers low and high. Return the count of odd numbers between low and high (inclusive).

    Example 1:
    Input: low = 3, high = 7
    Output: 3
    Explanation: The odd numbers between 3 and 7 are [3,5,7].

    Example 2:
    Input: low = 8, high = 10
    Output: 1
    Explanation: The odd numbers between 8 and 10 are [9].

    Constraints:
    0 <= low <= high <= 10^9
    */
    public int countOdds(int low, int high) {
        return (++high >> 1) - (low >> 1);
    }

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {3, 3, 7},
                {1, 8, 10}
        });
    }
}
