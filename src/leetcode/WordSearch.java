/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class WordSearch {
  /*
  Given a 2D board and a word, find if the word exists in the grid.
  
  The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
  
  For example,
  Given board =
  
  [
    ["ABCE"],
    ["SFCS"],
    ["ADEE"]
  ]
  
  word = "ABCCED", -> returns true,
  word = "SEE", -> returns true,
  word = "ABCB", -> returns false.
  */

  public boolean exist(char[][] board, String word) {
    if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) return false;
    boolean[][] reached = new boolean[board.length][board[0].length];
    for (int i = 0; i < board.length; i++)
      for (int j = 0; j < board[i].length; j++)
        if (dfs(i, j, 0, board, word, reached)) return true;
    return false;
  }

  private boolean dfs(int x, int y, int i, char[][] board, String word, boolean[][] reached) {
    if (x < 0 || y < 0 || x == board.length || y == board[0].length || reached[x][y] || board[x][y] != word.charAt(i))
      return false;
    if (++i == word.length()) return true;
    reached[x][y] = true;
    boolean result = dfs(x - 1, y, i, board, word, reached) || dfs(x, y - 1, i, board, word, reached)
        || dfs(x + 1, y, i, board, word, reached) || dfs(x, y + 1, i, board, word, reached);
    reached[x][y] = false;
    return result;
  }

  @Test
  public void test() {
    char[][] board = new char[][] { "ABCE".toCharArray(), "SFCS".toCharArray(), "ADEE".toCharArray() };
    assertTrue(exist(board, "ABCCED"));
    assertTrue(exist(board, "SEE"));
    assertFalse(exist(board, "ABCB"));
  }
}
