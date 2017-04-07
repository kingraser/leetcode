package leetcode;

import static leetcode.FriendCircles.getRoot;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;

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
    int[] roots = IntStream.range(0, n).toArray();
    for (int i = 0, rootA, rootB; i < edges.length; i++)
      if ((rootA = getRoot(roots, edges[i][0])) == (rootB = getRoot(roots, edges[i][1]))) return false; // there is a circle
      else roots[rootA] = rootB; // union
    return true;
  }

}
