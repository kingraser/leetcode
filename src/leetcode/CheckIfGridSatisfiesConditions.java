package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CheckIfGridSatisfiesConditions {
/*
You are given a 2D matrix grid of size m x n. You need to check if each cell grid[i][j] is:
    Equal to the cell below it, i.e. grid[i][j] == grid[i + 1][j] (if it exists).
    Different from the cell to its right, i.e. grid[i][j] != grid[i][j + 1] (if it exists).
Return true if all the cells satisfy these conditions, otherwise, return false.

Example 1:
Input: grid = [[1,0,2],[1,0,2]]
Output: true
Explanation:
All the cells in the grid satisfy the conditions.

Example 2:
Input: grid = [[1,1,1],[0,0,0]]
Output: false
Explanation:
All cells in the first row are equal.

Example 3:
Input: grid = [[1],[2],[3]]
Output: false
Explanation:
Cells in the first column have different values.

Constraints:
    1 <= n, m <= 10
    0 <= grid[i][j] <= 9
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, TestUtil.getArray("[[1,0,2],[1,0,2]]")},
				{false, TestUtil.getArray("[[1,1,1],[0,0,0]]")},
				{false, TestUtil.getArray("[[1],[2],[3]]")},
		});
	}

	public boolean satisfiesConditions(int[][] grid) {
		for (int col = 0, last = grid[0].length - 1; col < last; ) if (grid[0][col] == grid[0][++col]) return false;
		for (int col = 0, colSize = grid[0].length; col < colSize; col++)
			for (int row = 1, rowSize = grid.length; row < rowSize; row++)
				if (grid[row][col] != grid[0][col]) return false;
		return true;
	}
}
