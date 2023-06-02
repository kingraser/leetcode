package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class AsteroidCollision {
	/*
	We are given an array asteroids of integers representing asteroids in a row.
	For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
	Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

	Example 1:
	Input: asteroids = [5,10,-5]
	Output: [5,10]
	Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.

	Example 2:
	Input: asteroids = [8,-8]
	Output: []
	Explanation: The 8 and -8 collide exploding each other.

	Example 3:
	Input: asteroids = [10,2,-5]
	Output: [10]
	Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.

	Constraints:
	2 <= asteroids.length <= 10^4
	-1000 <= asteroids[i] <= 1000
	asteroids[i] != 0
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{5, 10}, new int[]{5, 10, -5}},
				{new int[]{}, new int[]{8, -8}},
				{new int[]{10}, new int[]{10, 2, -5}},
		});
	}

	public int[] asteroidCollision(int[] asteroids) {
		int result[] = new int[asteroids.length], size = 0;
		A:
		for (int asteroid : asteroids) {
			if (asteroid < 0)
				while (size > 0 && result[size - 1] > 0)
					if (result[size - 1] > -asteroid || result[--size] == -asteroid) continue A;
			result[size++] = asteroid;
		}
		return Arrays.copyOf(result, size);
	}
}
