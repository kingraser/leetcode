package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.*;

/**
 * @author Wit
 */
public class KHighestRankedItemsWithinAPriceRange {
	/*
	You are given a 0-indexed 2D integer array grid of size m x n that represents a map of the items in a shop. The integers in the grid represent the following:
	0 represents a wall that you cannot pass through.
	1 represents an empty cell that you can freely move to and from.
	All other positive integers represent the price of an item in that cell. You may also freely move to and from these item cells.
	It takes 1 step to travel between adjacent grid cells.
	You are also given integer arrays pricing and start where pricing = [low, high] and start = [row, col] indicates that you start at the position (row, col) and are interested only in items with a price in the range of [low, high] (inclusive). You are further given an integer k.
	You are interested in the positions of the k highest-ranked items whose prices are within the given price range. The rank is determined by the first of these criteria that is different:
	Distance, defined as the length of the shortest path from the start (shorter distance has a higher rank).
	Price (lower price has a higher rank, but it must be in the price range).
	The row number (smaller row number has a higher rank).
	The column number (smaller column number has a higher rank).
	Return the k highest-ranked items within the price range sorted by their rank (highest to lowest). If there are fewer than k reachable items within the price range, return all of them.

	Example 1:
	Input: grid = [[1,2,0,1],[1,3,0,1],[0,2,5,1]], pricing = [2,5], start = [0,0], k = 3
	Output: [[0,1],[1,1],[2,1]]
	Explanation: You start at (0,0).
	With a price range of [2,5], we can take items from (0,1), (1,1), (2,1) and (2,2).
	The ranks of these items are:
	- (0,1) with distance 1
	- (1,1) with distance 2
	- (2,1) with distance 3
	- (2,2) with distance 4
	Thus, the 3 highest ranked items in the price range are (0,1), (1,1), and (2,1).

	Example 2:
	Input: grid = [[1,2,0,1],[1,3,3,1],[0,2,5,1]], pricing = [2,3], start = [2,3], k = 2
	Output: [[2,1],[1,2]]
	Explanation: You start at (2,3).
	With a price range of [2,3], we can take items from (0,1), (1,1), (1,2) and (2,1).
	The ranks of these items are:
	- (2,1) with distance 2, price 2
	- (1,2) with distance 2, price 3
	- (1,1) with distance 3
	- (0,1) with distance 4
	Thus, the 2 highest ranked items in the price range are (2,1) and (1,2).

	Example 3:
	Input: grid = [[1,1,1],[0,0,1],[2,3,4]], pricing = [2,3], start = [0,0], k = 3
	Output: [[2,1],[2,0]]
	Explanation: You start at (0,0).
	With a price range of [2,3], we can take items from (2,0) and (2,1).
	The ranks of these items are:
	- (2,1) with distance 5
	- (2,0) with distance 6
	Thus, the 2 highest ranked items in the price range are (2,1) and (2,0).
	Note that k = 3 but there are only 2 reachable items within the price range.

	Constraints:
	m == grid.length
	n == grid[i].length
	1 <= m, n <= 10^5
	1 <= m * n <= 10^5
	0 <= grid[i][j] <= 10^5
	pricing.length == 2
	2 <= low <= high <= 10^5
	start.length == 2
	0 <= row <= m - 1
	0 <= col <= n - 1
	grid[row][col] > 0
	1 <= k <= m * n
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TestUtil.transferToInteger("[[0,1],[1,1],[2,1]]"), TestUtil.getArray("[[1,2,0,1],[1,3,0,1],[0,2,5,1]]"), new int[]{2, 5}, new int[2], 3},
				{TestUtil.transferToInteger("[[2,1],[1,2]]"), TestUtil.getArray("[[1,2,0,1],[1,3,3,1],[0,2,5,1]]"), new int[]{2, 3}, new int[]{2, 3}, 2},
				{TestUtil.transferToInteger("[[2,1],[2,0]]"), TestUtil.getArray("[[1,1,1],[0,0,1],[2,3,4]]"), new int[]{2, 3}, new int[2], 3},
		});
	}

	public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
		// 0 for distance, 1 for price, 2 for row, 3 for col
		Queue<int[]> queue = new ArrayDeque<>() {{add(start);}};
		List<int[]> candidates = new ArrayList<>(k);
		for (int distance = 0, rowSize = grid.length, colSize = grid[0].length, low = pricing[0], high = pricing[1]; !queue.isEmpty(); distance++) {
			for (int size = queue.size(), poll[], row, col, price; size-- > 0; ) {
				if ((price = grid[row = (poll = queue.poll())[0]][col = poll[1]]) == 0) continue;
				if (price >= low && price <= high) candidates.add(new int[]{distance, price, row, col});
				if (row - 1 >= 0 && grid[row - 1][col] > 0) queue.add(new int[]{row - 1, col});
				if (col - 1 >= 0 && grid[row][col - 1] > 0) queue.add(new int[]{row, col - 1});
				if (row + 1 < rowSize && grid[row + 1][col] > 0) queue.add(new int[]{row + 1, col});
				if (col + 1 < colSize && grid[row][col + 1] > 0) queue.add(new int[]{row, col + 1});
				grid[row][col] = 0;
			}
			if (candidates.size() >= k) break;
		}
		candidates.sort((a1, a2) -> {
			for (int i = 0; i < 4; i++) if (a1[i] != a2[i]) return a1[i] - a2[i];
			return 0;
		});
		List<List<Integer>> result = new ArrayList<>(candidates.size());
		for (int i = 0, size = Math.min(k, candidates.size()), poll[]; i < size; ) {
			List<Integer> cell = new ArrayList<>();
			cell.add((poll = candidates.get(i++))[2]);
			cell.add(poll[3]);
			result.add(cell);
		}
		return result;
	}
}
