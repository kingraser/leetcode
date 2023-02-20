package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class MergeTwo2DArraysBySummingValues {
	/*
	You are given two 2D integer arrays nums1 and nums2.

	nums1[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
	nums2[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
	Each array contains unique ids and is sorted in ascending order by id.

	Merge the two arrays into one array that is sorted in ascending order by id, respecting the following conditions:
	Only ids that appear in at least one of the two arrays should be included in the resulting array.
	Each id should be included only once and its value should be the sum of the values of this id in the two arrays. If the id does not exist in one of the two arrays then its value in that array is considered to be 0.
	Return the resulting array. The returned array must be sorted in ascending order by id.

	Example 1:
	Input: nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
	Output: [[1,6],[2,3],[3,2],[4,6]]
	Explanation: The resulting array contains the following:
	- id = 1, the value of this id is 2 + 4 = 6.
	- id = 2, the value of this id is 3.
	- id = 3, the value of this id is 2.
	- id = 4, the value of this id is 5 + 1 = 6.

	Example 2:
	Input: nums1 = [[2,4],[3,6],[5,5]], nums2 = [[1,3],[4,3]]
	Output: [[1,3],[2,4],[3,6],[4,3],[5,5]]
	Explanation: There are no common ids, so we just include each id with its value in the resulting list.

	Constraints:
	1 <= nums1.length, nums2.length <= 200
	nums1[i].length == nums2[j].length == 2
	1 <= idi, vali <= 1000
	Both arrays contain unique ids.
	Both arrays are in strictly ascending order by id.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TestUtil.getArray("[[1,6],[2,3],[3,2],[4,6]]"), TestUtil.getArray("[[1,2],[2,3],[4,5]]"), TestUtil.getArray("[[1,4],[3,2],[4,1]]")},
				{TestUtil.getArray("[[1,3],[2,4],[3,6],[4,3],[5,5]]"), TestUtil.getArray("[[2,4],[3,6],[5,5]]"), TestUtil.getArray("[[1,3],[4,3]]")},
		});
	}

	public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
		int idx = 0, idx1 = 0, idx2 = 0, result[][] = new int[nums1.length + nums2.length][2];
		while (idx1 < nums1.length)
			if (idx2 >= nums2.length || nums1[idx1][0] < nums2[idx2][0]) result[idx++] = nums1[idx1++];
			else if (nums1[idx1][0] == nums2[idx2][0]) {
				result[idx][0] = nums1[idx1][0];
				result[idx++][1] = nums1[idx1++][1] + nums2[idx2++][1];
			} else result[idx++] = nums2[idx2++];
		while (idx2 < nums2.length) result[idx++] = nums2[idx2++];
		return Arrays.copyOf(result, idx);
	}
}
