/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月8日<p>
//-------------------------------------------------------
public class GameofLife {

  /*
  According to the Wikipedia's article: "The Game of Life, also known simply as Life, 
  is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
  
  Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
  Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) 
  using the following four rules (taken from the above Wikipedia article):
  
  Any live cell with fewer than two live neighbors dies, as if caused by under-population.
  Any live cell with two or three live neighbors lives on to the next generation.
  Any live cell with more than three live neighbors dies, as if by over-population..
  Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
  
  Write a function to compute the next state (after one update) of the board given its current state.
  
  Follow up:
  
  1Could you solve it in-place? 
  Remember that the board needs to be updated at the same time: 
  You cannot update some cells first and then use their updated values to update other cells.
  2In this question, we represent the board using a 2D array.  
  In principle, the board is infinite, 
  which would cause problems when the active area encroaches the border of the array. 
  How would you address these problems?
  
  011    111
  110 -> 100
  011    111
  */

  @Test
  public void test() {
    int[][] expected = new int[][] { { 1, 1, 1 }, { 1, 0, 0 }, { 1, 1, 1 } },
        actual = new int[][] { { 0, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
    gameOfLife(actual);
    assertArrayEquals(expected, actual);
  }

  public void gameOfLife(int[][] b) {
    for (int i = 0; i < b.length; i++)
      for (int j = 0, live; j < b[0].length; j++)
        if ((live = getLivesAround(b, i, j)) == 3 && b[i][j] == 0) b[i][j] = 3;//3 for now dead and then lives 
        else if (b[i][j] == 1 && (live < 2 || live > 3)) b[i][j] = 2;//2 for now lives and then dead
    for (int i = 0; i < b.length; i++)
      for (int j = 0; j < b[0].length; b[i][j++] &= 1);// %2
  }

  private int getLivesAround(int[][] b, int row, int col) {
    int lives = 0;
    for (int i = row - 1; i < row + 2; i++)
      for (int j = col - 1; j < col + 2; j++)
        if ((i == row && j == col) || i < 0 || i >= b.length || j < 0 || j >= b[0].length) continue;
        else if (b[i][j] == 1 || b[i][j] == 2) lives++;
    return lives;
  }

}
