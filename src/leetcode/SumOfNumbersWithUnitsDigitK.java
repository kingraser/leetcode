package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SumOfNumbersWithUnitsDigitK {
	/*
	Given two integers num and k, consider a set of positive integers with the following properties:
	The `units` digit of each integer is k.
	The sum of the integers is num.
	Return the minimum possible size of such a set, or -1 if no such set exists.
	Note:
	The set can contain multiple instances of the same integer, and the sum of an empty set is considered 0.
	The `units` digit of a number is the rightmost digit of the number.

	Example 1:
	Input: num = 58, k = 9
	Output: 2
	Explanation:
	One valid set is [9,49], as the sum is 58 and each integer has a units digit of 9.
	Another valid set is [19,39].
	It can be shown that 2 is the minimum possible size of a valid set.

	Example 2:
	Input: num = 37, k = 2
	Output: -1
	Explanation: It is not possible to obtain a sum of 37 using only integers that have a units digit of 2.

	Example 3:
	Input: num = 0, k = 7
	Output: 0
	Explanation: The sum of an empty set is considered 0.

	Constraints:
	0 <= num <= 3000
	0 <= k <= 9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, 10, 0},
				{-1, 10, 8},
				{2, 58, 9},
				{-1, 37, 2},
				{0, 0, 7}
		});
	}

	public int minimumNumbers(int num, int k) {
		if (num == 0) return 0;
		for (int i = 1, units = num % 10, product; (product = i * k) <= num && i <= 10; i++)
			if (product % 10 == units) return i;
		return -1;
	}
}
