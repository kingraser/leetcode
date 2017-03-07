package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.Stack;
import java.util.stream.Stream;

import org.junit.Test;

public class SudokuSolver {

  /*
  Write a program to solve a Sudoku puzzle by filling the empty cells.  
  Empty cells are indicated by the character '.'.  
  You may assume that there will be only one unique solution. 
  */

  @Test
  public void test() {
    char[][] board = Stream.of("53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6",
        ".6....28.", "...419..5", "....8..79").map(s -> s.toCharArray()).toArray(l -> new char[l][]);
    solveSudoku(board);
    assertArrayEquals(Stream.of("534678912", "672195348", "198342567", "859761423", "426853791", "713924856",
        "961537284", "287419635", "345286179").map(s -> s.toCharArray()).toArray(l -> new char[l][]), board);
  }

  static int row, col, top;

  public void solveSudoku(char[][] board) {
    Stack<Integer> records = new Stack<>();
    for (row = 0; row < 9; row++)
      for (col = 0; col < 9; col++)
        if (board[row][col] == '.') put(board, '1', records);
  }

  private void put(char[][] A, char start, Stack<Integer> records) {
    A[row][col] = '.';
    A: for (char a = start; a <= '9'; a++) {
      for (int i = 0; i < 9; i++)
        if (A[row][i] == a || A[i][col] == a || A[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == a) continue A; // conflict
      A[row][col] = a; // place
      records.push(row * 10 + col); // record
      return;
    }
    put(A, ++A[row = (top = records.pop()) / 10][col = (top % 10)], records);
  }

}
