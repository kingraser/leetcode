package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FormSmallestNumberFromTwoDigitArrays {
	/*
	Given two arrays of unique digits nums1 and nums2, return the smallest number that contains at least one digit from each array.

	Example 1:
	Input: nums1 = [4,1,3], nums2 = [5,7]
	Output: 15
	Explanation: The number 15 contains the digit 1 from nums1 and the digit 5 from nums2. It can be proven that 15 is the smallest number we can have.

	Example 2:
	Input: nums1 = [3,5,2,6], nums2 = [3,1,7]
	Output: 3
	Explanation: The number 3 contains the digit 3 which exists in both arrays.

	Constraints:
	1 <= nums1.length, nums2.length <= 9
	1 <= nums1[i], nums2[i] <= 9
	All digits in each array are unique.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{15, new int[]{4, 1, 3}, new int[]{5, 7}},
				{3, new int[]{3, 5, 2, 6}, new int[]{3, 1, 7}},
		});
	}

	public int minNumber(int[] nums1, int[] nums2) {
		int digits[] = new int[10], first = 10, second = 10;
		for (int num : nums1) {
			digits[num]++;
			first = Integer.min(first, num);
		}
		for (int num : nums2) {
			digits[num]++;
			second = Integer.min(second, num);
		}
		for (int i = 1; i < 10; i++) if (digits[i] == 2) return i;
		return first < second ? 10 * first + second : 10 * second + first;
	}
}
