package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.*;

/**
 * @author Wit
 */
public class MinimumCostOfAPathWithSpecialRoads {
/*
You are given an array start where start = [startX, startY] represents your initial position (startX, startY) in a 2D space. You are also given the array target where target = [targetX, targetY] represents your target position (targetX, targetY).
The cost of going from a position (x1, y1) to any other position in the space (x2, y2) is |x2 - x1| + |y2 - y1|.
There are also some special roads. You are given a 2D array specialRoads where specialRoads[i] = [x1i, y1i, x2i, y2i, costi] indicates that the ith special road can take you from (x1i, y1i) to (x2i, y2i) with a cost equal to costi. You can use each special road any number of times.
Return the minimum cost required to go from (startX, startY) to (targetX, targetY).

Example 1:
Input: start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
Output: 5
Explanation: The optimal path from (1,1) to (4,5) is the following:
- (1,1) -> (1,2). This move has a cost of |1 - 1| + |2 - 1| = 1.
- (1,2) -> (3,3). This move uses the first special edge, the cost is 2.
- (3,3) -> (3,4). This move has a cost of |3 - 3| + |4 - 3| = 1.
- (3,4) -> (4,5). This move uses the second special edge, the cost is 1.
So the total cost is 1 + 2 + 1 + 1 = 5.
It can be shown that we cannot achieve a smaller total cost than 5.

Example 2:
Input: start = [3,2], target = [5,7], specialRoads = [[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]
Output: 7
Explanation: It is optimal to not use any special edges and go directly from the starting to the ending position with a cost |5 - 3| + |7 - 2| = 7.

Constraints:
start.length == target.length == 2
1 <= startX <= targetX <= 10^5
1 <= startY <= targetY <= 10^5
1 <= specialRoads.length <= 200
specialRoads[i].length == 5
startX <= x1i, x2i <= targetX
startY <= y1i, y2i <= targetY
1 <= cost_i <= 10^5
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{11, new int[]{1, 1}, new int[]{4, 10}, TestUtil.getArray("[[1,6,2,1,2],[4,2,3,8,4],[2,5,1,3,5],[2,4,3,8,5]]")},
				{5, new int[]{1, 1}, new int[]{4, 5}, TestUtil.getArray("[[1,2,3,3,2],[3,4,4,5,1]]")},
				{7, new int[]{3, 2}, new int[]{5, 7}, TestUtil.getArray("[[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]")},
		});
	}

	public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
		int currentMinCost = getCost(start[0], start[1], target[0], target[1]), costs[] = new int[specialRoads.length];
		if (currentMinCost < 2) return currentMinCost;
		Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
		for (int i = 0, road[]; i < specialRoads.length; i++)
			pq.offer(new int[]{costs[i] = getCost(start[0], start[1], (road = specialRoads[i])[0], road[1]) + road[4], i});
		for (int tuple[], current, previous[]; !pq.isEmpty() && (tuple = pq.poll())[0] < currentMinCost; ) {
			if (tuple[0] != costs[current = tuple[1]]) continue;
			currentMinCost = Math.min(currentMinCost, costs[current] + getCost((previous = specialRoads[current])[2], previous[3], target[0], target[1]));
			for (int next = 0, nextRoad[], nCost; next < specialRoads.length; next++)
				if (costs[current] + (nCost = (nextRoad = specialRoads[next])[4] + getCost(previous[2], previous[3], nextRoad[0], nextRoad[1])) < costs[next])
					pq.offer(new int[]{costs[next] = costs[current] + nCost, next});
		}
		return currentMinCost;
	}

	int getCost(int x1, int y1, int x2, int y2) {return Math.abs(x1 - x2) + Math.abs(y1 - y2);}
}
