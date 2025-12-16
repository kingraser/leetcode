package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;


public class AbsoluteDifferenceBetweenMaximumAndMinimumKElements {
    /*
    You are given an integer array nums and an integer k.
    Find the absolute difference between:
        the sum of the k largest elements in the array; and
        the sum of the k smallest elements in the array.
    Return an integer denoting this difference.

    Example 1:
    Input: nums = [5,2,2,4], k = 2
    Output: 5
    Explanation:
        The k = 2 largest elements are 4 and 5. Their sum is 4 + 5 = 9.
        The k = 2 smallest elements are 2 and 2. Their sum is 2 + 2 = 4.
        The absolute difference is abs(9 - 4) = 5.

    Example 2:
    Input: nums = [100], k = 1
    Output: 0
    Explanation:
        The largest element is 100.
        The smallest element is 100.
        The absolute difference is abs(100 - 100) = 0.

    Constraints:
        1 <= n == nums.length <= 100
        1 <= nums[i] <= 100
        1 <= k <= n
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {5, new int[]{5, 2, 2, 4}, 2},
                {0, new int[]{100}, 1},
        });
    }

    public int absDifference(int[] nums, int k) {
        int big = 0, small = 0, map[] = new int[101];
        for (int num : nums) map[num]++;
        for (int num = 100, newk = k; newk > 0; num--)
            if (map[num] > 0) big += ((newk -= map[num]) < 0 ? map[num] + newk : map[num]) * num;
        for (int num = 1; k > 0; num++)
            if (map[num] > 0) small += ((k -= map[num]) < 0 ? map[num] + k : map[num]) * num;
        return big - small;
    }
}
