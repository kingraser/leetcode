package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class MinimumMovesToEqualArrayElementsIII {
    /*
    You are given an integer array nums.
    In one move, you may increase the value of any single element nums[i] by 1.
    Return the minimum total number of moves required so that all elements in nums become equal.

    Example 1:
    Input: nums = [2,1,3]
    Output: 3
    Explanation:
    To make all elements equal:
        Increase nums[0] = 2 by 1 to make it 3.
        Increase nums[1] = 1 by 1 to make it 2.
        Increase nums[1] = 2 by 1 to make it 3.
    Now, all elements of nums are equal to 3. The minimum total moves is 3.

    Example 2:
    Input: nums = [4,4,5]
    Output: 2
    Explanation:
    To make all elements equal:
        Increase nums[0] = 4 by 1 to make it 5.
        Increase nums[1] = 4 by 1 to make it 5.
    Now, all elements of nums are equal to 5. The minimum total moves is 2.

    Constraints:
        1 <= nums.length <= 100
        1 <= nums[i] <= 100
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {3, new int[]{2, 1, 3}},
                {2, new int[]{4, 4, 5}},
        });
    }

    public int minMoves(int[] nums) {
        int result = 0, currentMax = nums[0];
        for (int i = 1; i < nums.length; i++)
            if (nums[i] > currentMax) {
                result += (nums[i] - currentMax) * i;
                currentMax = nums[i];
            } else result += currentMax - nums[i];
        return result;
    }
}
