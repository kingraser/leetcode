package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wit
 */
public class FindtheMiddleIndexinArray {
    /*
    Given a 0-indexed integer array nums, find the leftmost middleIndex (i.e., the smallest amongst all the possible ones).
    A middleIndex is an index where nums[0] + nums[1] + ... + nums[middleIndex-1] == nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1].
    If middleIndex == 0, the left side sum is considered to be 0. Similarly, if middleIndex == nums.length - 1, the right side sum is considered to be 0.
    Return the leftmost middleIndex that satisfies the condition, or -1 if there is no such index.

    Example 1:
    Input: nums = [2,3,-1,8,4]
    Output: 3
    Explanation:
    The sum of the numbers before index 3 is: 2 + 3 + -1 = 4
    The sum of the numbers after index 3 is: 4 = 4

    Example 2:
    Input: nums = [1,-1,4]
    Output: 2
    Explanation:
    The sum of the numbers before index 2 is: 1 + -1 = 0
    The sum of the numbers after index 2 is: 0

    Example 3:
    Input: nums = [2,5]
    Output: -1
    Explanation:
    There is no valid middleIndex.

    Example 4:
    Input: nums = [1]
    Output: 0
    Explantion:
    The sum of the numbers before index 0 is: 0
    The sum of the numbers after index 0 is: 0

    Constraints:
    1 <= nums.length <= 100
    -1000 <= nums[i] <= 1000
    */

    public int findMiddleIndex(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        for (int i = 0, leftSum = 0; i < nums.length; leftSum += nums[i++])
            if (leftSum << 1 == sum - nums[i]) return i;
        return -1;
    }

    public int findMiddleIndexII(int[] nums) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>(nums.length << 1);
        for (int i = 0; i < nums.length; sum += nums[i++]) map.putIfAbsent((sum << 1) + nums[i], i);
        return map.getOrDefault(sum, -1);
    }

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {3, new int[]{2, 3, -1, 8, 4}},
                {2, new int[]{1, -1, 4}},
                {-1, new int[]{2, 5}},
                {0, new int[]{1}}
        });
    }
}
