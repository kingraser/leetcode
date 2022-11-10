package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimumConsecutiveCardsToPickUp {
	/*
	You are given an integer array cards where cards[i] represents the value of the ith card. A pair of cards are matching if the cards have the same value.
	Return the minimum number of consecutive cards you have to pick up to have a pair of matching cards among the picked cards. If it is impossible to have matching cards, return -1.

	Example 1:
	Input: cards = [3,4,2,3,4,7]
	Output: 4
	Explanation: We can pick up the cards [3,4,2,3] which contain a matching pair of cards with value 3. Note that picking up the cards [4,2,3,4] is also optimal.

	Example 2:
	Input: cards = [1,0,5,3]
	Output: -1
	Explanation: There is no way to pick up a set of consecutive cards that contain a pair of matching cards.

	Constraints:
	1 <= cards.length <= 10^5
	0 <= cards[i] <= 10^6
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, new int[]{95, 11, 8, 65, 5, 86, 30, 27, 30, 73, 15, 91, 30, 7, 37, 26, 55, 76, 60, 43, 36, 85, 47, 96, 6}},
				{4, new int[]{3, 4, 2, 3, 4, 7}},
				{-1, new int[]{1, 0, 5, 3}},
		});
	}

	public int minimumCardPickup(int[] cards) {
		int result = cards.length + 1, frequency[] = new int[1000_001];
		for (int left = 0, right = 0; right < cards.length; right++)
			if (++frequency[cards[right]] == 2) {
				while (--frequency[cards[left++]] == 0) {}
				if ((result = Math.min(result, right - left + 2)) == 2) break;
			}
		return result == cards.length + 1 ? -1 : result;
	}
}
