package leetcode;

import static leetcode.BattleshipsinaBoard.DIRS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.BitSet;

import org.junit.Test;

public class WordSearch {

  /*
  Given a 2D board and a word, find if the word exists in the grid.  
  The word can be constructed from letters of sequentially adjacent cell, 
  where "adjacent" cells are those horizontally or vertically neighboring. 
  The same letter cell may not be used more than once.
  For example, Given board =
  
  [
    ["ABCE"],
    ["SFCS"],
    ["ADEE"]
  ]
  
  word = "ABCCED", -> returns true,
  word = "SEE", -> returns true,
  word = "ABCB", -> returns false.
  */

  @Test
  public void test() {
    char[][] board = new char[][] { "ABCE".toCharArray(), "SFCS".toCharArray(), "ADEE".toCharArray() };
    assertTrue(exist(board, "ABCCED"));
    assertTrue(exist(board, "SEE"));
    assertFalse(exist(board, "ABCB"));
  }

  public boolean exist(char[][] board, String word) {
    if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) return false;
    BitSet reached = new BitSet();
    for (int row = 0; row < board.length; row++)
      for (int col = 0; col < board[row].length; col++)
        if (dfs(row, col, 0, board, word, reached)) return true;
    return false;
  }

  private boolean dfs(int row, int col, int idx, char[][] board, String word, BitSet reached) {
    if (row < 0 || col < 0 || row == board.length || col == board[0].length || reached.get(row * board[0].length + col)
        || board[row][col] != word.charAt(idx))
      return false;
    if (idx + 1 == word.length()) return true;
    reached.set(row * board[0].length + col);
    boolean result = DIRS.stream().anyMatch(dir -> dfs(row + dir[0], col + dir[1], idx + 1, board, word, reached));
    reached.clear(row * board[0].length + col);
    return result;
  }

}
