package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.*;

public class CourseSchedule {

  /*
  There are a total of n courses you have to take, labeled from 0 to n - 1.    
  Some courses may have prerequisites, 
  for example to take course 0 you have to first take course 1, 
  which is expressed as a pair: [0,1]
      
  Given the total number of courses and a list of prerequisite pairs, 
  is it possible for you to finish all courses?
  
  For example:    
  2, [[1,0]]    
  There are a total of 2 courses to take. 
  To take course 1 you should have finished course 0. So it is possible.
  
  2, [[1,0],[0,1]]    
  There are a total of 2 courses to take. 
  To take course 1 you should have finished course 0, 
  and to take course 0 you should also have finished course 1.
  So it is impossible.   
  */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, 2, new int[][]{{1, 0}}},
                {false, 2, new int[][]{{1, 0}, {0, 1}}}
        });
    }

    //Topological Sort
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> courses = new HashMap<>();
        Deque<Integer> zeroDegrees = getZeroDegrees(prerequisites, inDegree, courses);
        for (; !zeroDegrees.isEmpty(); numCourses--)
            for (int next : courses.getOrDefault(zeroDegrees.poll(), new ArrayList<>()))
                if (--inDegree[next] == 0) zeroDegrees.add(next);
        return numCourses == 0;
    }

    public static Deque<Integer> getZeroDegrees(int[][] prerequisites, int[] inDegree, Map<Integer, List<Integer>> courses) {
        for (int[] pair : prerequisites) {
            inDegree[pair[0]]++;
            courses.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
        }
        Deque<Integer> zeroDegrees = new ArrayDeque<>();
        for (int i = 0; i < inDegree.length; i++) if (inDegree[i] == 0) zeroDegrees.add(i);
        return zeroDegrees;
    }

}
