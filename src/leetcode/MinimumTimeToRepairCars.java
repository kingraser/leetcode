package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Wit
 */
public class MinimumTimeToRepairCars {
	/*
	You are given an integer array ranks representing the ranks of some mechanics. ranksi is the rank of the ith mechanic. A mechanic with a rank r can repair n cars in r * n2 minutes.
	You are also given an integer cars representing the total number of cars waiting in the garage to be repaired.
	Return the minimum time taken to repair all the cars.
	Note: All the mechanics can repair the cars simultaneously.

	Example 1:
	Input: ranks = [4,2,3,1], cars = 10
	Output: 16
	Explanation:
	- The first mechanic will repair two cars. The time required is 4 * 2 * 2 = 16 minutes.
	- The second mechanic will repair two cars. The time required is 2 * 2 * 2 = 8 minutes.
	- The third mechanic will repair two cars. The time required is 3 * 2 * 2 = 12 minutes.
	- The fourth mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
	It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​

	Example 2:
	Input: ranks = [5,1,8], cars = 6
	Output: 16
	Explanation:
	- The first mechanic will repair one car. The time required is 5 * 1 * 1 = 5 minutes.
	- The second mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
	- The third mechanic will repair one car. The time required is 8 * 1 * 1 = 8 minutes.
	It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​

	Constraints:
	1 <= ranks.length <= 10^5
	1 <= ranks[i] <= 100
	1 <= cars <= 10^6
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{16L, new int[]{4, 2, 3, 1}, 10},
				{16L, new int[]{5, 1, 8}, 6},
		});
	}

	public long repairCars(int[] ranks, int cars) {
		long left = 1, right = ((long) ranks[0]) * cars * cars;
		while (left < right) {
			long mid = (left + right) >> 1, repaired = 0;
			for (int rank : ranks) repaired += (long) (Math.sqrt(1.0 * mid / rank));
			if (repaired < cars) left = mid + 1;
			else right = mid;
		}
		return left;
	}

	public long repairCarsII(int[] ranks, int cars) {
		int[] rankCount = new int[101];
		for (int rank : ranks) rankCount[rank]++;
		PriorityQueue<long[]> pq = new PriorityQueue<>(101, Comparator.comparingLong(a -> a[0]));
		for (int i = 1; i <= 100; i++) if (rankCount[i] > 0) pq.add(new long[]{i, i, 1, rankCount[i]});
		//{time for next repair , rank , cars repaired , frequency}
		for (long[] mechanic; cars > 0; mechanic[0] = mechanic[1] * ++mechanic[2] * mechanic[2], pq.add(mechanic))
			if ((cars -= (mechanic = pq.poll())[3]) <= 0) return mechanic[0];
		return 0;
	}
}
