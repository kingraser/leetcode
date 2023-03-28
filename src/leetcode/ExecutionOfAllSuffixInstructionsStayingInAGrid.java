package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wit
 */
public class ExecutionOfAllSuffixInstructionsStayingInAGrid {
	/*
	There is an n x n grid, with the top-left cell at (0, 0) and the bottom-right cell at (n - 1, n - 1). You are given the integer n and an integer array startPos where startPos = [startrow, startcol] indicates that a robot is initially at cell (startrow, startcol).
	You are also given a 0-indexed string s of length m where s[i] is the ith instruction for the robot: 'L' (move left), 'R' (move right), 'U' (move up), and 'D' (move down).
	The robot can begin executing from any ith instruction in s. It executes the instructions one by one towards the end of s but it stops if either of these conditions is met:
	The next instruction will move the robot off the grid.
	There are no more instructions left to execute.
	Return an array answer of length m where answer[i] is the number of instructions the robot can execute if the robot begins executing from the ith instruction in s.

	Example 1:
	Input: n = 3, startPos = [0,1], s = "RRDDLU"
	Output: [1,5,4,3,1,0]
	Explanation: Starting from startPos and beginning execution from the ith instruction:
	- 0th: "RRDDLU". Only one instruction "R" can be executed before it moves off the grid.
	- 1st:  "RDDLU". All five instructions can be executed while it stays in the grid and ends at (1, 1).
	- 2nd:   "DDLU". All four instructions can be executed while it stays in the grid and ends at (1, 0).
	- 3rd:    "DLU". All three instructions can be executed while it stays in the grid and ends at (0, 0).
	- 4th:     "LU". Only one instruction "L" can be executed before it moves off the grid.
	- 5th:      "U". If moving up, it would move off the grid.

	Example 2:
	Input: n = 2, startPos = [1,1], s = "LURD"
	Output: [4,1,0,0]
	Explanation:
	- 0th: "LURD".
	- 1st:  "URD".
	- 2nd:   "RD".
	- 3rd:    "D".

	Example 3:
	Input: n = 1, startPos = [0,0], s = "LRUD"
	Output: [0,0,0,0]
	Explanation: No matter which instruction the robot begins execution from, it would move off the grid.

	Constraints:
	m == s.length
	1 <= n, m <= 500
	startPos.length == 2
	0 <= startrow, startcol < n
	s consists of 'L', 'R', 'U', and 'D'.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{1, 5, 4, 3, 1, 0}, 3, new int[]{0, 1}, "RRDDLU"},
				{new int[]{4, 1, 0, 0}, 2, new int[]{1, 1}, "LURD"},
				{new int[4], 1, new int[2], "LRUD"},
		});
	}

	public int[] executeInstructions(int n, int[] startPos, String s) {
		int len = s.length(), result[] = new int[len], borderTop = startPos[0] + 1, borderBottom = n - startPos[0], borderLeft = startPos[1] + 1, borderRight = n - startPos[1];
		Map<Integer, Integer> horizontal = new HashMap<>() {{put(0, len);}}, vertical = new HashMap<>() {{put(0, len);}};
		for (int i = len - 1, row = 0, col = 0, c, local; i >= 0; i--) {
			if ((c = s.charAt(i)) == 'L') col++; // since we move backwards, we need to move to the opposite direction
			else if (c == 'R') col--;
			else if (c == 'U') row++;
			else row--;
			local = len - i;
			if (vertical.containsKey(row - borderTop))
				local = Math.min(local, vertical.get(row - borderTop) - i - 1);
			if (vertical.containsKey(row + borderBottom))
				local = Math.min(local, vertical.get(row + borderBottom) - i - 1);
			if (horizontal.containsKey(col - borderLeft))
				local = Math.min(local, horizontal.get(col - borderLeft) - i - 1);
			if (horizontal.containsKey(col + borderRight))
				local = Math.min(local, horizontal.get(col + borderRight) - i - 1);
			vertical.put(row, i);
			horizontal.put(col, i);
			result[i] = local;
		}
		return result;
	}
}
