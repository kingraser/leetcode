package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author Wit
 */
public class LargestValuesFromLabels {
/*
You are given n item's value and label as two integer arrays values and labels. You are also given two integers numWanted and useLimit.
Your task is to find a subset of items with the maximum sum of their values such that:
    The number of items is at most numWanted.
    The number of items with the same label is at most useLimit.
Return the maximum sum.

Example 1:
Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1
Output: 9
Explanation:
The subset chosen is the first, third, and fifth items with the sum of values 5 + 3 + 1.

Example 2:
Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2
Output: 12
Explanation:
The subset chosen is the first, second, and third items with the sum of values 5 + 4 + 3.

Example 3:
Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1
Output: 16
Explanation:
The subset chosen is the first and fourth items with the sum of values 9 + 7.

Constraints:
    n == values.length == labels.length
    1 <= n <= 2 * 10^4
    0 <= values[i], labels[i] <= 2 * 104
    1 <= numWanted, useLimit <= n
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{9, new int[]{5, 4, 3, 2, 1}, new int[]{1, 1, 2, 2, 3}, 3, 1},
				{12, new int[]{5, 4, 3, 2, 1}, new int[]{1, 3, 3, 3, 2}, 3, 2},
				{16, new int[]{9, 8, 8, 7, 6}, new int[]{0, 0, 0, 1, 1}, 3, 1},
		});
	}

	public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
		int result = 0, length = values.length, map[][] = new int[length][2];
		for (int i = 0; i < length; map[i][1] = labels[i++]) map[i][0] = values[i];
		Arrays.sort(map, Comparator.comparingInt(a -> a[0]));
		HashMap<Integer, Integer> countMap = new HashMap<>();
		for (int i = length - 1; i >= 0 && numWanted > 0; i--) {
			if (countMap.merge(map[i][1], 1, Integer::sum) > useLimit) continue;
			result += map[i][0];
			numWanted--;
		}
		return result;
	}
}
