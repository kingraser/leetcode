package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class FindOccurrencesOfAnElementInAnArray {
/*
You are given an integer array nums, an integer array queries, and an integer x.
For each queries[i], you need to find the index of the queries[i]th occurrence of x in the nums array. If there are fewer than queries[i] occurrences of x, the answer should be -1 for that query.
Return an integer array answer containing the answers to all queries.

Example 1:
Input: nums = [1,3,1,7], queries = [1,3,2,4], x = 1
Output: [0,-1,2,-1]
Explanation:
    For the 1st query, the first occurrence of 1 is at index 0.
    For the 2nd query, there are only two occurrences of 1 in nums, so the answer is -1.
    For the 3rd query, the second occurrence of 1 is at index 2.
    For the 4th query, there are only two occurrences of 1 in nums, so the answer is -1.

Example 2:
Input: nums = [1,2,3], queries = [10], x = 5
Output: [-1]
Explanation:
    For the 1st query, 5 doesn't exist in nums, so the answer is -1.

Constraints:
    1 <= nums.length, queries.length <= 10^5
    1 <= queries[i] <= 10^5
    1 <= nums[i], x <= 10^4
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{0, -1, 2, -1}, new int[]{1, 3, 1, 7}, new int[]{1, 3, 2, 4}, 1},
				{new int[]{-1}, new int[]{1, 2, 3}, new int[]{10}, 5},
		});
	}

	public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
		int result[] = new int[queries.length], resultSize = 0;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) if (nums[i] == x) list.add(i);
		for (int query : queries) result[resultSize++] = query > list.size() ? -1 : list.get(query - 1);
		return result;
	}
}
