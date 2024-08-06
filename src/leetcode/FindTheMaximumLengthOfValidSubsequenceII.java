package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindTheMaximumLengthOfValidSubsequenceII {
/*
You are given an integer array nums and a positive integer k.
A subsequence sub of nums with length x is called valid if it satisfies:
    (sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k.
Return the length of the longest valid subsequence of nums.

Example 1:
Input: nums = [1,2,3,4,5], k = 2
Output: 5
Explanation:
The longest valid subsequence is [1, 2, 3, 4, 5].

Example 2:
Input: nums = [1,4,2,3,1,4], k = 3
Output: 4

Explanation:
The longest valid subsequence is [1, 4, 1, 4].

Constraints:
    2 <= nums.length <= 10^3
    1 <= nums[i] <= 10^7
    1 <= k <= 10^3
*/


    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {5, new int[]{1, 2, 3, 4, 5}, 2},
                {4, new int[]{1, 4, 2, 3, 1, 4}, 3},
        });
    }

    //dp[currentNum][mod] for seq length of mod which ends with currentNum
    public int maximumLength(int[] nums, int k) {
        int dp[][] = new int[k][k], max = 0;
        for (int num : nums)
            for (int remain = 0, current = num % k; remain < k; )
                max = Math.max(max, dp[current][remain] = dp[remain++][current] + 1);
        return max;
    }
}
