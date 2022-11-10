package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wit
 */
public class MostPopularVideoCreator {
	/*
	You are given two string arrays creators and ids, and an integer array views, all of length n. The ith video on a platform was created by creator[i], has an id of ids[i], and has views[i] views.
	The popularity of a creator is the sum of the number of views on all of the creator's videos. Find the creator with the highest popularity and the id of their most viewed video.
	If multiple creators have the highest popularity, find all of them.
	If multiple videos have the highest view count for a creator, find the lexicographically smallest id.
	Return a 2D array of strings answer where answer[i] = [creatori, idi] means that creatori has the highest popularity and idi is the id of their most popular video. The answer can be returned in any order.

	Example 1:
	Input: creators = ["alice","bob","alice","chris"], ids = ["one","two","three","four"], views = [5,10,5,4]
	Output: [["alice","one"],["bob","two"]]
	Explanation:
	The popularity of alice is 5 + 5 = 10.
	The popularity of bob is 10.
	The popularity of chris is 4.
	alice and bob are the most popular creators.
	For bob, the video with the highest view count is "two".
	For alice, the videos with the highest view count are "one" and "three". Since "one" is lexicographically smaller than "three", it is included in the answer.

	Example 2:
	Input: creators = ["alice","alice","alice"], ids = ["a","b","c"], views = [1,2,2]
	Output: [["alice","b"]]
	Explanation:
	The videos with id "b" and "c" have the highest view count.
	Since "b" is lexicographically smaller than "c", it is included in the answer.

	Constraints:
	n == creators.length == ids.length == views.length
	1 <= n <= 10^5
	1 <= creators[i].length, ids[i].length <= 5
	creators[i] and ids[i] consist only of lowercase English letters.
	0 <= views[i] <= 10^5
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TestUtil.transferToString("[[\"bb\",\"baa\"]]"), new String[]{"bb", "bb"}, new String[]{"baa", "bba"}, new int[]{1, 0}},
				{TestUtil.transferToString("[[\"alice\",\"one\"],[\"bob\",\"two\"]]"), new String[]{"alice", "bob", "alice", "chris"}, new String[]{"one", "two", "three", "four"}, new int[]{5, 10, 5, 4}},
				{TestUtil.transferToString("[[\"alice\",\"b\"]]"), new String[]{"alice", "alice", "alice"}, new String[]{"a", "b", "c"}, new int[]{1, 2, 2}},
		});
	}


	public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
		List<List<String>> result = new ArrayList<>(creators.length);
		int maxPopularity = 0;
		Map<String, Record> map = new HashMap<>(creators.length << 1);
		for (int i = 0; i < views.length; i++)
			maxPopularity = Math.max(maxPopularity, map.computeIfAbsent(creators[i], k -> new Record()).add(views[i], ids[i]));
		for (Map.Entry<String, Record> entry : map.entrySet())
			if (entry.getValue().popularitySum == maxPopularity)
				result.add(List.of(entry.getKey(), entry.getValue().mostPopularVideoId));
		return result;
	}

	class Record {
		int popularitySum, maxPopularity = -1;
		String mostPopularVideoId;

		public int add(int popularity, String videoId) {
			if (popularity > maxPopularity || (popularity == maxPopularity && isLexicographicallySmaller(videoId, mostPopularVideoId))) {
				maxPopularity = popularity;
				mostPopularVideoId = videoId;
			}
			return popularitySum += popularity;
		}

		private boolean isLexicographicallySmaller(String videoId, String mostPopularVideoId) {
			int compare = 0, length = Math.min(videoId.length(), mostPopularVideoId.length());
			for (int i = 0; compare == 0 && i < length; i++) compare = videoId.charAt(i) - mostPopularVideoId.charAt(i);
			return compare < 0 || (compare == 0 && videoId.length() < mostPopularVideoId.length());
		}
	}
}
