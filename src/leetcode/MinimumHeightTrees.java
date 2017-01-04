/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年3月2日;
//-------------------------------------------------------
public class MinimumHeightTrees {
  /*
  For a undirected graph with tree characteristics, we can choose any node as the root. 
  The result graph is then a rooted tree. 
  Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). 
  Given such a graph, write a function to find all the MHTs and return a list of their root labels.
  
  Format
  The graph contains n nodes which are labeled from 0 to n - 1. 
  You will be given the number n and a list of undirected edges (each edge is a pair of labels).    
  You can assume that no duplicate edges will appear in edges. 
  Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
  
  Example 1:
  
  Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
  
      0
      |
      1
     / \
    2   3
  
  return [1]
  
  Example 2:
  
  Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
  
   0  1  2
    \ | /
      3
      |
      4
      |
      5
  
  return [3, 4] 
  */

  @Test
  public void test() {
    assertEquals(new ArrayList<>(), findMinHeightTrees(0, new int[][] {}));
    assertEquals(Arrays.asList(0), findMinHeightTrees(1, new int[][] { { 0, 0 } }));
    assertEquals(Arrays.asList(1), findMinHeightTrees(4, new int[][] { { 1, 0 }, { 1, 2 }, { 1, 3 } }));
    assertEquals(new HashSet<>(Arrays.asList(3, 4)),
        new HashSet<>(findMinHeightTrees(6, new int[][] { { 0, 3 }, { 1, 3 }, { 2, 3 }, { 4, 3 }, { 5, 4 } })));
  }

  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    Map<Integer, Set<Integer>> map = new HashMap<>();
    for (int[] edge : edges) {
      map.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
      map.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
    }
    List<Integer> leaves = map.keySet().stream().filter(k -> map.get(k).size() == 1).collect(Collectors.toList());
    while (n > 2) {
      n -= leaves.size();
      List<Integer> newLeaves = new ArrayList<>();
      for (int i : leaves) {
        int j = map.get(i).iterator().next();
        map.get(j).remove(i);
        if (map.get(j).size() == 1) newLeaves.add(j);
      }
      leaves = newLeaves;
    }
    return leaves;
  }

}
