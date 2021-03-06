package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindNumberswithEvenNumberofDigits {
    /*
    Given an array nums of integers, return how many of them contain an even number of digits.

    Example 1:
    Input: nums = [12,345,2,6,7896]
    Output: 2
    Explanation:
    12 contains 2 digits (even number of digits).
    345 contains 3 digits (odd number of digits).
    2 contains 1 digit (odd number of digits).
    6 contains 1 digit (odd number of digits).
    7896 contains 4 digits (even number of digits).
    Therefore only 12 and 7896 contain an even number of digits.

    Example 2:
    Input: nums = [555,901,482,1771]
    Output: 1
    Explanation:
    Only 1771 contains an even number of digits.

    Constraints:
    1 <= nums.length <= 500
    1 <= nums[i] <= 10^5
    */

    public int findNumbers(int[] nums) {
        int count = 0;
        for (int i : nums)
            if ((i > 9 && i < 100) || (i > 999 && i < 10000) || i == 100000) count++;
        return count;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, findNumbers(new int[]{12, 345, 2, 6, 7896}));
        Assert.assertEquals(1, findNumbers(new int[]{555, 901, 482, 1771}));
    }
}
