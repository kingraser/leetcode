/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class ValidSudoku {

  public boolean isValidSudoku(char[][] board) {
    boolean[][] row = new boolean[9][9], col = new boolean[9][9], box = new boolean[9][9];
    for (int i = 0, num; i < 9; i++)
      for (int j = 0; j < 9; j++) {
        if (board[i][j] == '.') continue;
        if (row[i][num = board[i][j] - '1'] || col[j][num] || box[(i / 3) * 3 + j / 3][num]) return false;
        row[i][num] = true;
        col[j][num] = true;
        box[(i / 3) * 3 + j / 3][num] = true;
      }
    return true;
  }

  @Test
  public void test() {
    char[][] board = new char[][] { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
        { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
        { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
        { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
        { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
    assertTrue(isValidSudoku(board));;
  }

}
