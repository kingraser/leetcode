package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wit
 */
public class EqualRowAndColumnPairs {
	/*
	Given a 0-indexed n x n integer matrix grid, return the number of pairs (Ri, Cj) such that row Ri and column Cj are equal.
	A row and column pair is considered equal if they contain the same elements in the same order (i.e. an equal array).

	Example 1:
	Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
	Output: 1
	Explanation: There is 1 equal row and column pair:
	- (Row 2, Column 1): [2,7,7]

	Example 2:
	Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
	Output: 3
	Explanation: There are 3 equal row and column pairs:
	- (Row 0, Column 0): [3,1,2,2]
	- (Row 2, Column 2): [2,4,2,2]
	- (Row 3, Column 2): [2,4,2,2]

	Constraints:
	n == grid.length == grid[i].length
	1 <= n <= 200
	1 <= grid[i][j] <= 10^5
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, TestUtil.getArray("[[3,2,1],[1,7,6],[2,7,7]]")},
				{3, TestUtil.getArray("[[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]")}
		});
	}

	public int equalPairs(int[][] grid) {
		Map<String, Integer> map = new HashMap<>();
		for (int[] row : grid) map.merge(Arrays.toString(row), 1, Integer::sum);
		int pairs = 0;
		for (int c = 0, n = grid.length; c < n; ++c) {
			int[] col = new int[n];
			for (int r = 0; r < n; ++r) col[r] = grid[r][c];
			pairs += map.getOrDefault(Arrays.toString(col), 0);
		}
		return pairs;
	}

}
