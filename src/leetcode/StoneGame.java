package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class StoneGame {
	/*
	Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
	The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.
	Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row. This continues until there are no more piles left, at which point the person with the most stones wins.
	Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.

	Example 1:
	Input: piles = [5,3,4,5]
	Output: true
	Explanation:
	Alice starts first, and can only take the first 5 or the last 5.
	Say she takes the first 5, so that the row becomes [3, 4, 5].
	If Bob takes 3, then the board is [4, 5], and Alice takes 5 to win with 10 points.
	If Bob takes the last 5, then the board is [3, 4], and Alice takes 4 to win with 9 points.
	This demonstrated that taking the first 5 was a winning move for Alice, so we return true.

	Example 2:
	Input: piles = [3,7,2,3]
	Output: true

	Constraints:
	2 <= piles.length <= 500
	piles.length is even.
	1 <= piles[i] <= 500
	sum(piles[i]) is odd.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, new int[]{5, 3, 4, 5}},
				{true, new int[]{3, 7, 2, 3}}
		});
	}

	/*
	Approach 1: Just return true

	Alex is first to pick pile.
	piles.length is even, and this lead to an interesting fact:
	Alex can always pick odd piles or always pick even piles!

	For example,
	If Alex wants to pick even indexed piles piles[0], piles[2], ....., piles[n-2],
	he picks first piles[0], then Lee can pick either piles[1] or piles[n - 1].
	Every turn, Alex can always pick even indexed piles and Lee can only pick odd indexed piles.

	In the description, we know that sum(piles) is odd.
	If sum(piles[even]) > sum(piles[odd]), Alex just picks all evens and wins.
	If sum(piles[even]) < sum(piles[odd]), Alex just picks all odds and wins.

	So, Alex always defeats Lee in this game.
	*/
	public boolean stoneGame(int[] piles) {
		return true;
	}

	/*
	It's tricky when we have even number of piles of stones. You may not have this condition in an interview.

	Follow-up:

	What if piles.length can be odd?
	What if we want to know exactly the difference of score?
	Then we need to solve it with DP.

	dp[i][j] means the biggest number of stones you can get more than opponent picking piles in piles[i] ~ piles[j].
	You can first pick piles[i] or piles[j].

	If you pick piles[i], your result will be piles[i] - dp[i + 1][j]
	If you pick piles[j], your result will be piles[j] - dp[i][j - 1]
	So we get:
	dp[i][j] = max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1])
	We start from smaller subarray then we use that to calculate bigger subarray.

	Note that take evens or take odds, it's just an easy strategy to win when the number of stones is even.
	It's not the best solution!
	I didn't find a tricky solution when the number of stones is odd (maybe there is).
	*/
	public boolean stoneGameII(int[] piles) {
		int[][] dp = new int[piles.length][piles.length];
		for (int i = 0; i < piles.length; i++) dp[i][i] = piles[i];
		for (int distance = 1; distance < piles.length; distance++)
			for (int i = 0; i < piles.length - distance; i++)
				dp[i][i + distance] = Math.max(piles[i] - dp[i + 1][i + distance], piles[i + distance] - dp[i][i + distance - 1]);
		return dp[0][piles.length - 1] > 0;
	}
}
