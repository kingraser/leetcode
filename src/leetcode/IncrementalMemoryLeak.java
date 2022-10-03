package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class IncrementalMemoryLeak {
	/*
	You are given two integers memory1 and memory2 representing the available memory in bits on two memory sticks. There is currently a faulty program running that consumes an increasing amount of memory every second.
	At the ith second (starting from 1), i bits of memory are allocated to the stick with more available memory (or from the first memory stick if both have the same available memory). If neither stick has at least i bits of available memory, the program crashes.
	Return an array containing [crashTime, memory1crash, memory2crash], where crashTime is the time (in seconds) when the program crashed and memory1crash and memory2crash are the available bits of memory in the first and second sticks respectively.

	Example 1:
	Input: memory1 = 2, memory2 = 2
	Output: [3,1,0]
	Explanation: The memory is allocated as follows:
	- At the 1st second, 1 bit of memory is allocated to stick 1. The first stick now has 1 bit of available memory.
	- At the 2nd second, 2 bits of memory are allocated to stick 2. The second stick now has 0 bits of available memory.
	- At the 3rd second, the program crashes. The sticks have 1 and 0 bits available respectively.

	Example 2:
	Input: memory1 = 8, memory2 = 11
	Output: [6,0,4]
	Explanation: The memory is allocated as follows:
	- At the 1st second, 1 bit of memory is allocated to stick 2. The second stick now has 10 bit of available memory.
	- At the 2nd second, 2 bits of memory are allocated to stick 2. The second stick now has 8 bits of available memory.
	- At the 3rd second, 3 bits of memory are allocated to stick 1. The first stick now has 5 bits of available memory.
	- At the 4th second, 4 bits of memory are allocated to stick 2. The second stick now has 4 bits of available memory.
	- At the 5th second, 5 bits of memory are allocated to stick 1. The first stick now has 0 bits of available memory.
	- At the 6th second, the program crashes. The sticks have 0 and 4 bits available respectively.

	Constraints:
	0 <= memory1, memory2 <= 2^31 - 1
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{3, 1, 0}, 2, 2},
				{new int[]{6, 0, 4}, 8, 11},
		});
	}

	public int[] memLeak(int memory1, int memory2) {
		int min, record[] = allocate(Math.abs(memory1 - memory2), 1, 1), count = record[0], left = record[1];
		record = memLeak((min = Math.min(memory1, memory2)) + left, min, count + 1);
		record[0] += count;
		if (left != 0 && memory1 < memory2) {
			int temp = record[1];
			record[1] = record[2];
			record[2] = temp;
		}
		return record;
	}

	/**
	 * calculate with start
	 *
	 * @param max   memory1 which is greater than or equals to memory2
	 * @param min   memory2
	 * @param start the first size
	 * @return int[]
	 * 0 for crash time
	 * 1 for max left
	 * 2 for min left
	 */
	int[] memLeak(int max, int min, int start) {
		int result[] = new int[3], record[] = allocate(min, start + 1, 2), finalTry;
		result[0] = (record[0] << 1) + 1;
		result[1] = max - sum(start, record[0], 2);
		result[2] = record[1];
		if (result[1] >= (finalTry = (start + (record[0] << 1)))) {
			result[1] -= finalTry;
			result[0]++;
		}
		return result;
	}


	/**
	 * allocate memory
	 *
	 * @param memory total memory size
	 * @param start  first size
	 * @param step   the diff between neighbour operation
	 * @return int[]
	 * 0 for success count
	 * 1 for memory left
	 */
	int[] allocate(long memory, long start, int step) {
		if (memory < start) return new int[]{0, (int) memory};
		// (start+(start+(n-1)*step))*n/2<=memory
		// step*n^2+(2*start-step)*n-2*memory<=0
		// a=step b=2*start-step c=-2*memory
		long b = (start << 1) - step, c = -(memory << 1);
		int n = (int) ((Math.sqrt(b * b - ((step * c) << 2)) - b) / (step << 1));
		return new int[]{n, (int) (memory - sum(start, n, step))};
	}

	/**
	 * get sum of arithmetic progression
	 *
	 * @param start first num
	 * @param n     count
	 * @param step  the diff between neighbour num
	 * @return the sum
	 */
	int sum(long start, long n, int step) {return (int) ((((start << 1) + (n - 1) * step) * n) >> 1);}
}
