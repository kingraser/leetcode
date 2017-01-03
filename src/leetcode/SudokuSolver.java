/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Stack;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class SudokuSolver {

  static int row, col, top;

  public void solveSudoku(char[][] board) {
    Stack<Integer> log = new Stack<>();
    for (row = 0; row < 9; row++)
      for (col = 0; col < 9; col++)
        if (board[row][col] == '.') put(board, '1', log);
  }

  private void put(char[][] A, char start, Stack<Integer> log) {
    A[row][col] = '.';
    A: for (char a = start; a <= '9'; a++) {
      for (int i = 0; i < 9; i++)
        if (A[row][i] == a || A[i][col] == a || A[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == a) continue A;//有重复
      A[row][col] = a;//放置
      log.push(row * 10 + col);//历史记录
      return;
    }
    put(A, ++A[row = (top = log.pop()) / 10][col = (top % 10)], log);
  }

  @Test
  public void test() {
    char[][] board = new char[][] { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
        { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
        { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
        { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
        { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } },
        expected = new char[][] { { '5', '3', '4', '6', '7', '8', '9', '1', '2' },
            { '6', '7', '2', '1', '9', '5', '3', '4', '8' }, { '1', '9', '8', '3', '4', '2', '5', '6', '7' },
            { '8', '5', '9', '7', '6', '1', '4', '2', '3' }, { '4', '2', '6', '8', '5', '3', '7', '9', '1' },
            { '7', '1', '3', '9', '2', '4', '8', '5', '6' }, { '9', '6', '1', '5', '3', '7', '2', '8', '4' },
            { '2', '8', '7', '4', '1', '9', '6', '3', '5' }, { '3', '4', '5', '2', '8', '6', '1', '7', '9' } };
    solveSudoku(board);
    assertArrayEquals(expected, board);
  }
}
