package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindXorBeautyOfArray {
	/*
	You are given a 0-indexed integer array nums.
	The effective value of three indices i, j, and k is defined as ((nums[i] | nums[j]) & nums[k]).
	The xor-beauty of the array is the XORing of the effective values of all the possible triplets of indices (i, j, k) where 0 <= i, j, k < n.
	Return the xor-beauty of nums.
	Note that:
	val1 | val2 is bitwise OR of val1 and val2.
	val1 & val2 is bitwise AND of val1 and val2.

	Example 1:
	Input: nums = [1,4]
	Output: 5
	Explanation:
	The triplets and their corresponding effective values are listed below:
	- (0,0,0) with effective value ((1 | 1) & 1) = 1
	- (0,0,1) with effective value ((1 | 1) & 4) = 0
	- (0,1,0) with effective value ((1 | 4) & 1) = 1
	- (0,1,1) with effective value ((1 | 4) & 4) = 4
	- (1,0,0) with effective value ((4 | 1) & 1) = 1
	- (1,0,1) with effective value ((4 | 1) & 4) = 4
	- (1,1,0) with effective value ((4 | 4) & 1) = 0
	- (1,1,1) with effective value ((4 | 4) & 4) = 4
	Xor-beauty of array will be bitwise XOR of all beauties = 1 ^ 0 ^ 1 ^ 4 ^ 1 ^ 4 ^ 0 ^ 4 = 5.

	Example 2:
	Input: nums = [15,45,20,2,34,35,5,44,32,30]
	Output: 34
	Explanation: The xor-beauty of the given array is 34.

	Constraints:
	1 <= nums.length <= 10^5
	1 <= nums[i] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{5, new int[]{1, 4}},
				{34, new int[]{15, 45, 20, 2, 34, 35, 5, 44, 32, 30}},
		});
	}

	public int xorBeauty(int[] nums) {
		int result = 0;
		for (int num : nums) result ^= num;
		return result;
	}
/*
Simple reason why taking XOR of all numbers work:

Let's just consider all possible combinations, and simplify the xor operations.

Split all the possible triplets into below 3 types.

Type 1: Considering 1 number a with all 3 indices same
(a | a) & a = a

Type 2: Considering 2 numbers a b with any 2 indices same

Different combinations are:

(a | a) & b = a & b
(b | b) & a = b & a
(a | b) & a = aa | ab = a | ab = a (By Distributive_law and Absorption_law)
(b | a) & a = a
(a | b) & b = b
(b | a) & b = b
XOR of the above values :
(a & b) ^ (b & a) ^ a ^ a ^ b ^ b
= (a&b)^(a&b) ^ a^a ^ b^b
= 0

We can also note that,

1st point and 2nd pt are same.
3rd point and 4th pt are same.
5th point and 6th pt are same.
Type 3: Considering 3 numbers a b c with all 3 indices are different

Whenever we consider (a | b) & c,
we should also consider (b | a) & c.
It is easy to note that, both these have the same values. So, taking XOR of these 2, equates to 0.
Similarly, we can do the same for (a|c)&b, along with (c|a)&b. XOR of these is 0.
Also, for (b|c)&a ^ (c|b)&a is 0.
So, the only numbers that remain are the numbers which have all 3 indices same
(a | a) & a.
*/
}
