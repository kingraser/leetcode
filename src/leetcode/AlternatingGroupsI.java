package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class AlternatingGroupsI {
/*
There is a circle of red and blue tiles. You are given an array of integers colors. The color of tile i is represented by colors[i]:
    colors[i] == 0 means that tile i is red.
    colors[i] == 1 means that tile i is blue.
Every 3 contiguous tiles in the circle with alternating colors (the middle tile has a different color from its left and right tiles) is called an alternating group.
Return the number of alternating groups.
Note that since colors represents a circle, the first and the last tiles are considered to be next to each other.

Example 1:
Input: colors = [1,1,1]
Output: 0
Explanation:

Example 2:
Input: colors = [0,1,0,0,1]
Output: 3
Explanation:
Alternating groups:

Constraints:
    3 <= colors.length <= 100
    0 <= colors[i] <= 1
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{0, new int[]{1, 1, 1}},
				{3, new int[]{0, 1, 0, 0, 1}},
		});
	}

	public int numberOfAlternatingGroups(int[] colors) {
		int result = 0, length = colors.length, last = length - 1, prev = colors[0] ^ colors[1];
		if (prev == 1 && colors[0] != colors[last]) result++;
		for (int i = 1; i < last; )
			if (prev == 0) prev = colors[i++] ^ colors[i];
			else if ((prev = colors[i++] ^ colors[i]) == 1) result++;
		if (prev == 1 && colors[0] != colors[last]) result++;
		return result;
	}
}
