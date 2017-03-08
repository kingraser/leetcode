package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SurroundedRegions {

  /*
  Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.    
  A region is captured by flipping all 'O's into 'X's in that surrounded region.    
  For example,
  X X X X
  X O O X
  X X O X
  X O X X    
  After running your function, the board should be:
  X X X X
  X X X X
  X X X X
  X O X X    
  */

  public void solve(char[][] board) {
    boolean[][] reached = new boolean[board.length][board[0].length];
    for (int row = 0; row < board.length; row += board.length - 1) // search from top and bottom
      for (int col = 0; col < board[0].length; col++)
        if (board[row][col] == 'O') dfs(board, row + (row == 0 ? 1 : -1), col, reached);
    for (int col = 0; col < board[0].length; col += board[0].length - 1) // search from left and right
      for (int row = 1; row < board.length - 1; row++)
        if (board[row][col] == 'O') dfs(board, row, col + (col == 0 ? 1 : -1), reached);
    for (int row = 1; row < board.length - 1; row++) // change 0 to X
      for (int col = 1; col < board[0].length - 1; col++)
        if (board[row][col] == 'O' && !reached[row][col]) board[row][col] = 'X';
  }

  private int[] dRow = new int[] { 1, -1, 0, 0 }, dCol = new int[] { 0, 0, 1, -1 };

  private void dfs(char[][] A, int row, int col, boolean[][] reached) {
    if (row < 1 || row > A.length - 2 || col < 1 || col > A[0].length - 2 || reached[row][col] || A[row][col] != 'O')
      return;
    reached[row][col] = true;
    for (int i = 0; i < 4; dfs(A, row + dRow[i], col + dCol[i++], reached));
  }

  @Test
  public void test() {
    char[][] expected = new char[][] { "XXXX".toCharArray(), "XXXX".toCharArray(), "XXXX".toCharArray(),
        "XOXX".toCharArray() },
        actual = new char[][] { "XXXX".toCharArray(), "XOOX".toCharArray(), "XXOX".toCharArray(),
            "XOXX".toCharArray() };
    solve(actual);
    assertArrayEquals(expected, actual);
  }

}
