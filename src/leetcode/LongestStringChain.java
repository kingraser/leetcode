package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class LongestStringChain {
	/*
	You are given an array of words where each word consists of lowercase English letters.
	wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.
	For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
	A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
	Return the length of the longest possible word chain with words chosen from the given list of words.

	Example 1:
	Input: words = ["a","b","ba","bca","bda","bdca"]
	Output: 4
	Explanation: One of the longest word chains is ["a","ba","bda","bdca"].

	Example 2:
	Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
	Output: 5
	Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].

	Example 3:
	Input: words = ["abcd","dbqca"]
	Output: 1
	Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
	["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.

	Constraints:
	1 <= words.length <= 1000
	1 <= words[i].length <= 16
	words[i] only consists of lowercase English letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{7, new String[]{"ksqvsyq", "ks", "kss", "czvh", "zczpzvdhx", "zczpzvh", "zczpzvhx", "zcpzvh", "zczvh", "gr", "grukmj", "ksqvsq", "gruj", "kssq", "ksqsq", "grukkmj", "grukj", "zczpzfvdhx", "gru"}},
				{5, new String[]{"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"}},
				{4, new String[]{"bdca", "bda", "ca", "dca", "a"}},
				{4, new String[]{"a", "b", "ba", "bca", "bda", "bdca"}},
				{5, new String[]{"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"}},
				{1, new String[]{"abcd", "dbqca"}}
		});
	}

	static final int MAX_LENGTH = 16;
	int result, maxLength, currentLevel;

	public int longestStrChain(String[] words) {
		result = 1;
		List<Record>[] chain = new List[MAX_LENGTH + 1];
		for (int i = 1; i < chain.length; ) chain[i++] = new ArrayList<>(words.length);
		for (String word : words) chain[word.length()].add(new Record(word));
		for (maxLength = MAX_LENGTH; maxLength >= 1 && chain[maxLength].isEmpty(); ) maxLength--;
		for (currentLevel = 1; currentLevel < maxLength && chain[currentLevel].isEmpty(); ) currentLevel++;
		for (; maxLength - currentLevel + 1 > result; currentLevel++)
			for (Record record : chain[currentLevel]) if (record.length == 1 && dfs(chain, record)) break;
		return result;
	}

	/**
	 * dfs
	 *
	 * @param chain  the chain
	 * @param record the record
	 * @return whether reach the end
	 * false for no successor
	 * true for reach the end
	 */
	boolean dfs(List<Record>[] chain, Record record) {
		int level = record.chars.length;
		if (level == maxLength || chain[level + 1].isEmpty()) {
			currentLevel = level + 1;
			return true;
		}
		for (Record next : chain[level + 1])
			if (next.length == 1 && isPredecessor(record, next) && dfs(chain, next)) return true;
		return false;
	}

	boolean isPredecessor(Record prev, Record next) {
		for (int prevIdx = 0, nextIdx = 0, diffs = 0; prevIdx < prev.chars.length; )
			if (prev.chars[prevIdx] == next.chars[nextIdx++]) prevIdx++;
			else if (++diffs > 1) return false;
		result = Math.max(result, next.length = prev.length + 1);
		return true;
	}

	public class Record {
		char chars[];
		int length = 1;

		public Record(String word) {chars = word.toCharArray();}
	}
}
