package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ExtraCharactersInAString {
	/*
	You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters in s which are not present in any of the substrings.
	Return the minimum number of extra characters left over if you break up s optimally.

	Example 1:
	Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
	Output: 1
	Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.

	Example 2:
	Input: s = "sayhelloworld", dictionary = ["hello","world"]
	Output: 3
	Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12. The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.

	Constraints:
	1 <= s.length <= 50
	1 <= dictionary.length <= 50
	1 <= dictionary[i].length <= 50
	dictionary[i] and s consists of only lowercase English letters
	dictionary contains distinct words
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, "leetscode", new String[]{"leet", "code", "leetcode"}},
				{3, "sayhelloworld", new String[]{"hello", "world"}},
		});
	}

	public int minExtraChar(String s, String[] dictionary) {
		TrieNode root = new TrieNode(), node;
		for (String word : dictionary) insert(root, word);
		int length = s.length(), dp[] = new int[length + 1];
		for (int i = length - 1, j; i >= 0; i--)
			for (dp[i] = dp[i + 1] + 1, node = root, j = i; j < length && (node = node.children[s.charAt(j) - 'a']) != null; j++)
				if (node.isWord) dp[i] = Math.min(dp[i], dp[j + 1]);
		return dp[0];
	}

	class TrieNode {
		TrieNode[] children = new TrieNode[26];
		boolean isWord = false;
	}


	void insert(TrieNode root, String word) {
		TrieNode node = root;
		for (char c : word.toCharArray())
			node = node.children[c -= 'a'] == null ? node.children[c] = new TrieNode() : node.children[c];
		node.isWord = true;
	}
}
