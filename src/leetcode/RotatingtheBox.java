package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Wit
 */
public class RotatingtheBox {
    /*
    You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
    A stone '#'
    A stationary obstacle '*'
    Empty '.'
    The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
    It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
    Return an n x m matrix representing the box after the rotation described above.

    Example 1:
    Input: box = [["#",".","#"]]
    Output: [["."],
             ["#"],
             ["#"]]

    Example 2:
    Input: box = [["#",".","*","."],
                  ["#","#","*","."]]
    Output: [["#","."],
             ["#","#"],
             ["*","*"],
             [".","."]]

    Example 3:
    Input: box = [["#","#","*",".","*","."],
                  ["#","#","#","*",".","."],
                  ["#","#","#",".","#","."]]
    Output: [[".","#","#"],
             [".","#","#"],
             ["#","#","*"],
             ["#","*","."],
             ["#",".","*"],
             ["#",".","."]]

    Constraints:
    m == box.length
    n == box[i].length
    1 <= m, n <= 500
    box[i][j] is either '#', '*', or '.'.
    */

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        TestUtil.testEquals(Arrays.stream(new Object[][]{
                {List.of(".", "#", "#"), List.of("#.#")},
                {List.of("#.", "##", "**", ".."), List.of("#.*.", "##*.")},
                {List.of(".##", ".##", "##*", "#*.", "#.*", "#.."), List.of("##*.*.", "###*..", "###.#.")},
                {List.of("*", "#", "*", ".", ".", ".", ".", "#", "*", "."), List.of("*#*...#.*.")},
        }).map(testCase -> Arrays.stream(testCase)
                .map(data -> getTestData((List<String>) data))
                .toArray(Object[]::new)).toArray(Object[][]::new));
    }

    char[][] getTestData(List<String> testData) {
        return testData.stream().map(String::toCharArray).toArray(char[][]::new);
    }

    public char[][] rotateTheBox(char[][] box) {
        char[][] result = new char[box[0].length][box.length];
        for (char[] row : box) drop(row);
        for (int row = 0, rowLast = box.length - 1; row < box.length; row++)
            for (int col = 0; col < result.length; ) result[col][rowLast - row] = box[row][col++];
        return result;
    }

    void drop(char[] row) {
        for (int i = row.length, end = i, c; i > 0; )
            if ((c = row[--i]) == '*') end = i;
            else if (c == '#' && i != --end) {
                row[i] = '.';
                row[end] = '#';
            }
    }
}
