package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

/**
 * @author Wit
 */
public class PointsThatIntersectWithCars {
	/*
	You are given a 0-indexed 2D integer array nums representing the coordinates of the cars parking on a number line. For any index i, nums[i] = [start_i, end_i] where start_i is the start_ing point of the ith car and end_i is the end_ing point of the ith car.
	Return the number of integer points on the line that are covered with any part of a car.

	Example 1:
	Input: nums = [[3,6],[1,5],[4,7]]
	Output: 7
	Explanation: All the points from 1 to 7 intersect at least one car, therefore the answer would be 7.

	Example 2:
	Input: nums = [[1,3],[5,8]]
	Output: 7
	Explanation: Points intersecting at least one car are 1, 2, 3, 5, 6, 7, 8. There are a total of 7 points, therefore the answer would be 7.

	Constraints:
	1 <= nums.length <= 100
	nums[i].length == 2
	1 <= start_i <= end_i <= 100
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{10, TestUtil.transferToInteger("[[2,5],[3,8],[1,6],[4,10]]")},
				{7, TestUtil.transferToInteger("[[3,6],[1,5],[4,7]]")},
				{7, TestUtil.transferToInteger("[[1,3],[5,8]]")},
		});
	}

	public int numberOfPoints(List<List<Integer>> nums) {
		int result = 0, last = 0;
		nums.sort(Comparator.comparingInt(l -> l.get(0)));
		for (List<Integer> num : nums) {
			result += Math.max(0, num.get(1) - Math.max(num.get(0), last) + 1);
			last = Math.max(last, num.get(1) + 1);
		}
		return result;
	}

	public int numberOfPointsII(List<List<Integer>> nums) {
		int result = 0, sum[] = new int[102];
		for (List<Integer> num : nums) {
			sum[num.get(0)]++;
			sum[num.get(1) + 1]--;
		}
		for (int i = 1, covered = 0; i <= 100; i++) if ((covered += sum[i]) > 0) result++;
		return result;
	}
}
