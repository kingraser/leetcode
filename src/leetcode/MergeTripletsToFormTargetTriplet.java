package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MergeTripletsToFormTargetTriplet {
/*
A triplet is an array of three integers. You are given a 2D integer array triplets, where triplets[i] = [ai, bi, ci] describes the ith triplet. You are also given an integer array target = [x, y, z] that describes the triplet you want to obtain.
To obtain target, you may apply the following operation on triplets any number of times (possibly zero):
    Choose two indices (0-indexed) i and j (i != j) and update triplets[j] to become [max(ai, aj), max(bi, bj), max(ci, cj)].
        For example, if triplets[i] = [2, 5, 3] and triplets[j] = [1, 7, 5], triplets[j] will be updated to [max(2, 1), max(5, 7), max(3, 5)] = [2, 7, 5].
Return true if it is possible to obtain the target triplet [x, y, z] as an element of triplets, or false otherwise.

Example 1:
Input: triplets = [[2,5,3],[1,8,4],[1,7,5]], target = [2,7,5]
Output: true
Explanation: Perform the following operations:
- Choose the first and last triplets [[2,5,3],[1,8,4],[1,7,5]]. Update the last triplet to be [max(2,1), max(5,7), max(3,5)] = [2,7,5]. triplets = [[2,5,3],[1,8,4],[2,7,5]]
The target triplet [2,7,5] is now an element of triplets.

Example 2:
Input: triplets = [[3,4,5],[4,5,6]], target = [3,2,5]
Output: false
Explanation: It is impossible to have [3,2,5] as an element because there is no 2 in any of the triplets.

Example 3:
Input: triplets = [[2,5,3],[2,3,4],[1,2,5],[5,2,3]], target = [5,5,5]
Output: true
Explanation: Perform the following operations:
- Choose the first and third triplets [[2,5,3],[2,3,4],[1,2,5],[5,2,3]]. Update the third triplet to be [max(2,1), max(5,2), max(3,5)] = [2,5,5]. triplets = [[2,5,3],[2,3,4],[2,5,5],[5,2,3]].
- Choose the third and fourth triplets [[2,5,3],[2,3,4],[2,5,5],[5,2,3]]. Update the fourth triplet to be [max(2,5), max(5,2), max(5,3)] = [5,5,5]. triplets = [[2,5,3],[2,3,4],[2,5,5],[5,5,5]].
The target triplet [5,5,5] is now an element of triplets.

Constraints:
    1 <= triplets.length <= 10^5
    triplets[i].length == target.length == 3
    1 <= ai, bi, ci, x, y, z <= 1000
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{false, TestUtil.getArray("[[3,1,7],[1,5,10]]"), new int[]{3, 5, 7}},
				{true, TestUtil.getArray("[[2,5,3],[1,8,4],[1,7,5]]"), new int[]{2, 7, 5}},
				{false, TestUtil.getArray("[[3,4,5],[4,5,6]]"), new int[]{3, 2, 5}},
				{true, TestUtil.getArray("[[2,5,3],[2,3,4],[1,2,5],[5,2,3]]"), new int[]{5, 5, 5}},
		});
	}

	public boolean mergeTriplets(int[][] triplets, int[] target) {
		int result = 0;
		A:
		for (int[] triplet : triplets) {
			int mask = 0;
			for (int i = 0; i < 3; i++)
				if (triplet[i] > target[i]) continue A;
				else if (triplet[i] == target[i]) mask |= 1 << i;
			if ((result |= mask) == 0b111) return true;
		}
		return false;
	}
}
