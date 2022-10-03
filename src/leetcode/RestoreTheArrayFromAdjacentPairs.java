package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wit
 */
public class RestoreTheArrayFromAdjacentPairs {
	/*
	There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.
	You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.
	It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
	Return the original array nums. If there are multiple solutions, return any of them.

	Example 1:
	Input: adjacentPairs = [[2,1],[3,4],[3,2]]
	Output: [1,2,3,4]
	Explanation: This array has all its adjacent pairs in adjacentPairs.
	Notice that adjacentPairs[i] may not be in left-to-right order.

	Example 2:
	Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
	Output: [-2,4,1,-3]
	Explanation: There can be negative numbers.
	Another solution is [-3,1,4,-2], which would also be accepted.

	Example 3:
	Input: adjacentPairs = [[100000,-100000]]
	Output: [100000,-100000]

	Constraints:
	nums.length == n
	adjacentPairs.length == n - 1
	adjacentPairs[i].length == 2
	2 <= n <= 10^5
	-10^5 <= nums[i], ui, vi <= 10^5
	There exists some nums that has adjacentPairs as its pairs.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{4, 3, 2, 1}, TestUtil.getArray("[[2,1],[3,4],[3,2]]")},
				{new int[]{100000, -100000}, TestUtil.getArray("[[100000,-100000]]")}
		});
	}

	public int[] restoreArray(int[][] adjacentPairs) {
		int length = adjacentPairs.length + 1, result[] = new int[length], xor = 0, start = 0, end = 0;
		Map<Integer, int[]> map = new HashMap<>(length << 1);
		for (int[] pair : adjacentPairs) {
			for (int num : pair) xor ^= num;
			link(map.computeIfAbsent(pair[0], k -> new int[3]), pair[1]);
			link(map.computeIfAbsent(pair[1], k -> new int[3]), pair[0]);
		}
		int lastBit = xor & -xor;
		for (int[] pair : adjacentPairs)
			for (int num : pair)
				if ((num & lastBit) == 0) start ^= num;
				else end ^= num;
		for (int i = 0, current = start == 0 ? end : start, prev = Integer.MIN_VALUE; i < length; )
			current = getNext(prev, map.get(prev = result[i++] = current));
		return result;
	}

	int getNext(int prev, int[] neighbours) {return neighbours[1] == prev ? neighbours[2] : neighbours[1];}

	void link(int[] neighbours, int neighbour) {neighbours[++neighbours[0]] = neighbour;}
}
