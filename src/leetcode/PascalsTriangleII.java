package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class PascalsTriangleII {
  /*
  Given an index k, return the kth row of the Pascal's triangle.
  
  For example, given k = 3,
  Return [1,3,3,1]. 
  */

  public List<Integer> getRow(int k) {
    List<Integer> res = Lists.newArrayList(1);
    for (int j = 1; j <= k; j++)
      res.add(res.get(res.size() - 1) * (k - j + 1) / j);
    return res;
  }

  @Test
  public void test() {
    assertEquals(Arrays.asList(1, 3, 3, 1), getRow(3));
  }
}
