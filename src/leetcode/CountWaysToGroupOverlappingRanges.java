package leetcode;

import leetcode.util.MathUtil;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Wit
 */
public class CountWaysToGroupOverlappingRanges {
	/*
	You are given a 2D integer array ranges where ranges[i] = [starti, endi] denotes that all integers between starti and endi (both inclusive) are contained in the ith range.

	You are to split ranges into two (possibly empty) groups such that:

	Each range belongs to exactly one group.
	Any two overlapping ranges must belong to the same group.
	Two ranges are said to be overlapping if there exists at least one integer that is present in both ranges.

	For example, [1, 3] and [2, 5] are overlapping because 2 and 3 occur in both ranges.
	Return the total number of ways to split ranges into two groups. Since the answer may be very large, return it modulo 10^9 + 7.

	Example 1:
	Input: ranges = [[6,10],[5,15]]
	Output: 2
	Explanation:
	The two ranges are overlapping, so they must be in the same group.
	Thus, there are two possible ways:
	- Put both the ranges together in group 1.
	- Put both the ranges together in group 2.

	Example 2:
	Input: ranges = [[1,3],[10,20],[2,5],[4,8]]
	Output: 4
	Explanation:
	Ranges [1,3], and [2,5] are overlapping. So, they must be in the same group.
	Again, ranges [2,5] and [4,8] are also overlapping. So, they must also be in the same group.
	Thus, there are four possible ways to group them:
	- All the ranges in group 1.
	- All the ranges in group 2.
	- Ranges [1,3], [2,5], and [4,8] in group 1 and [10,20] in group 2.
	- Ranges [1,3], [2,5], and [4,8] in group 2 and [10,20] in group 1.

	Constraints:
	1 <= ranges.length <= 10^5
	ranges[i].length == 2
	0 <= starti <= endi <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, TestUtil.getArray("[[6,10],[5,15]]")},
				{4, TestUtil.getArray("[[1,3],[10,20],[2,5],[4,8]]")},
		});
	}

	public int countWays(int[][] ranges) {
		Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));
		int count = 1, preRangeEnd = ranges[0][1];
		for (int i = 1; i < ranges.length; i++)
			if (ranges[i][0] > preRangeEnd) {
				count++;
				preRangeEnd = ranges[i][1];
			} else preRangeEnd = Integer.max(preRangeEnd, ranges[i][1]);
		return MathUtil.fastPow(2, count, 1000_000_007);
	}




}
