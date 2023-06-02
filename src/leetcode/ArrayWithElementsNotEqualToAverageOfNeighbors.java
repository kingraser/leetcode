package leetcode;

import leetcode.util.TestUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class ArrayWithElementsNotEqualToAverageOfNeighbors {
	/*
	You are given a 0-indexed array nums of distinct integers. You want to rearrange the elements in the array such that every element in the rearranged array is not equal to the average of its neighbors.
	More formally, the rearranged array should have the property such that for every i in the range 1 <= i < nums.length - 1, (nums[i-1] + nums[i+1]) / 2 is not equal to nums[i].
	Return any rearrangement of nums that meets the requirements.

	Example 1:
	Input: nums = [1,2,3,4,5]
	Output: [1,2,4,5,3]
	Explanation:
	When i=1, nums[i] = 2, and the average of its neighbors is (1+4) / 2 = 2.5.
	When i=2, nums[i] = 4, and the average of its neighbors is (2+5) / 2 = 3.5.
	When i=3, nums[i] = 5, and the average of its neighbors is (4+3) / 2 = 3.5.

	Example 2:
	Input: nums = [6,2,0,9,7]
	Output: [9,7,6,2,0]
	Explanation:
	When i=1, nums[i] = 7, and the average of its neighbors is (9+6) / 2 = 7.5.
	When i=2, nums[i] = 6, and the average of its neighbors is (7+2) / 2 = 4.5.
	When i=3, nums[i] = 2, and the average of its neighbors is (6+0) / 2 = 3.

	Constraints:
	3 <= nums.length <= 10^5
	0 <= nums[i] <= 10^5
	*/
	@Test
	public void test() {
		TestUtil.test(new Object[][]{
				{new int[]{1, 2, 3, 4, 5}},
				{new int[]{6, 2, 0, 9, 7}}
		}, (inputs, results) -> Assert.assertTrue(isValid((int[]) results)));
	}

	//A number can be the average of its neighbors if one neighbor is smaller than the number and the other is greater than the number.
	//We can put numbers smaller than the median on odd indices and the rest on even indices.
	public int[] rearrangeArray(int[] nums) {
		boolean order = nums[0] < nums[1];
		for (int i = 1, last = nums.length - 1; i < last; i++, order = !order)
			if ((order == nums[i] < nums[i + 1])) {
				int temp = nums[i];
				nums[i] = nums[i + 1];
				nums[i + 1] = temp;
			}
		return nums;

	}

	private boolean isValid(int[] nums) {
		for (int i = 1, last = nums.length - 1; i < last; i++)
			if (nums[i] << 1 == nums[i - 1] + nums[i + 1]) return false;
		return true;
	}
}
