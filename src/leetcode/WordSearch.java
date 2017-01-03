/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.apache.commons.lang3.StringUtils;

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

  static char[][] b;//board

  static boolean[][] r;//reached

  static String s;//word

  public boolean exist(char[][] board, String word) {
    b = board;
    s = word;
    if (b == null || b.length == 0 || b[0].length == 0 || StringUtils.isBlank(s)) return false;
    r = new boolean[b.length][b[0].length];
    for (int i = 0; i < b.length; i++)
      for (int j = 0; j < b[i].length; j++)
        if (DFS(i, j, 0)) return true;
    return false;
  }

  private boolean DFS(int x, int y, int i) {
    if (x < 0 || y < 0 || x == b.length || y == b[0].length || r[x][y] || b[x][y] != s.charAt(i)) return false;
    if (++i == s.length()) return true;
    r[x][y] = true;
    boolean a = DFS(x - 1, y, i) || DFS(x, y - 1, i) || DFS(x + 1, y, i) || DFS(x, y + 1, i);
    r[x][y] = false;
    return a;
  }

  @Test
  public void test() {
    char[][] board = new char[][] { "ABCE".toCharArray(), "SFCS".toCharArray(), "ADEE".toCharArray() };
    assertTrue(exist(board, "ABCCED"));
    assertTrue(exist(board, "SEE"));
    assertFalse(exist(board, "ABCB"));
  }
}
