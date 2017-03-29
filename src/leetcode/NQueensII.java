package leetcode;

import static leetcode.NQueens.isOk;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class NQueensII {

  /*
  Follow up for N-Queens problem.  
  Now, instead outputting board configurations, return the total number of distinct solutions.
  */

  public int totalNQueens(int n) {
    int[] sum = new int[1];
    dfs(sum, new ArrayList<>(), n);
    return sum[0];
  }

  private void dfs(int[] sum, List<Integer> queens, int size) {
    if (queens.size() == size) sum[0]++;
    else for (int col = 0; col < size; col++)
      if (isOk(col, queens)) {
        queens.add(col);
        dfs(sum, queens, size);
        queens.remove(queens.size() - 1);
      }
  }

  @Test
  public void test() {
    assertEquals(92, totalNQueens(8));
  }
}
