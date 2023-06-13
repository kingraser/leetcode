package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wit
 */
public class GetWatchedVideosByYourFriends {
	/*
	There are n people, each person has a unique id between 0 and n-1. Given the arrays watchedVideos and friends, where watchedVideos[i] and friends[i] contain the list of watched videos and the list of friends respectively for the person with id = i.
	Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched videos by the friends of your friends and so on. In general, the level k of videos are all watched videos by people with the shortest path exactly equal to k with you. Given your id and the level of videos, return the list of videos ordered by their frequencies (increasing). For videos with the same frequency order them alphabetically from least to greatest.

	Example 1:
	nInput: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
	Output: ["B","C"]
	Explanation:
	You have id = 0 (green color in the figure) and your friends are (yellow color in the figure):
	Person with id = 1 -> watchedVideos = ["C"]
	Person with id = 2 -> watchedVideos = ["B","C"]
	The frequencies of watchedVideos by your friends are:
	B -> 1
	C -> 2

	Example 2:
	Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
	Output: ["D"]
	Explanation:
	You have id = 0 (green color in the figure) and the only friend of your friends is the person with id = 3 (yellow color in the figure).

	Constraints:
	n == watchedVideos.length == friends.length
	2 <= n <= 100
	1 <= watchedVideos[i].length <= 100
	1 <= watchedVideos[i][j].length <= 8
	0 <= friends[i].length < n
	0 <= friends[i][j] < n
	0 <= id < n
	1 <= level < n
	if friends[i] contains j, then friends[j] contains i
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{List.of("B", "C"), TestUtil.transferToString("[[\"A\",\"B\"],[\"C\"],[\"B\",\"C\"],[\"D\"]]"), TestUtil.getArray("[[1,2],[0,3],[0,3],[1,2]]"), 0, 1},
				{List.of("D"), TestUtil.transferToString("[[\"A\",\"B\"],[\"C\"],[\"B\",\"C\"],[\"D\"]]"), TestUtil.getArray("[[1,2],[0,3],[0,3],[1,2]]"), 0, 2},
		});
	}

	public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
		int[] visited = new int[friends.length];
		Queue<Integer> queue = new ArrayDeque<>(visited.length) {{offer(id);}};
		visited[id]++;
		for (int currentLevel = 0; currentLevel < level && !queue.isEmpty(); currentLevel++)
			for (int size = queue.size(); size-- > 0; )
				for (int friend : friends[queue.poll()]) if (visited[friend]++ == 0) queue.offer(friend);
		Map<String, Integer> map = new HashMap<>(queue.size() << 1);
		for (Integer person : queue) for (String video : watchedVideos.get(person)) map.merge(video, 1, Integer::sum);
		return map.entrySet().stream().sorted((e1, e2) -> Objects.equals(e1.getValue(), e2.getValue()) ? e1.getKey().compareTo(e2.getKey()) : e1.getValue() - e2.getValue()).map(Map.Entry::getKey).collect(Collectors.toList());
	}

}
