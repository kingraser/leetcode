package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class PartitionToKEqualSumSubsets {
	/*
	Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.

	Example 1:
	Input: nums = [4,3,2,3,5,2,1], k = 4
	Output: true
	Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.

	Example 2:
	Input: nums = [1,2,3,4], k = 3
	Output: false

	Constraints:
	1 <= k <= nums.length <= 16
	1 <= nums[i] <= 10^4
	The frequency of each element is in the range [1, 4].
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, new int[]{4, 3, 2, 3, 5, 2, 1}, 4},
				{false, new int[]{1, 2, 3, 4}, 3}
		});
	}

	int target, nums[];
	boolean[] visited;

	public boolean canPartitionKSubsets(int[] nums, int k) {
		if (k > (this.nums = nums).length) return false;
		int sum = 0;
		for (int num : nums) sum += num;
		if (sum % k != 0) return false;
		Arrays.sort(nums);
		if (nums[nums.length - 1] > (target = sum / k)) return false;
		visited = new boolean[nums.length];
		return dfs(0, nums.length - 1, k);
	}

	boolean dfs(int sum, int startIndex, int leftSubSetCount) {
		if (leftSubSetCount == 0 || (sum == target && dfs(0, nums.length - 1, leftSubSetCount - 1))) return true;
		for (int i = startIndex; i >= 0; i--) {
			if (visited[i] || sum + nums[i] > target) continue;
			visited[i] = true;
			if (dfs(sum + nums[i], i - 1, leftSubSetCount)) return true;
			visited[i] = false;
		}
		return false;
	}
}
