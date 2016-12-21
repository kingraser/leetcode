/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年5月11日;
//-------------------------------------------------------
public class CourseScheduleII {
  /*
  There are a total of n courses you have to take, labeled from 0 to n - 1.    
  Some courses may have prerequisites, 
  for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]    
  Given the total number of courses and a list of prerequisite pairs, 
  return the ordering of courses you should take to finish all courses.    
  There may be multiple correct orders, you just need to return one of them. 
  If it is impossible to finish all courses, return an empty array.    
  For example:    
  2, [[1,0]]    
  There are a total of 2 courses to take. 
  To take course 1 you should have finished course 0. So the correct course order is [0,1]    
  4, [[1,0],[2,0],[3,1],[3,2]]    
  There are a total of 4 courses to take. 
  To take course 3 you should have finished both courses 1 and 2. 
  Both courses 1 and 2 should be taken after you finished course 0. 
  So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
  */

  @Test
  public void test() {
    assertArrayEquals(new int[] { 0, 1 }, findOrder(2, new int[][] { { 1, 0 } }));
    assertArrayEquals(new int[] { 0, 1, 2, 3 }, findOrder(4, new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } }));
  }

  //Topological Sort
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] result = new int[numCourses], indegree = new int[numCourses];
    Map<Integer, List<Integer>> courses = new HashMap<>(numCourses);
    Arrays.stream(prerequisites).forEach(pair -> {
      indegree[pair[0]]++;
      courses.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
    });
    Deque<Integer> zeroDegrees = new ArrayDeque<>();
    for (int i = 0; i < indegree.length; i++)
      if (indegree[i] == 0) zeroDegrees.add(i);
    for (Integer node, i = 0; Objects.nonNull(node = zeroDegrees.poll()); result[i++] = node, numCourses--)
      for (int next : courses.getOrDefault(node, new ArrayList<>()))
        if (--indegree[next] == 0) zeroDegrees.add(next);
    return numCourses == 0 ? result : new int[] {};
  }
}
