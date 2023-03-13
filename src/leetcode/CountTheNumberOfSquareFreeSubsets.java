package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountTheNumberOfSquareFreeSubsets {
	/*
	You are given a positive integer 0-indexed array nums.
	A subset of the array nums is square-free if the product of its elements is a square-free integer.
	A square-free integer is an integer that is divisible by no square number other than 1.
	Return the number of square-free non-empty subsets of the array nums. Since the answer may be too large, return it modulo 10^9 + 7.
	A non-empty subset of nums is an array that can be obtained by deleting some (possibly none but not all) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.

	Example 1:
	Input: nums = [3,4,4,5]
	Output: 3
	Explanation: There are 3 square-free subsets in this example:
	- The subset consisting of the 0th element [3]. The product of its elements is 3, which is a square-free integer.
	- The subset consisting of the 3rd element [5]. The product of its elements is 5, which is a square-free integer.
	- The subset consisting of 0th and 3rd elements [3,5]. The product of its elements is 15, which is a square-free integer.
	It can be proven that there are no more than 3 square-free subsets in the given array.

	Example 2:
	Input: nums = [1]
	Output: 1
	Explanation: There is 1 square-free subset in this example:
	- The subset consisting of the 0th element [1]. The product of its elements is 1, which is a square-free integer.
	It can be proven that there is no more than 1 square-free subset in the given array.

	Constraints:
	1 <= nums.length <= 1000
	1 <= nums[i] <= 30
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, new int[]{10, 15, 6, 25}},
				{3, new int[]{3, 4, 4, 5}},
				{1, new int[]{1}},
				{1000000006, new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 30, 30, 30, 30, 30, 30, 30}}
		});
	}
	//dp[i][mask] means the count of square free subsets in sequence[i, total_length) with the mask generated from the sequence [0,i)

	long dp[][];
	int nums[], mod = 1000_000_007, masks[] = {-1, 0, 1, 2, -1, 4, 3, 8, -1, -1, 5, 16, -1, 32, 9, 6, -1, 64, -1, 128, -1, 10, 17, 256, -1, -1, 33, -1, -1, 512, 7};

	public int squareFreeSubsets(int[] nums) {
		dp = new long[nums.length][1024];
		this.nums = nums;
		return (int) (dfs(0, 0) - 1 + mod) % mod;// -1 means to ignore the case we do not choose any num
	}

	long dfs(int index, int mask) {
		if (index >= nums.length) return 1;
		if (dp[index][mask] != 0) return dp[index][mask];
		dp[index][mask] = dfs(index + 1, mask);
		if (masks[nums[index]] != -1 && (mask & masks[nums[index]]) == 0)
			dp[index][mask] = (dp[index][mask] + dfs(index + 1, mask | masks[nums[index]])) % mod;
		return dp[index][mask];
	}

	public int squareFreeSubsetsII(int[] nums) {
		long result = 1, frequency[] = new long[31];
		int primes[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29}, doublePrimes[] = {6, 10, 14, 22, 26}, triplePrimes[] = {15, 21};
		for (int num : nums) frequency[num]++;
		for (int prime : primes) if (frequency[prime] > 0) result = (result * (frequency[prime] + 1)) % mod;
		for (int doublePrime : doublePrimes) {
			if (frequency[doublePrime] == 0) continue;
			long toAdd = frequency[doublePrime];
			for (int i = 1, prime2 = doublePrime >> 1; i < primes.length; i++)
				if (primes[i] != prime2 && frequency[primes[i]] > 0) toAdd = (toAdd * (frequency[primes[i]] + 1)) % mod;
			result = (result + toAdd) % mod;
		}
		for (int triplePrime : triplePrimes) {
			if (frequency[triplePrime] == 0) continue;
			long toAdd = frequency[triplePrime];
			for (int i = 2, prime3 = triplePrime / 3; i < primes.length; i++)
				if (primes[i] != prime3 && frequency[primes[i]] > 0) toAdd = (toAdd * (frequency[primes[i]] + 1)) % mod;
			toAdd = (toAdd * (frequency[2] + 1)) % mod;
			result = (result + toAdd) % mod;
		}
		for (int doublePrime : doublePrimes) {
			if (frequency[doublePrime] == 0) continue;
			int prime2 = doublePrime >> 1;
			for (int triplePrime : triplePrimes) {
				if (frequency[triplePrime] == 0 || triplePrime % prime2 == 0) continue;
				long toAdd = frequency[doublePrime] * frequency[triplePrime];
				for (int i = 2, prime3 = triplePrime / 3; i < primes.length; i++) // prime start from 5
					if (primes[i] != prime2 && primes[i] != prime3 && frequency[primes[i]] > 0)
						toAdd = (toAdd * (frequency[primes[i]] + 1)) % mod;
				result = (result + toAdd) % mod;
			}
		}
		if (frequency[30] > 0) { // 30
			long toAdd = frequency[30];
			for (int i = 3; i < primes.length; i++) // prime not 2, not 3, not 5
				if (frequency[primes[i]] > 0) toAdd = (toAdd * (frequency[primes[i]] + 1)) % mod;
			result = (result + toAdd) % mod;
		}
		while (frequency[1]-- > 0) result = (result << 1) % mod; // 1
		// -1 for ignore the case that not choosing any num
		// +mod operation in case of the -1 operation to make the result turned out to be -1
		return (int) (result - 1 + mod) % mod;
	}
}
