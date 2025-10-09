package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class MaximizeSumOfAtMostKDistinctElements {
    /*
    You are given a positive integer array nums and an integer k.
    Choose at most k elements from nums so that their sum is maximized. However, the chosen numbers must be distinct.
    Return an array containing the chosen numbers in strictly descending order.

    Example 1:
    Input: nums = [84,93,100,77,90], k = 3
    Output: [100,93,90]
    Explanation:
    The maximum sum is 283, which is attained by choosing 93, 100 and 90. We rearrange them in strictly descending order as [100, 93, 90].

    Example 2:
    Input: nums = [84,93,100,77,93], k = 3
    Output: [100,93,84]
    Explanation:
    The maximum sum is 277, which is attained by choosing 84, 93 and 100. We rearrange them in strictly descending order as [100, 93, 84]. We cannot choose 93, 100 and 93 because the chosen numbers must be distinct.

    Example 3:
    Input: nums = [1,1,1,2,2,2], k = 6
    Output: [2,1]
    Explanation:
    The maximum sum is 3, which is attained by choosing 1 and 2. We rearrange them in strictly descending order as [2, 1].

    Constraints:
        1 <= nums.length <= 100
        1 <= nums[i] <= 10^9
        1 <= k <= nums.length
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[]{100, 93, 90}, new int[]{84, 93, 100, 77, 90}, 3},
                {new int[]{100, 93, 84}, new int[]{84, 93, 100, 77, 93}, 3},
                {new int[]{2, 1}, new int[]{1, 1, 1, 2, 2, 2}, 6},
        });
    }

    public int[] maxKDistinct(int[] nums, int k) {
        return Arrays.stream(nums)
                .distinct()
                .boxed()
                .sorted(Comparator.reverseOrder())
                .limit(k)
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
