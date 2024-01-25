package leetcode;

import leetcode.util.ArrayUtil;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class MinimumNumberOfWorkSessionsToFinishTheTasks {
	/*
	There are n tasks assigned to you. The task times are represented as an integer array tasks of length n, where the ith task takes tasks[i] hours to finish. A work session is when you work for at most sessionTime consecutive hours and then take a break.
	You should finish the given tasks in a way that satisfies the following conditions:
	If you start a task in a work session, you must complete it in the same work session.
	You can start a new task immediately after finishing the previous one.
	You may complete the tasks in any order.
	Given tasks and sessionTime, return the minimum number of work sessions needed to finish all the tasks following the conditions above.
	The tests are generated such that sessionTime is greater than or equal to the maximum element in tasks[i].

	Example 1:
	Input: tasks = [1,2,3], sessionTime = 3
	Output: 2
	Explanation: You can finish the tasks in two work sessions.
	- First work session: finish the first and the second tasks in 1 + 2 = 3 hours.
	- Second work session: finish the third task in 3 hours.

	Example 2:
	Input: tasks = [3,1,3,1,1], sessionTime = 8
	Output: 2
	Explanation: You can finish the tasks in two work sessions.
	- First work session: finish all the tasks except the last one in 3 + 1 + 3 + 1 = 8 hours.
	- Second work session: finish the last task in 1 hour.

	Example 3:
	Input: tasks = [1,2,3,4,5], sessionTime = 15
	Output: 1
	Explanation: You can finish all the tasks in one work session.

	Constraints:
	n == tasks.length
	1 <= n <= 14
	1 <= tasks[i] <= 10
	max(tasks[i]) <= sessionTime <= 15
	*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, new int[]{1, 2, 3}, 3},
				{2, new int[]{3, 1, 3, 1, 1}, 8},
				{1, new int[]{1, 2, 3, 4, 5}, 15},
		});
	}

	//solution 1 dp+bit-mask
	int sessionTime, tasks[], dp[][];

	public int minSessions(int[] tasks, int sessionTime) {
		dp = new int[(1 << (this.tasks = tasks).length) - 1][sessionTime + 1];
		return backtracking(0, this.sessionTime = sessionTime);
	}

	int backtracking(int state, int remainingTime) {
		if (state == (1 << tasks.length) - 1) return 1;
		if (dp[state][remainingTime] != 0) return dp[state][remainingTime] - 1;
		int answer = 15;
		for (int i = 0, flag; i < tasks.length; i++)
			if (((flag = 1 << i) & state) == 0) {
				state |= flag;
				answer = Math.min(answer, remainingTime < tasks[i] ? backtracking(state, this.sessionTime - tasks[i]) + 1 : backtracking(state, remainingTime - tasks[i]));
				state ^= flag;
			}
		dp[state][remainingTime] = answer + 1;
		return answer;
	}

	public int minSessionsII(int[] tasks, int sessionTime) {
		// Sort tasks in descending order to prioritize longer tasks
		Arrays.sort(tasks);
		ArrayUtil.reverse(tasks);

		int left = 1; // Minimum session count
		int right = tasks.length; // Maximum session count
		int answer = 0; // Minimum number of sessions required

		// Binary search on the range of possible session counts
		while (left <= right) {
			int mid = left + (right - left) / 2;
			int[] workSessions = new int[mid]; // Array to track tasks assigned to each session

			// Check if it's possible to complete all tasks within the given session count
			if (isPossible(0, mid, sessionTime, 0, tasks, workSessions, mid)) {
				answer = mid; // Update the answer
				right = mid - 1; // Continue searching for a smaller session count
			} else {
				left = mid + 1; // Increase the session count
			}
		}

		return answer;
	}

	// Backtracking function to simulate the distribution of tasks among sessions
	private static boolean isPossible(int idx, int k, int maxLen, int max, int[] tasks, int[] workSessions, int zeroCount) {
		if (tasks.length - idx < zeroCount) {
			return false; // Not enough tasks remaining to fill all sessions
		}

		if (idx == tasks.length) {
			return max <= maxLen; // Current session duration exceeds the maximum session time
// All tasks have been assigned to sessions successfully
		}

		int currentTask = tasks[idx];

		for (int i = 0; i < k; i++) {
			if (workSessions[i] + currentTask > maxLen) {
				continue; // Current task cannot be assigned to this session
			}

			zeroCount -= workSessions[i] == 0 ? 1 : 0; // Decrement zeroCount if a session becomes non-empty
			workSessions[i] += currentTask; // Assign the task to the current session

			// Recursively check if it's possible to complete remaining tasks with the updated sessions
			if (isPossible(idx + 1, k, maxLen, Math.max(max, workSessions[i]), tasks, workSessions, zeroCount)) {
				return true; // Tasks can be completed with the current distribution of sessions
			}

			workSessions[i] -= currentTask; // Backtrack: Remove the task from the current session
			zeroCount += workSessions[i] == 0 ? 1 : 0; // Increment zeroCount if a session becomes empty again

			if (workSessions[i] == 0) {
				break; // Skip empty sessions to avoid redundant iterations
			}
		}

		return false; // Unable to complete all tasks with the current distribution of sessions
	}
}
