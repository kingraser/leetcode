package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class RedundantConnection {
    /*
    In this problem, a tree is an undirected graph that is connected and has no cycles.
    You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
    Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.

    Example 1:
    Input: edges = [[1,2],[1,3],[2,3]]
    Output: [2,3]

    Example 2:
    Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
    Output: [1,4]

    Constraints:
    n == edges.length
    3 <= n <= 1000
    edges[i].length == 2
    1 <= ai < bi <= edges.length
    ai != bi
    There are no repeated edges.
    The given graph is connected.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[]{2, 3}, new int[][]{{1, 2}, {1, 3}, {2, 3}}},
                {new int[]{1, 4}, new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}}
        });
    }

    static final int[] EMPTY = new int[0];

    public int[] findRedundantConnection(int[][] edges) {
        int array[] = new int[edges.length + 1], from, to;
        for (int[] edge : edges) {
            if ((from = find(array, edge[0])) == (to = find(array, edge[1]))) return edge;
            array[to] = from;
        }
        return EMPTY;
    }

    int find(int[] array, int node) {
        while (array[node] != 0) node = array[node];
        return node;
    }
}
