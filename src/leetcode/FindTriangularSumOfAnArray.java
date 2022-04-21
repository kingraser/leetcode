package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindTriangularSumOfAnArray {
    /*
    You are given a 0-indexed integer array nums, where nums[i] is a digit between 0 and 9 (inclusive).
    The triangular sum of nums is the value of the only element present in nums after the following process terminates:
    Let nums comprise n elements. If n == 1, end the process. Otherwise, create a new 0-indexed integer array newNums of length n - 1.
    For each index i, where 0 <= i < n - 1, assign the value of newNums[i] as (nums[i] + nums[i+1]) % 10, where % denotes modulo operator.
    Replace the array nums with newNums.
    Repeat the entire process starting from step 1.
    Return the triangular sum of nums.

    Example 1:
    Input: nums = [1,2,3,4,5]
    Output: 8
    Explanation:
    The above diagram depicts the process from which we obtain the triangular sum of the array.

    Example 2:
    Input: nums = [5]
    Output: 5
    Explanation:
    Since there is only one element in nums, the triangular sum is the value of that element itself.

    Constraints:
    1 <= nums.length <= 1000
    0 <= nums[i] <= 9
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {8, new int[]{1, 2, 3, 4, 5}},
                {5, new int[]{5}}
        });
    }

    public int triangularSum(int[] nums) {
        int result = 0, m = nums.length - 1, combinations = 1, exp2 = 0, exp5 = 0, inverse[] = {0, 1, 0, 7, 0, 0, 0, 3, 0, 9}, pow2mod10[] = {6, 2, 4, 8};
        for (int k = 0, mul, div; ; combinations = combinations * inverse[div % 10] % 10) {
            if (exp2 == 0 || exp5 == 0) {
                int temp = exp2 > 0 ? combinations * pow2mod10[exp2 & 3] : exp5 > 0 ? combinations * 5 : combinations;
                result = (result + temp * nums[k]) % 10;
            }
            if (k == m) return result;
            for (mul = m - k; (mul & 1) == 0; mul >>= 1) exp2++;
            for (; mul % 5 == 0; mul /= 5) exp5++;
            for (combinations = combinations * mul % 10, div = ++k; (div & 1) == 0; div >>= 1) exp2--;
            for (; div % 5 == 0; div /= 5) exp5--;
        }
    }
}
