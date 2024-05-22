package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author Wit
 */
public class MaximumDifferenceScoreInAGrid {
/*
You are given an m x n matrix grid consisting of positive integers. You can move from a cell in the matrix to any other cell that is either to the bottom or to the right (not necessarily adjacent). The score of a move from a cell with the value c1 to a cell with the value c2 is c2 - c1.
You can start at any cell, and you have to make at least one move.
Return the maximum total score you can achieve.

Example 1:
Input: grid = [[9,5,7,3],[8,9,6,1],[6,7,14,3],[2,5,3,1]]
Output: 9
Explanation: We start at the cell (0, 1), and we perform the following moves:
- Move from the cell (0, 1) to (2, 1) with a score of 7 - 5 = 2.
- Move from the cell (2, 1) to (2, 2) with a score of 14 - 7 = 7.
The total score is 2 + 7 = 9.

Example 2:
Input: grid = [[4,3,2],[3,2,1]]
Output: -1
Explanation: We start at the cell (0, 0), and we perform one move: (0, 0) to (0, 1). The score is 3 - 4 = -1.

Constraints:
    m == grid.length
    n == grid[i].length
    2 <= m, n <= 1000
    4 <= m * n <= 10^5
    1 <= grid[i][j] <= 105
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{9, TestUtil.transferToInteger("[[9,5,7,3],[8,9,6,1],[6,7,14,3],[2,5,3,1]]")},
				{-1, TestUtil.transferToInteger("[[4,3,2],[3,2,1]]")},
		});
	}

	//Any path from a cell (x1, y1) to another cell (x2, y2) will always have a score of grid[x2][y2] - grid[x1][y1].
	//Let’s say we fix the starting cell (x1, y1), how to the find a cell (x2, y2) such that the value grid[x2][y2] - grid[x1][y1] is the maximum possible?
	public int maxScore(List<List<Integer>> grid) {
		int limit = 1000_000, res = -limit, rowSize = grid.size(), colSize = grid.get(0).size();
		for (int row = 0; row < rowSize; row++)
			for (int col = 0; col < colSize; col++) {
				int minPre = Math.min(
						row > 0 ? grid.get(row - 1).get(col) : limit,
						col > 0 ? grid.get(row).get(col - 1) : limit
				);
				res = Math.max(res, grid.get(row).get(col) - minPre);
				if (minPre < grid.get(row).get(col)) grid.get(row).set(col, minPre);
			}
		return res;
	}
}
