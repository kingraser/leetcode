package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class QueryKthSmallestTrimmedNumber {
	/*
	You are given a 0-indexed array of strings nums, where each string is of equal length and consists of only digits.
	You are also given a 0-indexed 2D integer array queries where queries[i] = [ki, trimi]. For each queries[i], you need to:
	Trim each number in nums to its rightmost trimi digits.
	Determine the index of the kith smallest trimmed number in nums. If two trimmed numbers are equal, the number with the lower index is considered to be smaller.
	Reset each number in nums to its original length.
	Return an array answer of the same length as queries, where answer[i] is the answer to the ith query.
	Note:
	To trim to the rightmost x digits means to keep removing the leftmost digit, until only x digits remain.
	Strings in nums may contain leading zeros.

	Example 1:
	Input: nums = ["102","473","251","814"], queries = [[1,1],[2,3],[4,2],[1,2]]
	Output: [2,2,1,0]
	Explanation:
	1. After trimming to the last digit, nums = ["2","3","1","4"]. The smallest number is 1 at index 2.
	2. Trimmed to the last 3 digits, nums is unchanged. The 2nd smallest number is 251 at index 2.
	3. Trimmed to the last 2 digits, nums = ["02","73","51","14"]. The 4th smallest number is 73.
	4. Trimmed to the last 2 digits, the smallest number is 2 at index 0.
	   Note that the trimmed number "02" is evaluated as 2.

	Example 2:
	Input: nums = ["24","37","96","04"], queries = [[2,1],[2,2]]
	Output: [3,0]
	Explanation:
	1. Trimmed to the last digit, nums = ["4","7","6","4"]. The 2nd smallest number is 4 at index 3.
	   There are two occurrences of 4, but the one at index 0 is considered smaller than the one at index 3.
	2. Trimmed to the last 2 digits, nums is unchanged. The 2nd smallest number is 24.

	Constraints:
	1 <= nums.length <= 100
	1 <= nums[i].length <= 100
	nums[i] consists of only digits.
	All nums[i].length are equal.
	1 <= queries.length <= 100
	queries[i].length == 2
	1 <= ki <= nums.length
	1 <= trimi <= nums[i].length

	Follow up: Could you use the Radix Sort Algorithm to solve this problem? What will be the complexity of that solution?
	*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{0, 1, 2, 3}, new String[]{"9415", "5908", "1840", "5307"}, TestUtil.getArray("[[3,2],[2,2],[3,3],[1,3]]")},
				{new int[]{2, 2, 1, 0}, new String[]{"102", "473", "251", "814"}, TestUtil.getArray("[[1,1],[2,3],[4,2],[1,2]]")},
				{new int[]{3, 0}, new String[]{"24", "37", "96", "04"}, TestUtil.getArray("[[2,1],[2,2]]")},
		});
	}

	public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
		int ans[] = new int[queries.length], round = 0;
		for (int[] query : queries) round = Integer.max(round, query[1]);
		List<List<Integer>> list = radixSort(nums, round);
		for (int i = 0; i < queries.length; i++) ans[i] = list.get(queries[i][1] - 1).get(queries[i][0] - 1);
		return ans;
	}

	List<List<Integer>> radixSort(String[] nums, int roundSize) {
		List<List<Integer>> result = new ArrayList<>();
		for (int round = 0, numLength = nums[0].length(); round < roundSize; round++) {
			List<Integer>[] buckets = new List[10];
			for (int i = 0; i < 10; i++) buckets[i] = new ArrayList<>();
			for (int j = 0; j < nums.length; j++) {
				int idx = round == 0 ? j : result.get(round - 1).get(j);
				String s = nums[idx];
				buckets[s.charAt(numLength - round - 1) - '0'].add(idx);
			}
			List<Integer> currentRound = new ArrayList<>(nums.length);
			for (List<Integer> bucket : buckets) currentRound.addAll(bucket);
			result.add(currentRound);
		}
		return result;
	}
}
