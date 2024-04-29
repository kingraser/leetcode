package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PascalsTriangleII {
  /*
  Given an index k, return the kth row of the Pascal's triangle.
  
  For example, given k = 3,
  Return [1,3,3,1]. 
  */

  public List<Integer> getRow(int k) {
    List<Integer> res = new ArrayList<>(List.of(1));
    for (int j = 1; j <= k; j++)
      res.add(res.getLast() * (k - j + 1) / j);
    return res;
  }

  @Test
  public void test() {
    assertEquals(List.of(1, 3, 3, 1), getRow(3));
  }
}
