package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumHeightOfATriangle {
/*
You are given two integers red and blue representing the count of red and blue colored balls. You have to arrange these balls to form a triangle such that the 1st row will have 1 ball, the 2nd row will have 2 balls, the 3rd row will have 3 balls, and so on.
All the balls in a particular row should be the same color, and adjacent rows should have different colors.
Return the maximum height of the triangle that can be achieved.

Example 1:
Input: red = 2, blue = 4
Output: 3
Explanation:
The only possible arrangement is shown above.

Example 2:
Input: red = 2, blue = 1
Output: 2
Explanation:
The only possible arrangement is shown above.

Example 3:
Input: red = 1, blue = 1
Output: 1

Example 4:
Input: red = 10, blue = 1
Output: 2
Explanation:
The only possible arrangement is shown above.

Constraints:
    1 <= red, blue <= 100
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{5, 10, 10},
				{3, 2, 4},
				{2, 2, 1},
				{1, 1, 1},
				{2, 10, 1},
		});
	}

	public int maxHeightOfTriangle(int red, int blue) {
		return Math.max(calculate(red, blue), calculate(blue, red));
	}

	int calculate(int odd, int even) {
		int oddCount = (int) Math.sqrt(odd), evenCount = ((int) Math.sqrt((even << 2) + 1) - 1) >> 1;
		return evenCount < oddCount ? 1 + (evenCount << 1) : (oddCount << 1);
	}
}
