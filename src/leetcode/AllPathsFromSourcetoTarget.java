package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class AllPathsFromSourcetoTarget {
    /*
    Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
    The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
    
    Example 1:
    Input: graph = [[1,2],[3],[3],[]]
    Output: [[0,1,3],[0,2,3]]
    Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
    
    Example 2:
    Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
    Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
    
    Example 3:
    Input: graph = [[1],[]]
    Output: [[0,1]]
    
    Example 4:
    Input: graph = [[1,2,3],[2],[3],[]]
    Output: [[0,1,2,3],[0,2,3],[0,3]]
    
    Example 5:
    Input: graph = [[1,3],[2],[3],[]]
    Output: [[0,1,2,3],[0,3]]
     
    Constraints:
    n == graph.length
    2 <= n <= 15
    0 <= graph[i][j] < n
    graph[i][j] != i (i.e., there will be no self-loops).
    All the elements of graph[i] are unique.
    The input graph is guaranteed to be a DAG. 
    */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(graph, new ArrayList<>(1 << 4) {{add(0);}}, result, 1);
        return result;
    }

    void dfs(int[][] graph, List<Integer> list, List<List<Integer>> result, Integer reached) {
        int lastIdx = list.size() - 1, currentNode = list.get(lastIdx++), mask;
        if (currentNode == graph.length - 1) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int nextNode : graph[currentNode])
            if (((mask = 1 << nextNode) & reached) == 0) {
                list.add(nextNode);
                dfs(graph, list, result, reached | mask);
                list.remove(lastIdx);
            }
    }

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {
                        List.of(List.of(0, 1, 3), List.of(0, 2, 3)),
                        new int[][]{{1, 2}, {3}, {3}, {}}
                },
                {
                        List.of(List.of(0, 4),
                                List.of(0, 3, 4),
                                List.of(0, 1, 3, 4),
                                List.of(0, 1, 2, 3, 4),
                                List.of(0, 1, 4)),
                        new int[][]{{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}}
                },
                {
                        List.of(List.of(0, 1)),
                        new int[][]{{1}, {}}
                },
                {
                        List.of(List.of(0, 1, 2, 3), List.of(0, 2, 3), List.of(0, 3)),
                        new int[][]{{1, 2, 3}, {2}, {3}, {}}
                },
                {
                        List.of(List.of(0, 1, 2, 3), List.of(0, 3)),
                        new int[][]{{1, 3}, {2}, {3}, {}}
                }
        });
    }

}
