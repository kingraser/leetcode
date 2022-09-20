package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class MaximumMatchingOfPlayersWithTrainers {
	/*
	You are given a 0-indexed integer array players, where players[i] represents the ability of the ith player. You are also given a 0-indexed integer array trainers, where trainers[j] represents the training capacity of the jth trainer.
	The ith player can match with the jth trainer if the player's ability is less than or equal to the trainer's training capacity. Additionally, the ith player can be matched with at most one trainer, and the jth trainer can be matched with at most one player.
	Return the maximum number of matching between players and trainers that satisfy these conditions.

	Example 1:
	Input: players = [4,7,9], trainers = [8,2,5,8]
	Output: 2
	Explanation:
	One of the ways we can form two matching is as follows:
	- players[0] can be matched with trainers[0] since 4 <= 8.
	- players[1] can be matched with trainers[3] since 7 <= 8.
	It can be proven that 2 is the maximum number of matching that can be formed.

	Example 2:
	Input: players = [1,1,1], trainers = [10]
	Output: 1
	Explanation:
	The trainer can be matched with any of the 3 players.
	Each player can only be matched with one trainer, so the maximum answer is 1.

	Constraints:
	1 <= players.length, trainers.length <= 10^5
	1 <= players[i], trainers[j] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, new int[]{4, 7, 9}, new int[]{8, 2, 5, 8}},
				{1, new int[]{1, 1, 1}, new int[]{10}},

		});
	}

	public int matchPlayersAndTrainers(int[] players, int[] trainers) {
		Arrays.sort(players);
		Arrays.sort(trainers);
		int player = 0, trainer = 0;
		while (player < players.length && trainer < trainers.length)
			if (trainers[trainer++] >= players[player]) player++;
		return player;
	}
}
