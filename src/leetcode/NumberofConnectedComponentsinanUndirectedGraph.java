package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumberofConnectedComponentsinanUndirectedGraph {

  /*
  Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
  write a function to find the number of connected components in an undirected graph.
  
  Example 1:
  
     0          3
  
     |          |
  
     1 --- 2    4
  
  Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
  
  Example 2:
  
     0           4
  
     |           |
  
     1 --- 2 --- 3
  
  Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
  
  Note:  
  You can assume that no duplicate edges will appear in edges. 
  Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
  */

  @Test
  public void test() {
    assertEquals(2, countComponents(5, new int[][] { { 0, 1 }, { 1, 2 }, { 3, 4 } }));
    assertEquals(1, countComponents(5, new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } }));
  }

  public int countComponents(int n, int[][] edges) {
    int roots[] = new int[n], root1, root2;
    for (int i = 0; i < n; roots[i] = i++);
    for (int[] e : edges)
      if ((root1 = find(roots, e[0])) != (root2 = find(roots, e[1]))) {
        roots[root1] = root2; //union
        n--;
      }
    return n;
  }

  public int find(int[] roots, int id) {
    while (roots[id] != id)
      id = roots[id] = roots[roots[id]]; //path compression
    return id;
  }
}
