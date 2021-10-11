package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class QueensThatCanAttacktheKing {

    /*
    On an 8x8 chessboard, there can be multiple Black Queens and one White King.
    Given an array of integer coordinates queens that represents the positions of the Black Queens, and a pair of coordinates king that represent the position of the White King, return the coordinates of all the queens (in any order) that can attack the King.

    Example 1:
    Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
    Output: [[0,1],[1,0],[3,3]]
    Explanation:
    The queen at [0,1] can attack the king cause they're in the same row.
    The queen at [1,0] can attack the king cause they're in the same column.
    The queen at [3,3] can attack the king cause they're in the same diagnal.
    The queen at [0,4] can't attack the king cause it's blocked by the queen at [0,1].
    The queen at [4,0] can't attack the king cause it's blocked by the queen at [1,0].
    The queen at [2,4] can't attack the king cause it's not in the same row/column/diagnal as the king.

    Example 2:
    Input: queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
    Output: [[2,2],[3,4],[4,4]]

    Example 3:
    Input: queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
    Output: [[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]

    Constraints:
    1 <= queens.length <= 63
    queens[i].length == 2
    0 <= queens[i][j] < 8
    king.length == 2
    0 <= king[0], king[1] < 8
    At most one piece is allowed in a cell.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {
                        List.of(List.of(1, 0), List.of(0, 1), List.of(3, 3)),
                        new int[][]{{0, 1}, {1, 0}, {4, 0}, {0, 4}, {3, 3}, {2, 4}},
                        new int[]{0, 0}
                },
                {
                        List.of(List.of(3, 4), List.of(4, 4), List.of(2, 2)),
                        new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 4}, {3, 5}, {4, 4}, {4, 5}},
                        new int[]{3, 3}
                },
                {List.of(List.of(1, 4), List.of(5, 4), List.of(3, 7), List.of(4, 5), List.of(2, 3), List.of(4, 3), List.of(1, 6)),
                        new int[][]{{5, 6}, {7, 7}, {2, 1}, {0, 7}, {1, 6}, {5, 1}, {3, 7}, {0, 3}, {4, 0}, {1, 2}, {6, 3}, {5, 0}, {0, 4}, {2, 2}, {1, 1}, {6, 4}, {5, 4}, {0, 0}, {2, 6}, {4, 5}, {5, 2}, {1, 4}, {7, 5}, {2, 3}, {0, 5}, {4, 2}, {1, 0}, {2, 7}, {0, 1}, {4, 6}, {6, 1}, {0, 6}, {4, 3}, {1, 7}},
                        new int[]{3, 4}
                }
        });
    }

    static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] direction : DIRECTIONS)
            queensAttacktheKing(getQueenMap(queens), king[0], king[1], result, direction[0], direction[1]);
        return result;
    }

    int[][] getQueenMap(int[][] queens) {
        int[][] result = new int[8][8];
        for (int[] queen : queens) result[queen[0]][queen[1]] = 1;
        return result;
    }

    void queensAttacktheKing(int[][] queenMap, int kRow, int kCol, List<List<Integer>> res, int rStep, int cStep) {
        for (int row = kRow, col = kCol; isValid(row += rStep) && isValid(col += cStep); ) {
            if (queenMap[row][col] == 0) continue;
            res.add(List.of(row, col));
            return;
        }
    }

    boolean isValid(int coordinate) {return coordinate >= 0 && coordinate < 8;}

}