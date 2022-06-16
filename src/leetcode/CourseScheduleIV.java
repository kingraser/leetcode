package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wit
 */
public class CourseScheduleIV {
    /*
    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course a_i first if you want to take course bi.
    For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
    Prerequisites can also be indirect. If course_a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.
    You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.
    Return a boolean array answer, where answer[j] is the answer to the jth query.

    Example 1:
    Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
    Output: [false,true]
    Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
    Course 0 is not a prerequisite of course 1, but the opposite is true.

    Example 2:
    Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
    Output: [false,false]
    Explanation: There are no prerequisites, and each course is independent.

    Example 3:
    Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
    Output: [true,true]

    Constraints:
    2 <= numCourses <= 100
    0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
    prerequisites[i].length == 2
    0 <= ai, bi <= n - 1
    ai != bi
    All the pairs [ai, bi] are unique.
    The prerequisites graph has no cycles.
    1 <= queries.length <= 10^4
    0 <= ui, vi <= n - 1
    ui != vi
    */
    @Test
    public void test() {
        TestUtil.testEquals(
                Arrays.stream(new Object[][]{{false, true}, {false, false}, {true, true}}).map(a -> Arrays.stream(a).collect(Collectors.toList())).toArray(),
                TestUtil.getInputs("[[2,[[1,0]],[[0,1],[1,0]]],[2,[],[[1,0],[0,1]]],[3, [[1,2],[1,0],[2,0]],[[1,0],[1,2]]]]"));
    }

    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] connected = new boolean[n][n];
        for (int[] prerequisite : prerequisites) connected[prerequisite[0]][prerequisite[1]] = true;
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (!connected[i][j] && connected[i][k] && connected[k][j]) connected[i][j] = true;
        List<Boolean> ans = new ArrayList<>(queries.length);
        for (int[] query : queries) ans.add(connected[query[0]][query[1]]);
        return ans;
    }
}
