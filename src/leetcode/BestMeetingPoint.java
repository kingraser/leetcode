package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class BestMeetingPoint {

  /*
  A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
  
  For example, given three people living at (0,0), (0,4), and (2,2):
  
  1 - 0 - 0 - 0 - 1
  |   |   |   |   |
  0 - 0 - 0 - 0 - 0
  |   |   |   |   |
  0 - 0 - 1 - 0 - 0
  
  The point (0,2) is an ideal meeting point, as the total travel
  distance of 2+2+2=6 is minimal. So return 6.
  */

  @Test
  public void test() {
    assertEquals(6, minTotalDistance(new int[][] { { 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 } }));
  }

  public int minTotalDistance(int[][] grid) {
    List<Integer> rows = new ArrayList<>(), cols = new ArrayList<>();
    for (int i = 0; i < grid.length; i++)
      for (int j = 0; j < grid[0].length; j++)
        if (grid[i][j] == 1) {
          rows.add(i);
          cols.add(j);
        }
    int sum = 0;
    for (Integer row : rows)
      sum += Math.abs(row - rows.get(rows.size() >> 1));
    Collections.sort(cols);
    for (Integer pos : cols)
      sum += Math.abs(pos - cols.get(cols.size() >> 1));
    return sum;
  }

}
