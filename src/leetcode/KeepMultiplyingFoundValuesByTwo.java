package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Wit
 */
public class KeepMultiplyingFoundValuesByTwo {
    /*
    You are given an array of integers nums. You are also given an integer original which is the first number that needs to be searched for in nums.
    You then do the following steps:
    If original is found in nums, multiply it by two (i.e., set original = 2 * original).
    Otherwise, stop the process.
    Repeat this process with the new number as long as you keep finding the number.
    Return the final value of original.

    Example 1:
    Input: nums = [5,3,6,1,12], original = 3
    Output: 24
    Explanation:
    - 3 is found in nums. 3 is multiplied by 2 to obtain 6.
    - 6 is found in nums. 6 is multiplied by 2 to obtain 12.
    - 12 is found in nums. 12 is multiplied by 2 to obtain 24.
    - 24 is not found in nums. Thus, 24 is returned.

    Example 2:
    Input: nums = [2,7,9], original = 4
    Output: 4
    Explanation:
    - 4 is not found in nums. Thus, 4 is returned.

    Constraints:
    1 <= nums.length <= 1000
    1 <= nums[i], original <= 1000
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {24, new int[]{5, 3, 6, 1, 12}, 3},
                {4, new int[]{2, 7, 9}, 4}
        });
    }

    public int findFinalValue(int[] nums, int original) {
        Set<Integer> set = new HashSet<>(nums.length << 1);
        for (int num : nums) set.add(num);
        for (; ; original <<= 1) if (!set.contains(original)) return original;
    }

    public int findFinalValueII(int[] nums, int original) {
        boolean[] set = new boolean[1001];
        for (int num : nums) set[num] = true;
        for (; original < set.length; original <<= 1) if (!set[original]) break;
        return original;
    }
}
