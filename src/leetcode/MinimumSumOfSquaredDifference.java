package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Wit
 */
public class MinimumSumOfSquaredDifference {
	/*
	You are given two positive 0-indexed integer arrays nums1 and nums2, both of length n.
	The sum of squared difference of arrays nums1 and nums2 is defined as the sum of (nums1[i] - nums2[i])^2 for each 0 <= i < n.
	You are also given two positive integers k1 and k2. You can modify any of the elements of nums1 by +1 or -1 at most k1 times. Similarly, you can modify any of the elements of nums2 by +1 or -1 at most k2 times.
	Return the minimum sum of squared difference after modifying array nums1 at most k1 times and modifying array nums2 at most k2 times.
	Note: You are allowed to modify the array elements to become negative integers.

	Example 1:
	Input: nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
	Output: 579
	Explanation: The elements in nums1 and nums2 cannot be modified because k1 = 0 and k2 = 0.
	The sum of square difference will be: (1 - 2)^2 + (2 - 10)^2 + (3 - 20)^2 + (4 - 19)^2 = 579.

	Example 2:
	Input: nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
	Output: 43
	Explanation: One way to obtain the minimum sum of square difference is:
	- Increase nums1[0] once.
	- Increase nums2[2] once.
	The minimum of the sum of square difference will be:
	(2 - 5)^2 + (4 - 8)^2 + (10 - 7)^2 + (12 - 9)^2 = 43.
	Note that, there are other ways to obtain the minimum of the sum of square difference, but there is no way to obtain a sum smaller than 43.

	Constraints:
	n == nums1.length == nums2.length
	1 <= n <= 10^5
	0 <= nums1[i], nums2[i] <= 10^5
	0 <= k1, k2 <= 10^9
	*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{13L, new int[]{1, 2, 3, 4}, new int[]{2, 4, 5, 7}, 0, 1},
				{579L, new int[]{1, 2, 3, 4}, new int[]{2, 10, 20, 19}, 0, 0},
				{43L, new int[]{1, 4, 10, 12}, new int[]{5, 8, 6, 9}, 1, 1},
		});
	}

	public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
		TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
		for (int i = 0, abs; i < nums1.length; i++)
			if ((abs = Math.abs(nums1[i] - nums2[i])) > 0) map.merge(abs, 1, Integer::sum);
		for (int k = k1 + k2, max, count, second, step, remain; k > 0 && !map.isEmpty(); ) {
			Map.Entry<Integer, Integer> entry = map.pollFirstEntry();
			max = entry.getKey();
			if ((count = entry.getValue()) >= k || (step = (max - (second = map.isEmpty() ? 0 : map.firstKey())) * count) > k) {
				step = k / count;
				if ((count -= (remain = k % count)) > 0) map.merge(max - step, count, Integer::sum);
				if (max - step - 1 > 0) map.merge(max - step - 1, remain, Integer::sum);
				break;
			} else {
				k -= step;
				if (second > 0) map.merge(second, count, Integer::sum);
			}
		}
		long result = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet())
			result += ((long) entry.getValue()) * entry.getKey() * entry.getKey();
		return result;
	}
}
