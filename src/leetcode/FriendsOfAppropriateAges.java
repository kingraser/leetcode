package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FriendsOfAppropriateAges {
	/*
	There are n persons on a social media website. You are given an integer array ages where ages[i] is the age of the ith person.
	A Person x will not send a friend request to a person y (x != y) if any of the following conditions is true:
	age[y] <= 0.5 * age[x] + 7
	age[y] > age[x]
	age[y] > 100 && age[x] < 100
	Otherwise, x will send a friend request to y.
	Note that if x sends a request to y, y will not necessarily send a request to x. Also, a person will not send a friend request to themself.
	Return the total number of friend requests made.

	Example 1:
	Input: ages = [16,16]
	Output: 2
	Explanation: 2 people friend request each other.

	Example 2:
	Input: ages = [16,17,18]
	Output: 2
	Explanation: Friend requests are made 17 -> 16, 18 -> 17.

	Example 3:
	Input: ages = [20,30,100,110,120]
	Output: 3
	Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.

	Constraints:
	n == ages.length
	1 <= n <= 2 * 10^4
	1 <= ages[i] <= 120
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{29, new int[]{73, 106, 39, 6, 26, 15, 30, 100, 71, 35, 46, 112, 6, 60, 110}},
				{3, new int[]{108, 115, 5, 24, 82}},
				{2, new int[]{16, 16}},
				{2, new int[]{16, 17, 18}},
				{3, new int[]{20, 30, 100, 110, 120}},});
	}

	public int numFriendRequests(int[] ages) {
		int result = 0, freq[] = new int[121], preSums[] = new int[121];
		for (int age : ages) freq[age]++;
		for (int i = 15; i < 121; ) preSums[i] = preSums[i - 1] + freq[i++];
		for (int age = 120; age > 14; age--)
			if (freq[age] != 0) result += freq[age] * (preSums[age] - preSums[(age >> 1) + 7] - 1);
		return result;
	}
}
