package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

public class CheckIfAnyElementHasPrimeFrequency {
    /*
    You are given an integer array nums.
    Return true if the frequency of any element of the array is prime, otherwise, return false.
    The frequency of an element x is the number of times it occurs in the array.
    A prime number is a natural number greater than 1 with only two factors, 1 and itself.

    Example 1:
    Input: nums = [1,2,3,4,5,4]
    Output: true
    Explanation:
    4 has a frequency of two, which is a prime number.

    Example 2:
    Input: nums = [1,2,3,4,5]
    Output: false
    Explanation:
    All elements have a frequency of one.

    Example 3:
    Input: nums = [2,2,2,4,4]
    Output: true
    Explanation:
    Both 2 and 4 have a prime frequency.

    Constraints:
        1 <= nums.length <= 100
        0 <= nums[i] <= 100
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, new int[]{1, 2, 3, 4, 5, 4}},
                {false, new int[]{1, 2, 3, 4, 5}},
                {true, new int[]{2, 2, 2, 4, 4}},
        });
    }

    public static void main(String[] args) {
        int[] nums = new int[101];
        for (int i = 2, j; i < 101; i++) if (nums[i] == 0) for (nums[j = i] = 1; (j += i) < 101; ) nums[j] = 2;
        System.out.println(Arrays.toString(nums));
    }

    public boolean checkPrimeFrequency(int[] nums) {
        int frequency[] = new int[101], prime[] = {0, 0, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2};
        for (int num : nums) frequency[num]++;
        for (int num : frequency) if (prime[num] == 1) return true;
        return false;
    }
}
