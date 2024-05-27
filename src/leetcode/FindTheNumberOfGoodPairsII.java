package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author Wit
 */
public class FindTheNumberOfGoodPairsII {
/*
You are given 2 integer arrays nums1 and nums2 of lengths n and m respectively. You are also given a positive integer k.
A pair (i, j) is called good if nums1[i] is divisible by nums2[j] * k (0 <= i <= n - 1, 0 <= j <= m - 1).
Return the total number of good pairs.

Example 1:
Input: nums1 = [1,3,4], nums2 = [1,3,4], k = 1
Output: 5
Explanation:
The 5 good pairs are (0, 0), (1, 0), (1, 1), (2, 0), and (2, 2).

Example 2:
Input: nums1 = [1,2,4,12], nums2 = [2,4], k = 3
Output: 2
Explanation:
The 2 good pairs are (3, 0) and (3, 1).

Constraints:
    1 <= n, m <= 10^5
    1 <= nums1[i], nums2[j] <= 10^6
    1 <= k <= 10^3
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{5L, new int[]{1, 3, 4}, new int[]{1, 3, 4}, 1},
				{2L, new int[]{1, 2, 4, 12}, new int[]{2, 4}, 3},
		});
	}

	public long numberOfPairs(int[] nums1, int[] nums2, int k) {
		long result = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i : nums2) map.merge(i * k, 1, Integer::sum);
		for (int i : nums1) {
			int j;
			for (j = 1; j * j < i; j++)
				if (i % j == 0) result += map.getOrDefault(j, 0) + map.getOrDefault(i / j, 0);
			if (j * j == i) result += map.getOrDefault(j, 0);
		}
		return result;
	}
}
