package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.*;

/**
 * @author Wit
 */
public class JumpGameIV {
	/*
	Given an array of integers arr, you are initially positioned at the first index of the array.
	In one step you can jump from index i to index:
	i + 1 where: i + 1 < arr.length.
	i - 1 where: i - 1 >= 0.
	j where: arr[i] == arr[j] and i != j.
	Return the minimum number of steps to reach the last index of the array.
	Notice that you can not jump outside the array at any time.

	Example 1:
	Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
	Output: 3
	Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.

	Example 2:
	Input: arr = [7]
	Output: 0
	Explanation: Start index is the last index. You do not need to jump.

	Example 3:
	Input: arr = [7,6,9,6,9,6,9,7]
	Output: 1
	Explanation: You can jump directly from index 0 to index 7 which is last index of the array.

	Constraints:
	1 <= arr.length <= 5 * 10^4
	-10^8 <= arr[i] <= 10^8
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404}},
				{0, new int[]{7}},
				{1, new int[]{7, 6, 9, 6, 9, 6, 9, 7}}
		});
	}

	public int minJumps(int[] arr) {
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++)
			map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
		boolean[] visited = new boolean[arr.length];
		visited[0] = true;
		Queue<Integer> queue = new ArrayDeque<>(arr.length) {{
			add(0);
		}};
		Set<Integer> visitedNums = new HashSet<>(arr.length);
		for (int step = 0, size = 1, index; ; step++, size = queue.size())
			while (size-- > 0) {
				if ((index = queue.poll()) == arr.length - 1) return step;
				if (!visitedNums.contains(arr[index])) map.get(arr[index]).forEach(i -> offer(arr.length, queue, visited, i));
				visitedNums.add(arr[index]);
				offer(arr.length, queue, visited, index + 1);
				offer(arr.length, queue, visited, index - 1);
			}
	}

	void offer(int length, Queue<Integer> queue, boolean[] visited, int arg) {
		if (arg < 0 || arg >= length || visited[arg]) return;
		visited[arg] = true;
		queue.offer(arg);
	}
}
