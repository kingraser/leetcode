package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Wit
 */
public class FindThePunishmentNumberOfAnInteger {
	/*
	Given a positive integer n, return the punishment number of n.
	The punishment number of n is defined as the sum of the squares of all integers i such that:
	1 <= i <= n
	The decimal representation of i * i can be partitioned into contiguous substrings such that the sum of the integer values of these substrings equals i.

	Example 1:
	Input: n = 10
	Output: 182
	Explanation: There are exactly 3 integers i that satisfy the conditions in the statement:
	- 1 since 1 * 1 = 1
	- 9 since 9 * 9 = 81 and 81 can be partitioned into 8 + 1.
	- 10 since 10 * 10 = 100 and 100 can be partitioned into 10 + 0.
	Hence, the punishment number of 10 is 1 + 81 + 100 = 182

	Example 2:
	Input: n = 37
	Output: 1478
	Explanation: There are exactly 4 integers i that satisfy the conditions in the statement:
	- 1 since 1 * 1 = 1.
	- 9 since 9 * 9 = 81 and 81 can be partitioned into 8 + 1.
	- 10 since 10 * 10 = 100 and 100 can be partitioned into 10 + 0.
	- 36 since 36 * 36 = 1296 and 1296 can be partitioned into 1 + 29 + 6.
	Hence, the punishment number of 37 is 1 + 81 + 100 + 1296 = 1478

	Constraints:
	1 <= n <= 1000
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{182, 10},
				{1478, 37}
		});
	}

	static int[] punishNumbers = new int[]{1, 9, 10, 36, 45, 55, 82, 91, 99, 100, 235, 297, 369, 370, 379, 414, 657, 675, 703, 756, 792, 909, 918, 945, 964, 990, 991, 999, 1000}, prefixSums = new int[]{1, 82, 182, 1478, 3503, 6528, 13252, 21533, 31334, 41334, 96559, 184768, 320929, 457829, 601470, 772866, 1204515, 1660140, 2154349, 2725885, 3353149, 4179430, 5022154, 5915179, 6844475, 7824575, 8806656, 9804657, 10804657};

	public int punishmentNumber(int n) {
		int index = Arrays.binarySearch(punishNumbers, n);
		return prefixSums[index < 0 ? -index - 2 : index];
	}

	public static void main(String[] args) {
		// punishment numbers
		System.out.println(IntStream.range(1, 1001).filter(FindThePunishmentNumberOfAnInteger::isPunishmentNumber).mapToObj(Integer::toString).collect(Collectors.joining(",", "{", "}")));
		prefixSums[0] = 1;
		for (int i = 1; i < prefixSums.length; i++)
			prefixSums[i] = prefixSums[i - 1] + punishNumbers[i] * punishNumbers[i];
		System.out.println(Arrays.stream(prefixSums).mapToObj(Integer::toString).collect(Collectors.joining(",", "{", "}")));
	}

	static boolean isPunishmentNumber(int n) {
		return isPunishmentNumber(Integer.toString(n * n).toCharArray(), n, 0);
	}

	static boolean isPunishmentNumber(char[] digits, int n, int start) {
		if (n == 0) {
			while (start < digits.length) if (digits[start++] != '0') return false;
			return true;
		}
		for (int sum = 0; start < digits.length && sum <= n; )
			if (isPunishmentNumber(digits, n - (sum = sum * 10 + digits[start] - '0'), ++start)) return true;
		return false;
	}
}
