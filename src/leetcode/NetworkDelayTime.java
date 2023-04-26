package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class NetworkDelayTime {
	/*
	You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
	We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

	Example 1:
	Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
	Output: 2

	Example 2:
	Input: times = [[1,2,1]], n = 2, k = 1
	Output: 1

	Example 3:
	Input: times = [[1,2,1]], n = 2, k = 2
	Output: -1

	Constraints:
	1 <= k <= n <= 100
	1 <= times.length <= 6000
	times[i].length == 3
	1 <= ui, vi <= n
	ui != vi
	0 <= wi <= 100
	All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{22, TestUtil.getArray("[[2,13,18],[15,10,92],[6,15,80],[2,14,68],[13,14,65],[14,3,63],[10,13,59],[9,7,42],[1,14,70],[15,14,34],[11,1,48],[6,7,2],[8,13,48],[15,6,92],[8,7,19],[9,14,53],[3,5,48],[3,10,70],[3,8,57],[5,15,5],[10,14,8],[9,3,8],[15,8,52],[10,5,96],[4,7,52],[14,13,87],[14,10,91],[5,2,17],[3,15,5],[5,1,39],[13,3,39],[7,13,71],[13,2,41],[4,13,20],[11,12,61],[8,4,4],[9,8,80],[9,2,45],[7,9,88],[8,15,96],[1,12,92],[2,7,0],[7,2,43],[3,9,21],[4,2,95],[2,12,35],[2,5,32],[1,9,97],[4,9,95],[15,4,81],[5,13,30],[1,6,43],[1,7,22],[10,3,60],[11,4,9],[4,11,55],[14,5,5],[7,4,13],[15,13,72],[11,3,55],[11,8,50],[3,7,25],[12,11,29],[7,10,71],[7,5,24],[12,14,18],[9,13,89],[7,3,25],[2,9,2],[5,11,83],[6,4,48],[14,1,27],[14,11,21],[8,14,12],[10,1,74],[4,1,97],[4,10,46],[14,8,16],[13,5,39],[9,4,6],[11,7,98],[1,13,10],[8,11,22],[9,11,96],[1,8,56],[3,14,81],[6,11,45],[5,4,48],[4,6,71],[11,15,64],[3,12,74],[2,6,71],[7,8,35],[11,2,20],[7,12,12],[6,14,8],[2,15,42],[8,2,24],[6,12,67],[5,8,90],[2,10,36],[15,7,0],[15,1,68],[12,4,43],[1,5,78],[13,9,97],[2,4,51],[13,15,39],[9,12,93],[5,3,79],[7,1,34],[8,12,37],[12,15,36],[8,5,92],[7,11,96],[14,9,94],[8,1,31],[14,2,18],[2,8,62],[15,3,84],[6,1,3],[10,4,91],[1,3,75],[1,4,9],[11,10,69],[7,15,88],[6,9,25],[9,6,44],[6,8,68],[6,2,96],[1,15,16],[6,3,61],[12,9,50],[13,11,27],[8,6,40],[13,12,45],[14,7,61],[4,15,8],[12,2,87],[14,4,94],[11,6,37],[12,8,10],[13,6,0],[9,15,70],[1,10,26],[14,6,30],[15,2,58],[3,1,12],[10,7,96],[2,3,4],[5,14,99],[8,3,85],[12,10,38],[14,15,34],[4,8,31],[10,8,13],[4,12,57],[12,7,4],[3,2,65],[15,5,85],[12,5,42],[3,6,53],[4,3,3],[10,15,29],[9,5,47],[4,5,43],[9,1,98],[13,4,72],[3,4,88],[5,9,21],[10,12,41],[13,10,3],[3,11,77],[13,7,21],[5,7,88],[6,5,22],[12,6,72],[15,12,37],[9,10,94],[11,14,0],[1,11,51],[5,10,44],[14,12,34],[15,9,85],[11,13,74],[6,10,54],[8,10,9],[13,8,68],[2,11,13],[2,1,91],[12,3,31],[12,13,99],[1,2,84],[12,1,89],[4,14,9],[5,12,34],[7,14,53],[7,6,87],[11,9,20],[3,13,6],[6,13,40],[13,1,94],[10,11,20],[10,6,67],[5,6,27],[8,9,97],[11,5,66],[10,2,73],[10,9,17],[15,11,48]]"), 15, 2},
				{42, TestUtil.getArray("[[5,3,31],[5,1,5],[2,5,18],[2,1,88],[5,2,71],[1,3,35],[4,2,98],[2,3,12],[3,2,73],[1,4,31],[3,5,83],[2,4,17],[3,4,44],[1,2,42],[4,3,44],[4,1,10],[4,5,98],[5,4,31],[3,1,94],[1,5,21]]"), 5, 1},
				{3, TestUtil.getArray("[[1,2,1],[2,3,2],[1,3,4]]"), 3, 1},
				{2, TestUtil.getArray("[[2,1,1],[2,3,1],[3,4,1]]"), 4, 2},
				{1, TestUtil.getArray("[[1,2,1]]"), 2, 1},
				{-1, TestUtil.getArray("[[1,2,1]]"), 2, 2},
		});
	}

	public int networkDelayTime(int[][] times, int n, int k) {
		int time = 0, edges[][][] = new int[n + 1][n - 1][], childCount[] = new int[n + 1], reached[] = new int[n + 1];
		for (int[] edge : times) edges[edge[0]][childCount[edge[0]]++] = edge;
		Heap heap = new Heap(n--);
		for (reached[k]++; childCount[k] > 0; ) heap.add(edges[k][--childCount[k]]);
		for (int edge[], target; n > 0 && heap.size > 0; )
			if (reached[target = (edge = heap.poll())[1]]++ == 0) for (time = edge[2], n--; childCount[target] > 0; ) {
				if (reached[(edge = edges[target][--childCount[target]])[1]] > 0) continue;
				edge[2] += time;
				heap.add(edge);
			}
		return n == 0 ? time : -1;
	}

	public class Heap {
		//values key for node; value for weight
		//priorityQueue key for nothing, value[1] for node index, value[2] for weight; sort by weight
		//indexMap key for node; value for current index in the priorityQueue
		int size = 0, values[], priorityQueue[][], indexMap[];

		public Heap(int n) {
			values = new int[n + 1];
			priorityQueue = new int[n + 1][];
			indexMap = new int[n + 1];
			for (int i = 1; i <= n; indexMap[i++] = -1) values[i] = Integer.MAX_VALUE;
		}

		/**
		 * poll node
		 *
		 * @return 0 for target node; 1 for weight
		 */
		public int[] poll() {
			swap(0, --size);
			down(0);
			return priorityQueue[size];
		}

		void down(int index) {
			if (index >= size) return;
			int leftIndex = (index << 1) + 1, rightIndex = (index << 1) + 2, leftValue = leftIndex >= size ? Integer.MAX_VALUE : priorityQueue[leftIndex][2], rightValue = rightIndex >= size ? Integer.MAX_VALUE : priorityQueue[rightIndex][2], leastChildValue, leastChildIndex;
			if (leftValue < rightValue) {
				leastChildValue = leftValue;
				leastChildIndex = leftIndex;
			} else {
				leastChildValue = rightValue;
				leastChildIndex = rightIndex;
			}
			if (priorityQueue[index][2] <= leastChildValue) return;
			swap(index, leastChildIndex);
			down(leastChildIndex);
		}

		void swap(int i, int j) {
			indexMap[priorityQueue[i][1]] = j;
			indexMap[priorityQueue[j][1]] = i;
			int[] temp = priorityQueue[i];
			priorityQueue[i] = priorityQueue[j];
			priorityQueue[j] = temp;
		}

		/**
		 * add node
		 *
		 * @param array 1 for target node; 2 for weight
		 */
		public void add(int[] array) {
			if (values[array[1]] <= array[2]) return;
			values[array[1]] = array[2];
			if (indexMap[array[1]] == -1) {
				priorityQueue[indexMap[array[1]] = size] = array;
				up(size++);
			} else {
				priorityQueue[indexMap[array[1]]][2] = array[2];
				up(indexMap[array[1]]);
			}
		}

		void up(int i) {
			while (i != 0 && priorityQueue[(i - 1) >> 1][2] > priorityQueue[i][2]) swap(i, i = ((i - 1) >> 1));
		}
	}
}
