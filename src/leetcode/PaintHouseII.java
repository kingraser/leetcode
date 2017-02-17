package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PaintHouseII {

  /*
  There are a row of n houses, each house can be painted with one of the k colors. 
  The cost of painting each house with a certain color is different. 
  You have to paint all the houses such that no two adjacent houses have the same color. 
  The cost of painting each house with a certain color is represented by a n x k cost matrix. 
  For example, costs[0][0] is the cost of painting house 0 with color 0; 
  costs[1][2]is the cost of painting house 1 with color 2, and so on... 
  Find the minimum cost to paint all houses.
  
  Note:
  All costs are positive integers.
  
  Follow up:
  Could you solve it in O(nk) runtime?
  */

  @Test
  public void test() {
    assertEquals(4, minCost(new int[][] { { 1, 2, 3 }, { 1, 2, 3 }, { 1, 2, 3 } }));
  }

  public int minCost(int[][] costs) {
    return PaintHouse.minCost(costs);
  }
}
