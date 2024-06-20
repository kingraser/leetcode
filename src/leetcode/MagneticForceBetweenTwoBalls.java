package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class MagneticForceBetweenTwoBalls {
	/*
	In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket. Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.
	Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
	Given the integer array position and the integer m. Return the required force.

	Example 1:
	Input: position = [1,2,3,4,7], m = 3
	Output: 3
	Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.

	Example 2:
	Input: position = [5,4,3,2,1,1000000000], m = 2
	Output: 999999999
	Explanation: We can use baskets 1 and 1000000000.

	Constraints:
		n == position.length
		2 <= n <= 10^5
		1 <= position[i] <= 10^9
		All integers in position are distinct.
		2 <= m <= position.length
	*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{5, new int[]{79, 74, 57, 22}, 4},
				{3, new int[]{1, 2, 3, 4, 7}, 3},
				{999999999, new int[]{5, 4, 3, 2, 1, 1000000000}, 2},
		});
	}

	public int maxDistance(int[] position, int m) {
		Arrays.sort(position);
		int left = 1, right = (position[position.length - 1] - position[0]) / (m - 1), mid;
		while (left < right)
			if (check(position, mid = (left + right + 1) >> 1, m)) left = mid;
			else right = mid - 1;
		return left;
	}

	private boolean check(int[] position, int minimumDistance, int m) {
		for (int i = 0, nextPosition = position[0]; i < position.length && m != 0; i++)
			if (position[i] >= nextPosition) {
				nextPosition = position[i] + minimumDistance;
				m--;
			}
		return m == 0;
	}
}
