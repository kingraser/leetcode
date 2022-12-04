package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class NumberOfUnequalTripletsInArray {
	/*
	You are given a 0-indexed array of positive integers nums. Find the number of triplets (i, j, k) that meet the following conditions:
	0 <= i < j < k < nums.length
	nums[i], nums[j], and nums[k] are pairwise distinct.
	In other words, nums[i] != nums[j], nums[i] != nums[k], and nums[j] != nums[k].
	Return the number of triplets that meet the conditions.

	Example 1:
	Input: nums = [4,4,2,4,3]
	Output: 3
	Explanation: The following triplets meet the conditions:
	- (0, 2, 4) because 4 != 2 != 3
	- (1, 2, 4) because 4 != 2 != 3
	- (2, 3, 4) because 2 != 4 != 3
	Since there are 3 triplets, we return 3.
	Note that (2, 0, 4) is not a valid triplet because 2 > 0.

	Example 2:
	Input: nums = [1,1,1,1,1]
	Output: 0
	Explanation: No triplets meet the conditions so we return 0.

	Constraints:
	3 <= nums.length <= 100
	1 <= nums[i] <= 1000
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, new int[]{4, 4, 2, 4, 3}},
				{0, new int[]{1, 1, 1, 1, 1}}
		});
	}

	/*
	Iterate the input one by one,
	count[a] counts the frequency of element `a` we have seen.
	pair is the number of pairwise distinct pair
	trips is the number of pairwise distinct triplet.

	So for every element A[i],
	There are already pairs
	among them `pairs_with_a = count[A[i]] * (i - count[A[i]])` pairs contains `A[i]`
	so `pairs_without_a = pairs - pairs_with_a`
	and `A[i]` can combine `new_triplets_with_a = pairs_without_a`
	So that we update trips.

	For pairs, will be easier if you get the above process,
	we update pairs += i - count[A[i]]

	Also need to update count[A[i]] += 1

	After all, we simply return triplets.
	*/
	public int unequalTriplets(int[] nums) {
		int trips = 0, pairs = 0, count[] = new int[1001];
		for (int i = 0; i < nums.length; count[nums[i++]]++) {
			trips += pairs - count[nums[i]] * (i - count[nums[i]]);
			pairs += i - count[nums[i]];
		}
		return trips;
	}
}
