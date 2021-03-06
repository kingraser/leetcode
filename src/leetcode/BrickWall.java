package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class BrickWall {

  /*
  There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. 
  The bricks have the same height but different width. 
  You want to draw a vertical line from the top to the bottom and cross the least bricks.
  
  The brick wall is represented by a list of rows. 
  Each row is a list of integers representing the width of each brick in this row from left to right.
  
  If your line go through the edge of a brick, then the brick is not considered as crossed. 
  You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.
  
  You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.
  
  Example:  
  Input: 
  [[1,2,2,1],
  [3,1,2],
  [1,3,2],
  [2,4],
  [3,1,2],
  [1,3,1,1]]
  Output: 2
   
  Note:  
    The width sum of bricks in different rows are the same and won't exceed INT_MAX.
    The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. 
    Total number of bricks of the wall won't exceed 20,000.
  */

  @Test
  public void test() {
    assertEquals(3, leastBricks(Arrays.asList(Arrays.asList(1), Arrays.asList(1), Arrays.asList(1))));
    assertEquals(2, leastBricks(Arrays.asList(Arrays.asList(1, 2, 2, 1), Arrays.asList(3, 1, 2), Arrays.asList(1, 3, 2),
        Arrays.asList(2, 4), Arrays.asList(3, 1, 2), Arrays.asList(1, 3, 1, 1))));
  }

  public int leastBricks(List<List<Integer>> wall) {
    Map<Integer, Integer> map = new HashMap<>();
    for (List<Integer> row : wall)
      for (int i = 0, sum = 0, size = row.size() - 1; i < size; i++)
        map.merge(sum += row.get(i), 1, Integer::sum);
    return wall.size() - map.values().stream().mapToInt(i -> i.intValue()).max().orElse(0);
  }

}
