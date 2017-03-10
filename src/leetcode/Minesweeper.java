package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class Minesweeper {

  /*
  Let's play the minesweeper game!
  You are given a 2D char matrix representing the game board. 
  'M' represents an unrevealed mine, 
  'E' represents an unrevealed empty square, 
  'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, 
  digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.
  
  Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), 
  return the board after revealing this position according to the following rules:  
    If a mine ('M') is revealed, then the game is over - change it to 'X'.
    If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
    If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
    Return the board when no more squares will be revealed.
  
  Example 1:  
  Input:   
  [['E', 'E', 'E', 'E', 'E'],
  ['E', 'E', 'M', 'E', 'E'],
  ['E', 'E', 'E', 'E', 'E'],
  ['E', 'E', 'E', 'E', 'E']]  
  Click : [3,0]
  
  Output:   
  [['B', '1', 'E', '1', 'B'],
  ['B', '1', 'M', '1', 'B'],
  ['B', '1', '1', '1', 'B'],
  ['B', 'B', 'B', 'B', 'B']]
  
  Example 2:  
  Input:   
  [['B', '1', 'E', '1', 'B'],
  ['B', '1', 'M', '1', 'B'],
  ['B', '1', '1', '1', 'B'],
  ['B', 'B', 'B', 'B', 'B']]
  
  Click : [1,2]  
  Output:   
  [['B', '1', 'E', '1', 'B'],
  ['B', '1', 'X', '1', 'B'],
  ['B', '1', '1', '1', 'B'],
  ['B', 'B', 'B', 'B', 'B']]
  
  Note:  
    The range of the input matrix's height and width is [1,50].
    The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
    The input board won't be a stage when game is over (some mines have been revealed).
    For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares. 
  */

  @Test
  public void test() {
    char[][] board = new char[][] { "EEEEE".toCharArray(), "EEMEE".toCharArray(), "EEEEE".toCharArray(),
        "EEEEE".toCharArray() };
    updateBoard(board, new int[] { 3, 0 });
    assertArrayEquals(
        new char[][] { "B1E1B".toCharArray(), "B1M1B".toCharArray(), "B111B".toCharArray(), "BBBBB".toCharArray() },
        board);
    updateBoard(board, new int[] { 1, 2 });
    assertArrayEquals(
        new char[][] { "B1E1B".toCharArray(), "B1X1B".toCharArray(), "B111B".toCharArray(), "BBBBB".toCharArray() },
        board);
  }

  public char[][] updateBoard(char[][] board, int[] click) {
    int height = board.length, width = board[0].length, row = click[0], col = click[1], count;
    if (board[row][col] == 'M') board[row][col] = 'X';
    else if ((count = getMineCount(board, row, col, height, width)) > 0) board[row][col] = (char) (count + '0');
    else {
      board[row][col] = 'B';
      for (int i = -1, r; i < 2; i++)
        for (int j = -1, c; j < 2; j++)
          if ((i != 0 || j != 0) && (r = row + i) >= 0 && r < height && (c = col + j) >= 0 && c < width
              && board[r][c] == 'E')
            updateBoard(board, new int[] { r, c });
    }
    return board;
  }

  private int getMineCount(char[][] board, int row, int col, int height, int width) {
    int count = 0;
    for (int i = -1, r; i < 2; i++)
      for (int j = -1, c; j < 2; j++)
        if ((i != 0 || j != 0) && (r = row + i) >= 0 && r < height && (c = col + j) >= 0 && c < width
            && board[r][c] == 'M')
          count++;
    return count;
  }
}
