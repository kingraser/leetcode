package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wit
 */
public class AllAncestorsOfANodeInADirectedAcyclicGraph {
    /*
    You are given a positive integer n representing the number of nodes of a Directed Acyclic Graph (DAG). The nodes are numbered from 0 to n - 1 (inclusive).
    You are also given a 2D integer array edges, where edges[i] = [from-i, toi] denotes that there is a unidirectional edge from from-i to toi in the graph.
    Return a list answer, where answer[i] is the list of ancestors of the ith node, sorted in ascending order.
    A node u is an ancestor of another node v if u can reach v via a set of edges.

    Example 1:
    Input: n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
    Output: [[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]
    Explanation:
    The above diagram represents the input graph.
    - Nodes 0, 1, and 2 do not have any ancestors.
    - Node 3 has two ancestors 0 and 1.
    - Node 4 has two ancestors 0 and 2.
    - Node 5 has three ancestors 0, 1, and 3.
    - Node 6 has five ancestors 0, 1, 2, 3, and 4.
    - Node 7 has four ancestors 0, 1, 2, and 3.

    Example 2:
    Input: n = 5, edgeList = [[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
    Output: [[],[0],[0,1],[0,1,2],[0,1,2,3]]
    Explanation:
    The above diagram represents the input graph.
    - Node 0 does not have any ancestor.
    - Node 1 has one ancestor 0.
    - Node 2 has two ancestors 0 and 1.
    - Node 3 has three ancestors 0, 1, and 2.
    - Node 4 has four ancestors 0, 1, 2, and 3.

    Constraints:
    1 <= n <= 1000
    0 <= edges.length <= min(2000, n * (n - 1) / 2)
    edges[i].length == 2
    0 <= from-i, toi <= n - 1
    from-i != toi
    There are no duplicate edges.
    The graph is directed and acyclic.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {Arrays.stream(
                                new int[][]{{}, {}, {}, {0, 1}, {0, 2}, {0, 1, 3}, {0, 1, 2, 3, 4}, {0, 1, 2, 3}})
                        .map(array -> Arrays.stream(array).boxed().collect(Collectors.toList()))
                        .collect(Collectors.toList()),
                        8,
                        new int[][]{{0, 3}, {0, 4}, {1, 3}, {2, 4}, {2, 7}, {3, 5}, {3, 6}, {3, 7}, {4, 6}}},
                {Arrays.stream(
                                new int[][]{{}, {0}, {0, 1}, {0, 1, 2}, {0, 1, 2, 3}})
                        .map(array -> Arrays.stream(array).boxed().collect(Collectors.toList()))
                        .collect(Collectors.toList()),
                        5,
                        new int[][]{{0, 1}, {0, 2}, {0, 3}, {0, 4}, {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4}}}
        });
    }

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        int graph[][] = new int[n][n];
        for (int[] e : edges) graph[e[0]][++graph[e[0]][0]] = e[1];
        List<List<Integer>> ans = new ArrayList<>(n);
        for (int times = n; times-- > 0; ) ans.add(new ArrayList<>(n));
        for (int i = 0; i < n; ) dfs(i, i++, ans, graph);
        return ans;
    }

    void dfs(int ancestor, int kid, List<List<Integer>> ans, int[][] graph) {
        List<Integer> ancestors = ans.get(kid);
        if (!ancestors.isEmpty() && ancestors.get(ancestors.size() - 1) == ancestor) return;
        if (ancestor != kid) ancestors.add(ancestor);
        for (int i = 1; i <= graph[kid][0]; ) dfs(ancestor, graph[kid][i++], ans, graph);
    }
}
