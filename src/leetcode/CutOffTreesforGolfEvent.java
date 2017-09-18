package leetcode;

import static leetcode.BattleshipsinaBoard.DIRS;
import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Test;

import leetcode.util.ArrayUtil;

public class CutOffTreesforGolfEvent {

  /*
  You are asked to cut off trees in a forest for a golf event. 
  The forest is represented as a non-negative 2D map, in this map:  
    0 represents the obstacle can't be reached.
    1 represents the ground can be walked through.
    The place with number bigger than 1 represents a tree can be walked through, 
    and this positive number represents the tree's height.
  
  You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. 
  And after cutting, the original place has the tree will become a grass (value 1).  
  You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. 
  If you can't cut off all the trees, output -1 in that situation.  
  You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
  
  Example 1:  
  Input: 
  [
  [1,2,3],
  [0,0,4],
  [7,6,5]
  ]
  Output: 6
  
  Example 2:  
  Input: 
  [
  [1,2,3],
  [0,0,0],
  [7,6,5]
  ]
  Output: -1
  
  Example 3:  
  Input: 
  [
  [2,3,4],
  [0,0,5],
  [8,7,6]
  ]
  Output: 6
  Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
  
  Hint: size of the given matrix will not exceed 50x50. 
  */

  @Test
  public void test() {
    assertEquals(6, cutOffTree(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(0, 0, 4), Arrays.asList(7, 6, 5))));
    assertEquals(-1, cutOffTree(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(0, 0, 0), Arrays.asList(7, 6, 5))));
    assertEquals(6, cutOffTree(Arrays.asList(Arrays.asList(2, 3, 4), Arrays.asList(0, 0, 5), Arrays.asList(8, 7, 6))));
  }

  public int cutOffTree(List<List<Integer>> forest) {
    if (forest == null || forest.isEmpty()) return 0;
    PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
    for (int row = 0, rowSize = forest.size(); row < rowSize; row++)
      for (int col = 0, colSize = forest.get(0).size(); col < colSize; col++)
        if (forest.get(row).get(col) > 1) queue.add(new int[] { row, col, forest.get(row).get(col) });
    int result = 0;
    for (int step, tree[], start[] = new int[2]; !queue.isEmpty(); result += step, start = tree)
      if ((step = minStep(forest, start, tree = queue.poll())) < 0) return -1;
    return result;
  }

  private int minStep(List<List<Integer>> forest, int[] start, int[] tree) {
    int step = 0;
    BitSet reached = new BitSet();
    Deque<int[]> queue = new ArrayDeque<>();
    for (queue.add(start), reached.set((start[0] << 6) | start[1]); !queue.isEmpty(); step++)
      for (int i = 0, size = queue.size(), cur[], row, col; i < size; i++)
        if (ArrayUtil.equals(tree, 0, 1, cur = queue.poll(), 0, 1)) return step;
        else for (int[] d : DIRS)
          if ((row = cur[0] + d[0]) >= 0 && row < forest.size() && (col = cur[1] + d[1]) >= 0
              && col < forest.get(0).size() && forest.get(row).get(col) > 0 && !reached.get((row << 6) | col)) {
            queue.add(new int[] { row, col });
            reached.set((row << 6) | col);
          }
    return -1;
  }
}
