package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class JumpGameV {
	/*
	Given an array of integers arr and an integer d. In one step you can jump from index i to index:
	i + x where: i + x < arr.length and 0 < x <= d.
	i - x where: i - x >= 0 and 0 < x <= d.
	In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).
	You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.
	Notice that you can not jump outside the array at any time.

	Example 1:
	Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
	Output: 4
	Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
	Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 > 9. You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
	Similarly, You cannot jump from index 3 to index 2 or index 1.

	Example 2:
	Input: arr = [3,3,3,3,3], d = 3
	Output: 1
	Explanation: You can start at any index. You always cannot jump to any index.

	Example 3:
	Input: arr = [7,6,5,4,3,2,1], d = 1
	Output: 7
	Explanation: Start at index 0. You can visit all the indicies.

	Constraints:
	1 <= arr.length <= 1000
	1 <= arr[i] <= 10^5
	1 <= d <= arr.length
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{{12, new int[]{83, 11, 83, 70, 75, 45, 96, 11, 80, 75, 67, 83, 6, 51, 71, 64, 64, 42, 70, 23, 11, 24, 95, 65, 1, 54, 31, 50, 18, 16, 11, 86, 2, 48, 37, 34, 65, 67, 4, 17, 33, 70, 16, 73, 57, 96, 30, 26, 56, 1, 16, 74, 82, 77, 82, 62, 32, 90, 94, 33, 58, 23, 23, 65, 70, 12, 85, 27, 38, 100, 93, 49, 96, 96, 77, 37, 69, 71, 62, 34, 4, 14, 25, 37, 70, 3, 67, 88, 20, 30}, 29}, {4, new int[]{6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12}, 2}, {1, new int[]{3, 3, 3, 3, 3}, 3}, {7, new int[]{7, 6, 5, 4, 3, 2, 1}, 1},});
	}


	public int maxJumps(int[] arr, int d) {
		int res = 1, length = arr.length, dp[] = new int[length + 1];
		Arrays.fill(dp, 1);
		Stack stack1 = new Stack(length), stack2 = new Stack(length);
		for (int i = 0; i <= length; stack1.push(i++))
			for (int num = i < length ? arr[i] : Integer.MAX_VALUE, bottomNum; stack1.isNotEmpty() && num > (bottomNum = arr[stack1.peek()]); ) {
				while (stack1.isNotEmpty() && arr[stack1.peek()] == bottomNum) stack2.push(stack1.pop());
				for (int bottomIndex, peek = stack1.isNotEmpty() ? stack1.peek() : -d - 1; stack2.isNotEmpty(); ) {
					if ((i - (bottomIndex = stack2.pop())) <= d) dp[i] = Math.max(dp[i], dp[bottomIndex] + 1);
					if (bottomIndex - peek <= d) dp[peek] = Math.max(dp[peek], dp[bottomIndex] + 1);
				}
			}
		for (int i = 0; i < length; i++) res = Math.max(res, dp[i]);
		return res;
	}


	class Stack {
		int size = 0, array[];

		public Stack(int n) {array = new int[n];}

		int pop() {return array[--size];}

		int peek() {return array[size - 1];}

		void push(int num) {array[size++] = num;}

		boolean isNotEmpty() {return size != 0;}
	}
}
