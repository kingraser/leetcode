package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class NumberofIslandsII {

  /*
  A 2d grid map of m rows and n columns is initially filled with water. 
  We may perform an addLand operation which turns the water at position (row, col) into a land. 
  Given a list of positions to operate, count the number of islands after each addLand operation. 
  An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
  You may assume all four edges of the grid are all surrounded by water.
  
  Example: 
   
  Given m = 3, n = 3, positions = [[0,0], [0,2], [0,1], [1,1]].
  Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
  
  0 0 0
  0 0 0
  0 0 0
  
  Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
  
  1 0 0
  0 0 0   Number of islands = 1
  0 0 0
  
  Operation #2: addLand(0, 2) turns the water at grid[0][2] into a land.
  
  1 0 1
  0 0 0   Number of islands = 2
  0 0 0
  
  Operation #3: addLand(0, 1) turns the water at grid[0][1] into a land.
  
  1 1 1
  0 0 0   Number of islands = 1
  0 0 0
  
  Operation #4: addLand(1, 1) turns the water at grid[1][1] into a land.
  
  1 1 1
  0 1 0   Number of islands = 1
  0 0 0
  
  We return the result as an array: [1, 2, 1, 1]
  
  Challenge:  
  Can you do it in time complexity O(k log mn), where k is the length of the positions?
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(1, 2, 1, 1), numIslands2(new int[][] { { 0, 0 }, { 0, 2 }, { 0, 1 }, { 1, 1 } }));
  }

  int[] dRow = { 0, 0, 1, -1 }, dCol = { 1, -1, 0, 0 };

  public List<Integer> numIslands2(int[][] positions) {
    List<Integer> result = new ArrayList<>();
    int rowCount = positions.length, colCount = positions[0].length, count = 0, point,
        points[] = new int[rowCount * colCount];
    Arrays.fill(points, -1);
    for (int[] position : positions) {
      points[point = position[0] * colCount + position[1]] = point;
      count++;
      for (int k = 0, row, col, neighbour, rootOfNeighbour; k < 4; k++) {
        if ((row = position[0] + dRow[k]) < 0 || row == rowCount || (col = position[1] + dCol[k]) < 0 || col == colCount
            || points[neighbour = row * colCount + col] == -1)
          continue;
        if (points[point] != (rootOfNeighbour = getRoot(points, neighbour))) {
          points[point] = rootOfNeighbour;
          point = rootOfNeighbour;
          count--;
        }
      }
      result.add(count);
    }
    return result;
  }

  public int getRoot(int[] points, int leaf) {
    while (leaf != points[leaf]) {
      points[leaf] = points[points[leaf]];
      leaf = points[leaf];
    }
    return leaf;
  }

}
