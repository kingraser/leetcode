package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SpecialArrayII {
/*
An array is considered special if every pair of its adjacent elements contains two numbers with different parity.
You are given an array of integer `nums` and a 2D integer matrix queries, where for queries[i] = [from_i, to_i] your task is to check that sub_array nums[from_i..to_i] is special or not.

Return an array of booleans answer such that answer[i] is true if nums[from_i..to_i] is special.

Example 1:
Input: nums = [3,4,1,2,6], queries = [[0,4]]
Output: [false]
Explanation:
The sub_array is [3,4,1,2,6]. 2 and 6 are both even.

Example 2:
Input: nums = [4,3,1,6], queries = [[0,2],[2,3]]
Output: [false,true]
Explanation:
    The sub_array is [4,3,1]. 3 and 1 are both odd. So the answer to this query is false.
    The sub_array is [1,6]. There is only one pair: (1,6) and it contains numbers with different parity. So the answer to this query is true.

Constraints:
    1 <= nums.length <= 10^5
    1 <= nums[i] <= 10^5
    1 <= queries.length <= 10^5
    queries[i].length == 2
    0 <= queries[i][0] <= queries[i][1] <= nums.length - 1
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new boolean[]{false}, new int[]{3, 4, 1, 2, 6}, TestUtil.getArray("[[0,4]]")},
				{new boolean[]{false, true}, new int[]{4, 3, 1, 6}, TestUtil.getArray("[[0,2],[2,3]]")},
		});
	}

	public boolean[] isArraySpecial(int[] nums, int[][] queries) {
		boolean[] result = new boolean[queries.length];
		int prefix[] = new int[nums.length], resultSize = 0;
		for (int i = 1; i < nums.length; i++) prefix[i] = prefix[i - 1] + ((nums[i - 1] ^ nums[i]) & 1);
		for (int[] query : queries)
			result[resultSize++] = (prefix[query[1]] - prefix[query[0]]) == (query[1] - query[0]);
		return result;
	}
}
