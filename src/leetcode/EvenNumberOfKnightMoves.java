package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class EvenNumberOfKnightMoves {

    /*
    You are given two integer arrays start and target, where each array is of the form [x, y] representing a cell on a standard 8 x 8 chessboard.
    Return true if a knight can move from start to target in an even number of moves. Otherwise, return false.
    Note: A valid knight move consists of moving two squares in one direction and one square perpendicular to it. The figure below illustrates all eight possible moves from a cell.

    Example 1:
    Input: start = [1,1], target = [2,2]
    Output: true
    Explanation:
    One possible sequence of moves is (1, 1) -> (3, 2) -> (2, 4) -> (4, 3) -> (2, 2).
    The knight reaches the target in 4 moves, which is even. Thus, the answer is true.

    Example 2:
    Input: start = [4,5], target = [6,6]
    Output: false
    Explanation:num
    It is impossible to reach target = [6, 6] from start = [4, 5] in an even number of moves. Thus, the answer is false.

    Constraints:
    start.length == target.length == 2
    0 <= start[i], target[i] <= 7
    */
    @Test
    public void test() {
        TestUtil.testEquals(
                new Object[]{true, false},
                new Object[][]{
                        {new int[]{1, 1}, new int[]{2, 2}},
                        {new int[]{4, 5}, new int[]{6, 6}},
                }
        );
    }

    public boolean canReach(int[] start, int[] target) {
        return ((start[0] + start[1] + target[0] + target[1]) & 1) == 0;
    }
}
