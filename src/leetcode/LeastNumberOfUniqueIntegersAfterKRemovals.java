package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.*;

/**
 * @author Wit
 */
public class LeastNumberOfUniqueIntegersAfterKRemovals {
	/*
	Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.

	Example 1:
	Input: arr = [5,5,4], k = 1
	Output: 1
	Explanation: Remove the single 4, only 5 is left.
	Example 2:
	Input: arr = [4,3,1,1,3,3,2], k = 3
	Output: 2
	Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.

	Constraints:
	1 <= arr.length <= 10^5
	1 <= arr[i] <= 10^9
	0 <= k <= arr.length
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, new int[]{5, 5, 4}, 1},
				{2, new int[]{4, 3, 1, 1, 3, 3, 2}, 3}
		});
	}

	public int findLeastNumOfUniqueInts(int[] arr, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i : arr) map.merge(i, 1, Integer::sum);
		int result = map.size();
		List<Integer> values = new ArrayList<>(result) {{addAll(map.values());}};
		Collections.sort(values);
		for (int value : values) {
			if ((k -= value) < 0) break;
			result--;
		}
		return result;
	}
}
