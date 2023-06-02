package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class DivideArrayInSetsOfKConsecutiveNumbers {
	/*
	Given an array of integers nums and a positive integer k, check whether it is possible to divide this array into sets of k consecutive numbers.
	Return true if it is possible. Otherwise, return false.

	Example 1:
	Input: nums = [1,2,3,3,4,4,5,6], k = 4
	Output: true
	Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].

	Example 2:
	Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
	Output: true
	Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].

	Example 3:
	Input: nums = [1,2,3,4], k = 3
	Output: false
	Explanation: Each array should be divided in sub-arrays of size 3.

	Constraints:
	1 <= k <= nums.length <= 10^5
	1 <= nums[i] <= 10^9

	Note: This question is the same as 846: https://leetcode.com/problems/hand-of-straights/
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{false, new int[]{1, 1, 2, 2, 3, 3}, 2},
				{true, new int[]{1, 2, 3, 3, 4, 4, 5, 6}, 4},
				{true, new int[]{3, 2, 1, 2, 3, 4, 3, 4, 5, 9, 10, 11}, 3},
				{false, new int[]{1, 2, 3, 4}, 3},
		});
	}

	public boolean isPossibleDivide(int[] nums, int k) {
		if (nums.length % k != 0) return false;
		if (k == 1) return true;
		Queue queue = new Queue(k);
		Arrays.sort(nums);
		for (int num : nums) if (!queue.add(num)) return false;
		if (queue.isFull()) queue.remove();
		return queue.isEmpty();
	}

	public class Queue {
		int head, last, tail, array[], prev = -2;

		public Queue(int k) {array = new int[k + 1];}

		boolean isEmpty() {return head == tail;}

		int size() {return sub(tail, head);}

		boolean isFull() {return size() == array.length - 1;}

		public boolean add(int n) {
			if (n != prev) {
				if (isFull()) remove();
				if (isEmpty() || n == prev + 1) {
					if (size() > 1 && array[last] < array[dec(last)]) return false;
					prev = n;
					array[last = tail] = 1;
					tail = inc(tail);
				} else return false;
			} else array[last]++;
			return true;
		}

		int inc(int i) {return ++i >= array.length ? 0 : i;}

		int dec(int i) {return --i < 0 ? array.length - 1 : i;}

		int sub(int i, int j) {return (i -= j) < 0 ? i + array.length : i;}

		void remove() {
			int headValue = array[head];
			for (int i = 0; i < array.length; ) if ((array[i++] -= headValue) == 0) head = inc(head);
			array[tail] += headValue;
			if (isEmpty()) prev = -2;
		}
	}
}
