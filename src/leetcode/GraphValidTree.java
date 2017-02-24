package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class GraphValidTree {

  /*
  Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
  For example:  
  Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
  Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
  
  Hint:  
    Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
    According to the definition of tree on Wikipedia: "a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree."  
  Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
  */

  @Test
  public void test() {
    assertTrue(validTree(5, new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 } }));
    assertFalse(validTree(5, new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 1, 3 }, { 1, 4 } }));
    assertFalse(validTree(5, new int[][] { { 0, 1 }, { 1, 2 }, { 3, 4 } }));
  }

  public boolean validTree(int n, int[][] edges) {
    if (edges.length != n - 1) return false;
    int[] nums = new int[n];
    Arrays.fill(nums, -1);
    for (int i = 0, x, y; i < edges.length; i++) {
      if ((x = find(nums, edges[i][0])) == (y = find(nums, edges[i][1]))) return false;//there is a circle
      nums[x] = y;//union
    }
    return true;
  }

  int find(int nums[], int i) {
    if (nums[i] == -1) return i;
    return find(nums, nums[i]);
  }

}
