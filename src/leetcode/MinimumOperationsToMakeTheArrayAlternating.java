package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimumOperationsToMakeTheArrayAlternating {
    /*
    You are given a 0-indexed array nums consisting of n positive integers.
    The array nums is called alternating if:
    nums[i - 2] == nums[i], where 2 <= i <= n - 1.
    nums[i - 1] != nums[i], where 1 <= i <= n - 1.
    In one operation, you can choose an index i and change nums[i] into any positive integer.
    Return the minimum number of operations required to make the array alternating.

    Example 1:
    Input: nums = [3,1,3,2,4,3]
    Output: 3
    Explanation:
    One way to make the array alternating is by converting it to [3,1,3,1,3,1].
    The number of operations required in this case is 3.
    It can be proven that it is not possible to make the array alternating in less than 3 operations.

    Example 2:
    Input: nums = [1,2,2,2,2]
    Output: 2
    Explanation:
    One way to make the array alternating is by converting it to [1,2,1,2,1].
    The number of operations required in this case is 2.
    Note that the array cannot be converted to [2,2,2,2,2] because in this case nums[0] == nums[1] which violates the conditions of an alternating array.

    Constraints:
    1 <= nums.length <= 10^5
    1 <= nums[i] <= 10^5
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, new int[]{2, 2, 2, 2}},
                {3, new int[]{3, 1, 3, 2, 4, 3}},
                {2, new int[]{1, 2, 2, 2, 2}},
        });
    }

    public int minimumOperations(int[] nums) {
        int evens[] = new int[100001], odds[] = new int[100001], evenFirst = 0, evenSecond = 0, oddFirst = 0, oddSecond = 0;
        for (int i = 0, count; i < nums.length; i++)
            if ((i & 1) == 0) {
                if (++evens[nums[i]] >= evens[evenFirst]) {
                    if (nums[i] != evenFirst) evenSecond = evenFirst;
                    evenFirst = nums[i];
                } else if (evens[nums[i]] > evens[evenSecond]) evenSecond = nums[i];
            } else if (++odds[nums[i]] >= odds[oddFirst]) {
                if (nums[i] != oddFirst) oddSecond = oddFirst;
                oddFirst = nums[i];
            } else if (odds[nums[i]] > odds[oddSecond]) oddSecond = nums[i];
        if (evenFirst != oddFirst) return nums.length - evens[evenFirst] - odds[oddFirst];
        return nums.length - Math.max(evens[evenFirst] + odds[oddSecond], evens[evenSecond] + odds[oddFirst]);
    }
}
