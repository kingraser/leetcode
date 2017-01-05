/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class NQueensII {

  public int totalNQueens(int n) {
    int[] sum = new int[1];
    dfs(sum, new ArrayList<>(), n);
    return sum[0];
  }

  private void dfs(int[] sum, List<Integer> queens, int size) {
    if (queens.size() == size) {
      sum[0]++;
      return;
    }
    for (int col = 0; col < size; col++)
      if (isOk(col, queens)) {
        queens.add(col);
        dfs(sum, queens, size);
        queens.remove(queens.size() - 1);
      }
  }

  public boolean isOk(int col, List<Integer> queens) {
    for (int i = 0, size = queens.size(); i < size; i++)
      if (queens.get(i) == col || Math.abs(i - size) == Math.abs(queens.get(i) - col)) return false;
    return true;
  }

  @Test
  public void test() {
    assertEquals(92, totalNQueens(8));
  }
}
