package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Wit
 */
public class MaximumWhiteTilesCoveredByACarpet {
	/*
	You are given a 2D integer array tiles where tiles[i] = [li, ri] represents that every tile j in the range li <= j <= ri is colored white.
	You are also given an integer carpetLen, the length of a single carpet that can be placed anywhere.
	Return the maximum number of white tiles that can be covered by the carpet.

	Example 1:
	Input: tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
	Output: 9
	Explanation: Place the carpet starting on tile 10.
	It covers 9 white tiles, so we return 9.
	Note that there may be other places where the carpet covers 9 white tiles.
	It can be shown that the carpet cannot cover more than 9 white tiles.

	Example 2:
	Input: tiles = [[10,11],[1,1]], carpetLen = 2
	Output: 2
	Explanation: Place the carpet starting on tile 10.
	It covers 2 white tiles, so we return 2.

	Constraints:
	1 <= tiles.length <= 5 * 10^4
	tiles[i].length == 2
	1 <= li <= ri <= 10^9
	1 <= carpetLen <= 10^9
	The tiles are non-overlapping.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{9, TestUtil.getArray("[[1,5],[10,11],[12,18],[20,25],[30,32]]"), 10},
				{2, TestUtil.getArray("[[10,11],[1,1]]"), 2},
		});
	}

	public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
		int result = 0;
		Arrays.sort(tiles, Comparator.comparingInt(a -> a[0]));
		for (int left = 0, right = 0, cover = 0, start = 0, end = 1; result < carpetLen && right < tiles.length; )
			if (tiles[left][start] + carpetLen <= tiles[right][end]) {
				result = Math.max(result, cover + Math.max(0, tiles[left][start] + carpetLen - tiles[right][start]));
				cover -= getLen(tiles[left++]);
			} else result = Math.max(result, cover += getLen(tiles[right++]));
		return result;
	}


	int getLen(int[] tile) {return tile[1] - tile[0] + 1;}
}
