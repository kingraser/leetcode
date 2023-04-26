package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Wit
 */
public class PowerfulIntegers {
	/*
	Given three integers x, y, and bound, return a list of all the powerful integers that have a value less than or equal to bound.
	An integer is powerful if it can be represented as xi + yj for some integers i >= 0 and j >= 0.
	You may return the answer in any order. In your answer, each value should occur at most once.

	Example 1:
	Input: x = 2, y = 3, bound = 10
	Output: [2,3,4,5,7,9,10]
	Explanation:
	2 = 2^0 + 3^0
	3 = 2^1 + 3^0
	4 = 2^0 + 3^1
	5 = 2^1 + 3^1
	7 = 2^2 + 3^1
	9 = 2^3 + 3^0
	10 = 2^0 + 3^2

	Example 2:
	Input: x = 3, y = 5, bound = 15
	Output: [2,4,6,8,10,14]

	Constraints:
	1 <= x, y <= 100
	0 <= bound <= 10^6
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{List.of(2, 3, 4, 5, 7, 9, 10), 2, 3, 10},
				{List.of(2, 4, 6, 8, 10, 14), 3, 5, 15},
		});
	}

	public List<Integer> powerfulIntegers(int x, int y, int bound) {
		if (Math.min(x, y) == 1) return powerfulIntegers(Math.max(x, y), bound);
		Set<Integer> set = new HashSet<>();
		for (int i = 1; i < bound; i *= x)
			for (int j = 1, newBound = bound - i; j <= newBound; j *= y) set.add(i + j);
		return new ArrayList<>(set);
	}

	List<Integer> powerfulIntegers(int num, int bound) {
		if (num == 1) return bound > 1 ? List.of(2) : new ArrayList<>();
		List<Integer> result = new ArrayList<>(20); // cause bound <= 10^6
		for (int i = 1; i < bound; i *= num) result.add(i + 1);
		return result;
	}
}
