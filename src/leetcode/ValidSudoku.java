package leetcode;

import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.Test;

public class ValidSudoku {

  /*
  Determine if a Sudoku is valid.  
  The Sudoku board could be partially filled, where empty cells are filled with the character '.'.  
  Note:
  A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated. 
  */

  @Test
  public void test() {
    assertTrue(isValidSudoku(Stream.of("53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6",
        ".6....28.", "...419..5", "....8..79").map(s -> s.toCharArray()).toArray(l -> new char[l][])));
  }

  public boolean isValidSudoku(char[][] board) {
    boolean[][] row = new boolean[9][9], col = new boolean[9][9], box = new boolean[9][9];
    for (int i = 0; i < 9; i++)
      for (int j = 0, num; j < 9; j++) {
        if (board[i][j] == '.') continue;
        if (row[i][num = board[i][j] - '1'] || col[j][num] || box[(i / 3) * 3 + j / 3][num]) return false;
        row[i][num] = true;
        col[j][num] = true;
        box[(i / 3) * 3 + j / 3][num] = true;
      }
    return true;
  }

}
