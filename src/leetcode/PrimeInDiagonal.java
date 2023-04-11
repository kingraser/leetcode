package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class PrimeInDiagonal {
	/*
	You are given a 0-indexed two-dimensional integer array nums.
	Return the largest prime number that lies on at least one of the diagonals of nums. In case, no prime is present on any of the diagonals, return 0.
	Note that:
	An integer is prime if it is greater than 1 and has no positive integer divisors other than 1 and itself.
	An integer val is on one of the diagonals of nums if there exists an integer i for which nums[i][i] = val or an i for which nums[i][nums.length - i - 1]= val.

	In the above diagram, one diagonal is [1,5,9] and another diagonal is [3,5,7].

	Example 1:
	Input: nums = [[1,2,3],[5,6,7],[9,10,11]]
	Output: 11
	Explanation: The numbers 1, 3, 6, 9, and 11 are the only numbers present on at least one of the diagonals. Since 11 is the largest prime, we return 11.

	Example 2:
	Input: nums = [[1,2,3],[5,17,7],[9,11,10]]
	Output: 17
	Explanation: The numbers 1, 3, 9, 10, and 17 are all present on at least one of the diagonals. 17 is the largest prime, so we return 17.

	Constraints:
	1 <= nums.length <= 300
	nums.length == nums_i.length
	1 <= nums[i][j] <= 4*10^6
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{11, TestUtil.getArray("[[1,2,3],[5,6,7],[9,10,11]]")},
				{17, TestUtil.getArray("[[1,2,3],[5,17,7],[9,11,10]]")}
		});
	}

	static final int[] PRIMES = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997, 1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087, 1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151, 1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223, 1229, 1231, 1237, 1249, 1259, 1277, 1279, 1283, 1289, 1291, 1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373, 1381, 1399, 1409, 1423, 1427, 1429, 1433, 1439, 1447, 1451, 1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499, 1511, 1523, 1531, 1543, 1549, 1553, 1559, 1567, 1571, 1579, 1583, 1597, 1601, 1607, 1609, 1613, 1619, 1621, 1627, 1637, 1657, 1663, 1667, 1669, 1693, 1697, 1699, 1709, 1721, 1723, 1733, 1741, 1747, 1753, 1759, 1777, 1783, 1787, 1789, 1801, 1811, 1823, 1831, 1847, 1861, 1867, 1871, 1873, 1877, 1879, 1889, 1901, 1907, 1913, 1931, 1933, 1949, 1951, 1973, 1979, 1987, 1993, 1997, 1999};

	public int diagonalPrime(int[][] nums) {
		int result = 0;
		for (int row = 0, last = nums.length - 1, current; row < nums.length; ) {
			if ((current = nums[row][row]) > result && isPrime(current)) result = current;
			if ((current = nums[row][last - row++]) > result && isPrime(current)) result = current;
		}
		return result;
	}

	private boolean isPrime(int num) {
		if (num <= PRIMES[PRIMES.length - 1]) return Arrays.binarySearch(PRIMES, num) >= 0;
		for (int prime : PRIMES) if (num % prime == 0) return false;
		return true;
	}
}
