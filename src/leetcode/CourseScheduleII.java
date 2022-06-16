package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;

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
        assertArrayEquals(new int[]{0, 1}, findOrder(2, new int[][]{{1, 0}}));
        assertArrayEquals(new int[]{0, 1, 2, 3}, findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
    }

    //Topological Sort
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses], inDegree = new int[numCourses];
        Map<Integer, List<Integer>> courses = new HashMap<>(numCourses);
        Deque<Integer> zeroDegrees = CourseSchedule.getZeroDegrees(prerequisites, inDegree, courses);
        for (int node, i = 0; !zeroDegrees.isEmpty(); result[i++] = node, numCourses--)
            for (int next : courses.getOrDefault(node = zeroDegrees.poll(), new ArrayList<>()))
                if (--inDegree[next] == 0) zeroDegrees.add(next);
        return numCourses == 0 ? result : new int[]{};
    }

}
