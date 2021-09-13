package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wit
 */
public class CountSpecialQuadruplets {
    /*
    Given a 0-indexed integer array nums, return the number of distinct quadruplets (a, b, c, d) such that:
    nums[a] + nums[b] + nums[c] == nums[d], and
    a < b < c < d

    Example 1:
    Input: nums = [1,2,3,6]
    Output: 1
    Explanation: The only quadruplet that satisfies the requirement is (0, 1, 2, 3) because 1 + 2 + 3 == 6.

    Example 2:
    Input: nums = [3,3,6,4,5]
    Output: 0
    Explanation: There are no such quadruplets in [3,3,6,4,5].

    Example 3:
    Input: nums = [1,1,1,3,5]
    Output: 4
    Explanation: The 4 quadruplets that satisfy the requirement are:
    - (0, 1, 2, 3): 1 + 1 + 1 == 3
    - (0, 1, 3, 4): 1 + 1 + 3 == 5
    - (0, 2, 3, 4): 1 + 1 + 3 == 5
    - (1, 2, 3, 4): 1 + 1 + 3 == 5

    Constraints:
    4 <= nums.length <= 50
    1 <= nums[i] <= 100
     */
    public int countQuadruplets(int[] nums) {
        int result = 0, len = nums.length, last = len - 1;
        Map<Integer, Integer> count = new HashMap<>(len * len) {{put(nums[last] - nums[last - 1], 1);}};
        for (int i = len - 3; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) result += count.getOrDefault(nums[i] + nums[j], 0);
            for (int j = last; j > i; j--) count.merge(nums[j] - nums[i], 1, Integer::sum);
        }
        return result;
    }

    private static final int MAX_NUM = 100;

    public int countQuadrupletsII(int[] nums) {
        int result = 0, countOfNum[] = new int[MAX_NUM + 1], countOfSum2[] = new int[MAX_NUM + 1], countOfSUm3[] = new int[MAX_NUM + 1];
        for (int i = 0, j, k, num; i < nums.length; countOfNum[num]++)
            for (result += countOfSUm3[num = nums[i++]], j = MAX_NUM, k = MAX_NUM - num; k > 0; countOfSum2[j--] += countOfNum[k--])
                countOfSUm3[j] += countOfSum2[k];
        return result;
    }

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1, new int[]{1, 2, 3, 6}},
                {0, new int[]{3, 3, 6, 4, 5}},
                {4, new int[]{1, 1, 1, 3, 5}}
        });
    }
}
