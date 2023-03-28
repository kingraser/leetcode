package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wit
 */
public class VowelSpellchecker {
	/*
	Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
	For a given query word, the spell checker handles two categories of spelling mistakes:
	Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
	Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
	Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
	Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
	Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
	Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
	Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
	Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
	In addition, the spell checker operates under the following precedence rules:
	When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
	When the query matches a word up to capitlization, you should return the first such match in the wordlist.
	When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
	If the query has no matches in the wordlist, you should return the empty string.
	Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
	
	Example 1:
	Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
	Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
	
	Example 2:
	Input: wordlist = ["yellow"], queries = ["YellOw"]
	Output: ["yellow"]
	
	Constraints:
	1 <= wordlist.length, queries.length <= 5000
	1 <= wordlist[i].length, queries[i].length <= 7
	wordlist[i] and queries[i] consist only of only English letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new String[]{"kite", "KiTe", "KiTe", "Hare", "hare", "", "", "KiTe", "", "KiTe"}, new String[]{"KiTe", "kite", "hare", "Hare"}, new String[]{"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"}},
				{new String[]{"yellow"}, new String[]{"yellow"}, new String[]{"YellOw"}},
		});
	}

	static final int GAP = 'a' - 'A';
	long hash, capHash, vowelHash;

	public String[] spellchecker(String[] wordlist, String[] queries) {
		Map<Long, String> wordMap = new HashMap<>(wordlist.length << 1), capMap = new HashMap<>(wordlist.length << 1), vowelMap = new HashMap<>(wordlist.length << 1);
		for (String word : wordlist) {
			getHash(word);
			wordMap.put(hash, word);
			capMap.putIfAbsent(capHash, word);
			vowelMap.putIfAbsent(vowelHash, word);
		}
		for (int i = 0; i < queries.length; i++) {
			getHash(queries[i]);
			if (wordMap.containsKey(hash)) continue;
			String word = capMap.get(capHash);
			queries[i] = word != null ? word : vowelMap.getOrDefault(vowelHash, "");
		}
		return queries;
	}

	void getHash(String word) {
		hash = 0;
		capHash = 0;
		vowelHash = 0;
		for (int i = 0, length = word.length(), c; i < length; ) {
			hash = (hash << 7) | (c = word.charAt(i++));
			capHash = (capHash << 7) | (c < 'a' ? c += GAP : c);
			vowelHash = c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ? (vowelHash << 7) : (vowelHash << 7) | c;
		}
	}


}
