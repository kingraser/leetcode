package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SumOfBeautyInTheArray {
    /*
    You are given a 0-indexed integer array nums. For each index i (1 <= i <= nums.length - 2) the beauty of nums[i] equals:
    2, if nums[j] < nums[i] < nums[k], for all 0 <= j < i and for all i < k <= nums.length - 1.
    1, if nums[i - 1] < nums[i] < nums[i + 1], and the previous condition is not satisfied.
    0, if none of the previous conditions holds.
    Return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.

    Example 1:
    Input: nums = [1,2,3]
    Output: 2
    Explanation: For each index i in the range 1 <= i <= 1:
    - The beauty of nums[1] equals 2.

    Example 2:
    Input: nums = [2,4,6,4]
    Output: 1
    Explanation: For each index i in the range 1 <= i <= 2:
    - The beauty of nums[1] equals 1.
    - The beauty of nums[2] equals 0.

    Example 3:
    Input: nums = [3,2,1]
    Output: 0
    Explanation: For each index i in the range 1 <= i <= 1:
    - The beauty of nums[1] equals 0.

    Constraints:
    3 <= nums.length <= 10^5
    1 <= nums[i] <= 10^5
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, new int[]{1, 2, 3}},
                {1, new int[]{2, 4, 6, 4}},
                {0, new int[]{3, 2, 1}}
        });
    }

    public int sumOfBeauties(int[] nums) {
        int result = 0, last = nums.length - 1, min[] = new int[nums.length];
        min[last] = nums[last];
        for (int i = last - 1; i >= 2; i--) min[i] = Math.min(min[i + 1], nums[i]);
        for (int i = 1, max = nums[0]; i < last; max = Math.max(nums[i++], max))
            if (nums[i] > max && nums[i] < min[i + 1]) result += 2;
            else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) result += 1;
        return result;
    }
}
