package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class DividePlayersIntoTeamsOfEqualSkill {
	/*
	You are given a positive integer array skill of even length n where skill[i] denotes the skill of the ith player. Divide the players into n / 2 teams of size 2 such that the total skill of each team is equal.
	The chemistry of a team is equal to the product of the skills of the players on that team.
	Return the sum of the chemistry of all the teams, or return -1 if there is no way to divide the players into teams such that the total skill of each team is equal.

	Example 1:
	Input: skill = [3,2,5,1,3,4]
	Output: 22
	Explanation:
	Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where each team has a total skill of 6.
	The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22.

	Example 2:
	Input: skill = [3,4]
	Output: 12
	Explanation:
	The two players form a team with a total skill of 7.
	The chemistry of the team is 3 * 4 = 12.

	Example 3:
	Input: skill = [1,1,2,3]
	Output: -1
	Explanation:
	There is no way to divide the players into teams such that the total skill of each team is equal.

	Constraints:
	2 <= skill.length <= 10^5
	skill.length is even.
	1 <= skill[i] <= 1000
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{10L, new int[]{2, 4, 1, 3}},
				{22L, new int[]{3, 2, 5, 1, 3, 4}},
				{12L, new int[]{3, 4}},
				{-1L, new int[]{1, 1, 2, 3}},
		});
	}

	public long dividePlayers(int[] skill) {
		int freq[] = new int[1001], sum = 0, halfSum, length = skill.length, halfLength = length >> 1;
		if (length == 2) return (long) skill[0] * skill[1];
		long result = 0;
		for (int num : skill) {
			freq[num]++;
			sum += num;
		}
		if (sum % (halfLength) != 0) return -1;
		halfSum = (sum /= halfLength) >> 1;
		if ((sum & 1) == 0) {
			if ((freq[halfSum] & 1) == 1) return -1;
			result += (long) halfSum * halfSum * (freq[halfSum--] >> 1);
		}
		for (int i = 1; i <= halfSum; i++) {
			if (freq[i] == 0) continue;
			if (freq[i] != freq[sum - i]) return -1;
			result += (long) i * (sum - i) * freq[i];
		}
		return result;
	}
}
