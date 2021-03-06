package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class BattleshipsinaBoard {

  /*
  Given an 2D board, count how many different battleships are in it. 
  The battleships are represented with 'X's, empty slots are represented with '.'s. 
  You may assume the following rules:
  
    You receive a valid board, made of only battleships or empty slots.
    Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
    At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
  
  Example:
  
  X..X
  ...X
  ...X
  
  In the above board there are 2 battleships.
  
  Invalid Example:
  
  ...X
  XXXX
  ...X
  
  This is not a valid board - as battleships will always have a cell separating between them.
  
  Your algorithm should not modify the value of the board.
  */

  @Test
  public void test() {
    char[][] board = new char[][] { "X..X".toCharArray(), "...X".toCharArray(), "...X".toCharArray() };
    assertEquals(2, countBattleships(board));
  }

  public static final List<int[]> DIRS = Arrays.asList(new int[] { -1, 0 }, new int[] { 1, 0 }, new int[] { 0, -1 },
      new int[] { 0, 1 });

  public int countBattleships(char[][] board) {
    if (board == null || board.length == 0 || board[0].length == 0) return 0;
    int result = 0, rows = board.length, cols = board[0].length;
    boolean[][] reached = new boolean[rows][cols];
    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
        result += dfs(board, reached, row, col, rows, cols);
    return result;
  }

  private int dfs(char[][] board, boolean[][] reached, int row, int col, int rows, int cols) {
    if (row < 0 || row == rows || col < 0 || col == cols || reached[row][col] || board[row][col] != 'X') return 0;
    reached[row][col] = true;
    DIRS.forEach(dir -> dfs(board, reached, row + dir[0], col + dir[1], rows, cols));
    return 1;
  }
}
