package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Triangle {

  /*
  Given a triangle, find the minimum path sum from top to bottom. 
  Each step you may move to adjacent numbers on the row below.
  
  For example, given the following triangle
  
  [
       [2],
      [3,4],
     [6,5,7],
    [4,1,8,3]
  ]
  
  The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11). 
  */

  @Test
  public void test() {
    assertEquals(11, minimumTotal(
        Arrays.asList(Arrays.asList(2), Arrays.asList(3, 4), Arrays.asList(6, 5, 7), Arrays.asList(4, 1, 8, 3))));
  }

  public int minimumTotal(List<List<Integer>> triangle) {
    for (int i = triangle.size() - 2; i > -1; i--)
      for (int j = 0; j < triangle.get(i).size(); j++)
        triangle.get(i).set(j,
            triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
    return triangle.get(0).get(0);
  }

}
