package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimumNumberOfPeopleToTeach {
	/*
	On a social network consisting of m users and some friendships between users, two users can communicate with each other if they know a common language.
	You are given an integer n, an array languages, and an array friendships where:
	There are n languages numbered 1 through n,
	languages[i] is the set of languages the ith user knows, and
	friendships[i] = [ui, vi] denotes a friendship between the users ui and vi.
	You can choose one language and teach it to some users so that all friends can communicate with each other. Return the minimum number of users you need to teach.
	Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't guarantee that x is a friend of z.

	Example 1:
	Input: n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
	Output: 1
	Explanation: You can either teach user 1 the second language or user 2 the first language.

	Example 2:
	Input: n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
	Output: 2
	Explanation: Teach the third language to users 1 and 3, yielding two users to teach.

	Constraints:
	2 <= n <= 500
	languages.length == m
	1 <= m <= 500
	1 <= languages[i].length <= n
	1 <= languages[i][j] <= n
	1 <= ui < vi <= languages.length
	1 <= friendships.length <= 500
	All tuples (ui, vi) are unique
	languages[i] contains only unique values
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, 2, TestUtil.getArray("[[1],[2],[1,2]]"), TestUtil.getArray("[[1,2],[1,3],[2,3]]")},
				{2, 3, TestUtil.getArray("[[2],[1,3],[1,2],[3]]"), TestUtil.getArray("[[1,4],[1,2],[3,4],[2,3]]")},
		});
	}

	public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
		int result = 0, usersCount = languages.length, languageList[][] = new int[usersCount + 1][n + 1], languageFrequency[] = new int[n + 1];
		boolean languageMap[][] = new boolean[usersCount + 1][n + 1], haveFriendsCanNotCommunicate[] = new boolean[usersCount + 1];
		for (int user = 0; user < languages.length; )
			for (int language : languages[user++]) {
				languageMap[user][language] = true;
				languageList[user][++languageList[user][0]] = language;
			}
		for (int[] friendship : friendships)
			if (canNotCommunicate(languageMap, languageList, friendship[0], friendship[1]))
				haveFriendsCanNotCommunicate[friendship[0]] = haveFriendsCanNotCommunicate[friendship[1]] = true;
		for (int user = 1; user <= usersCount; user++)
			if (haveFriendsCanNotCommunicate[user])
				for (int j = 1; j <= languageList[user][0]; j++) languageFrequency[languageList[user][j]]++;
		int mostCommonLanguage = 1, mostCommonLanguageCount = languageFrequency[1];
		for (int language = 2; language <= n; language++)
			if (languageFrequency[language] > mostCommonLanguageCount)
				mostCommonLanguageCount = languageFrequency[mostCommonLanguage = language];
		for (int user = 1; user <= usersCount; user++)
			if (haveFriendsCanNotCommunicate[user] && !languageMap[user][mostCommonLanguage]) result++;
		return result;
	}

	boolean canNotCommunicate(boolean[][] languageMap, int[][] languageList, int a, int b) {
		if (languageList[a][0] > languageList[b][0]) return canNotCommunicate(languageMap, languageList, b, a);
		for (int i = 1; i <= languageList[a][0]; ) if (languageMap[b][languageList[a][i++]]) return false;
		return true;
	}
}
