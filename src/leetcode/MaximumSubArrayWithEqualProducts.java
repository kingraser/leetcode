package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

public class MaximumSubArrayWithEqualProducts {
    /*
    You are given an array of positive integers nums.
    An array arr is called product equivalent if prod(arr) == lcm(arr) * gcd(arr), where:
        prod(arr) is the product of all elements of arr.
        gcd(arr) is the GCD(Greatest Common Divisor) of all elements of arr.
        lcm(arr) is the LCM(Least Common Multiple) of all elements of arr.
    Return the length of the longest product equivalent sub-array of nums.

    Example 1:
    Input: nums = [1,2,1,2,1,1,1]
    Output: 5
    Explanation:
    The longest product equivalent subarray is [1, 2, 1, 1, 1], where prod([1, 2, 1, 1, 1]) = 2, gcd([1, 2, 1, 1, 1]) = 1, and lcm([1, 2, 1, 1, 1]) = 2.

    Example 2:
    Input: nums = [2,3,4,5,6]
    Output: 3
    Explanation:
    The longest product equivalent subarray is [3, 4, 5].

    Example 3:
    Input: nums = [1,2,3,1,4,5,1]
    Output: 5
    Constraints:
        2 <= nums.length <= 100
        1 <= nums[i] <= 10
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {5, new int[]{1, 2, 1, 2, 1, 1, 1}},
                {3, new int[]{2, 3, 4, 5, 6}},
                {5, new int[]{1, 2, 3, 1, 4, 5, 1}},
        });
    }

    int[] primes = new int[]{2, 3, 5, 7};

    int getFlag(int[] nums, int[] flags, int index) {
        if (flags[index] != -1) return flags[index];
        int result = 0, num = nums[index];
        if (num == 1) return flags[index] = 0;
        for (int i = 0; i < primes.length; i++) if (num % primes[i] == 0) result += 1 << i;
        return flags[index] = result;
    }

    public int maxLength(int[] nums) {
        int result = 2, length = nums.length, flags[] = new int[length];
        Arrays.fill(flags, -1);
        for (int left = 0, right = 0, bits = 0, currentFlag; right < length; bits |= currentFlag, result = Math.max(result, ++right - left))
            for (currentFlag = getFlag(nums, flags, right); (bits & currentFlag) != 0; )
                bits ^= getFlag(nums, flags, left++);
        return result;
    }
}
