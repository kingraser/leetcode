package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Wit
 */
public class AdvantageShuffle {
	/*
	You are given two integer arrays nums1 and nums2 both of the same length. The advantage of nums1 with respect to nums2 is the number of indices i for which nums1[i] > nums2[i].
	Return any permutation of nums1 that maximizes its advantage with respect to nums2.

	Example 1:
	Input: nums1 = [2,7,11,15], nums2 = [1,10,4,11]
	Output: [2,11,7,15]

	Example 2:
	Input: nums1 = [12,24,8,32], nums2 = [13,25,32,11]
	Output: [24,32,8,12]

	Constraints:
	1 <= nums1.length <= 10^5
	nums2.length == nums1.length
	0 <= nums1[i], nums2[i] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{2, 11, 7, 15}, new int[]{2, 7, 11, 15}, new int[]{1, 10, 4, 11}},
				{new int[]{24, 32, 8, 12}, new int[]{12, 24, 8, 32}, new int[]{13, 25, 32, 11}},
		});
	}

	public int[] advantageCount(int[] nums1, int[] nums2) {
		int length = nums1.length, result[] = new int[length];
		Integer[] indexes = new Integer[length];
		for (int i = 0; i < length; ) indexes[i] = i++;
		Arrays.sort(nums1);
		Arrays.sort(indexes, Comparator.comparingInt(i -> nums2[i]));
		for (int low = 0, high = length - 1, index = high; index >= 0; index--)
			result[indexes[index]] = nums1[nums2[indexes[index]] < nums1[high] ? high-- : low++];
		return result;
	}
}
