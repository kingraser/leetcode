package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ClosestPrimeNumbersInRange {
	/*
	Given two positive integers left and right, find the two integers num1 and num2 such that:
	left <= nums1 < nums2 <= right .
	nums1 and nums2 are both prime numbers.
	nums2 - nums1 is the minimum amongst all other pairs satisfying the above conditions.
	Return the positive integer array ans = [nums1, nums2]. If there are multiple pairs satisfying these conditions, Return the one with the minimum nums1 value or [-1, -1] if such numbers do not exist.
	A number greater than 1 is called prime if it is only divisible by 1 and itself.

	Example 1:
	Input: left = 10, right = 19
	Output: [11,13]
	Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
	The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
	Since 11 is smaller than 17, we return the first pair.

	Example 2:
	Input: left = 4, right = 6
	Output: [-1,-1]
	Explanation: There exists only one prime number in the given range, so the conditions cannot be satisfied.

	Constraints:
	1 <= left <= right <= 10^6
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{69371, 69379}, 69346, 69379},
				{new int[]{29, 31}, 19, 31},
				{new int[]{11, 13}, 10, 19},
				{new int[]{-1, -1}, 4, 6}
		});
	}

	public int[] closestPrimes(int left, int right) {
		int currentMin = Integer.MAX_VALUE, prime = -1, nextPrime = -1, prev = -1, num = Math.max(2, left);
		while (num <= right)
			if (isNotPrime(num)) num++;
			else {
				prev = num++;
				break;
			}
		for (; num <= right; num++) {
			if (isNotPrime(num)) continue;
			if (num - prev < currentMin && (currentMin = (nextPrime = num) - (prime = prev)) == 2) break;
			prev = num;
		}
		return new int[]{prime, nextPrime};
	}

	boolean isNotPrime(int x) {
		for (int i = 2, root = (int) Math.sqrt(x); i <= root; ) if (x % i++ == 0) return true;
		return false;
	}
}
