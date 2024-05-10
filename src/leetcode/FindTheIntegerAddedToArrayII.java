package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class FindTheIntegerAddedToArrayII {
/*
You are given two integer arrays nums1 and nums2.
From nums1 two elements have been removed, and all other elements have been increased (or decreased in the case of negative) by an integer, represented by the variable x.
As a result, nums1 becomes equal to nums2. Two arrays are considered equal when they contain the same integers with the same frequencies.
Return the minimum possible integer x that achieves this equivalence.

Example 1:
Input: nums1 = [4,20,16,12,8], nums2 = [14,18,10]
Output: -2
Explanation:
After removing elements at indices [0,4] and adding -2, nums1 becomes [18,14,10].

Example 2:
Input: nums1 = [3,5,5,3], nums2 = [7,7]
Output: 2
Explanation:
After removing elements at indices [0,3] and adding 2, nums1 becomes [7,7].

Constraints:
    3 <= nums1.length <= 200
    nums2.length == nums1.length - 2
    0 <= nums1[i], nums2[i] <= 1000
    The test cases are generated in a way that there is an integer x such that nums1 can become equal to nums2 by removing two elements and adding x to each element of nums1.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{-2, new int[]{4, 20, 16, 12, 8}, new int[]{14, 18, 10}},
				{2, new int[]{3, 5, 5, 3}, new int[]{7, 7}},
		});
	}

	public int minimumAddedInteger(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		for (int start = 2, diff; start > 0; )
			if (isRightStart(nums1, nums2, start, diff = nums2[0] - nums1[start--])) return diff;
		return nums2[0] - nums1[0];
	}

	boolean isRightStart(int[] nums1, int[] nums2, int skip, int diff) {
		for (int i1 = skip + 1, i2 = 1; i2 < nums2.length; )
			if (nums2[i2] - nums1[i1++] == diff) i2++;
			else if (++skip > 2) return false;
		return true;
	}
}
