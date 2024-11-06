package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static leetcode.util.TestUtil.getArray;

public class FindMinimumTimeToReachLastRoomI {
/*
There is a dungeon with n x m rooms arranged as a grid.
You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes exactly one second.
Return the minimum time to reach the room (n - 1, m - 1).
Two rooms are adjacent if they share a common wall, either horizontally or vertically.

Example 1:
Input: moveTime = [[0,4],[4,4]]
Output: 6
Explanation:
The minimum time required is 6 seconds.
    At time t == 4, move from room (0, 0) to room (1, 0) in one second.
    At time t == 5, move from room (1, 0) to room (1, 1) in one second.

Example 2:
Input: moveTime = [[0,0,0],[0,0,0]]
Output: 3
Explanation:
The minimum time required is 3 seconds.
    At time t == 0, move from room (0, 0) to room (1, 0) in one second.
    At time t == 1, move from room (1, 0) to room (1, 1) in one second.
    At time t == 2, move from room (1, 1) to room (1, 2) in one second.

Example 3:
Input: moveTime = [[0,1],[1,2]]
Output: 3

Constraints:
    2 <= n == moveTime.length <= 50
    2 <= m == moveTime[i].length <= 50
    0 <= moveTime[i][j] <= 10^9
*/

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {6, getArray("[[0,4],[4,4]]")},
                {3, getArray("[[0,0,0],[0,0,0]]")},
                {3, getArray("[[0,1],[1,2]]")},
        });
    }

    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int minTimeToReach(int[][] moveTime) {
        int rowSize = moveTime.length, colSize = moveTime[0].length, times[][] = new int[rowSize][colSize];
        for (int[] row : times) Arrays.fill(row, Integer.MAX_VALUE);
        times[0][0] = 0;
        Queue<int[]> queue = new ArrayDeque<>(rowSize + colSize) {{
            add(new int[]{0, 0, 0});
        }};
        for (int current[], time, row, col, newRow, newCol, newTime; !queue.isEmpty(); )
            if (times[row = (current = queue.poll())[0]][col = current[1]] >= (time = current[2]))
                for (int[] dir : dirs)
                    if ((newRow = row + dir[0]) >= 0 && newRow < rowSize && (newCol = col + dir[1]) >= 0 && newCol < colSize && (newTime = Math.max(moveTime[newRow][newCol], time) + 1) < times[newRow][newCol]) {
                        times[newRow][newCol] = newTime;
                        queue.add(new int[]{newRow, newCol, newTime});
                    }
        return times[rowSize - 1][colSize - 1];
    }
}
