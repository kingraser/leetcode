package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @author Wit
 */
public class FindifPathExistsinGraph {
    /*
    There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
    You want to determine if there is a valid path that exists from vertex start to vertex end.
    Given edges and the integers n, start, and end, return true if there is a valid path from start to end, or false otherwise.

    Example 1:
    Input: n = 3, edges = [[0,1],[1,2],[2,0]], start = 0, end = 2
    Output: true
    Explanation: There are two paths from vertex 0 to vertex 2:
    - 0 → 1 → 2
    - 0 → 2

    Example 2:
    Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], start = 0, end = 5
    Output: false
    Explanation: There is no path from vertex 0 to vertex 5.

    Constraints:
    1 <= n <= 2 * 10^5
    0 <= edges.length <= 2 * 10^5
    edges[i].length == 2
    1 <= ui, vi <= n - 1
    ui != vi
    1 <= start, end <= n - 1
    There are no duplicate edges.
    There are no self edges.
    */
    class Graph {
        int[] nodes;

        public Graph(int n, int[][] edges) {
            nodes = IntStream.range(0, n).toArray();
            for (int[] edge : edges) union(edge[0], edge[1]);
        }

        public boolean areConnected(int node1, int node2) {
            return findGroup(node1) == findGroup(node2);
        }

        public void union(int node1, int node2) {
            nodes[findGroup(node2)] = findGroup(node1);
        }

        private int findGroup(int node) {
            int group = node;
            while (group != nodes[group]) group = nodes[group];
            return nodes[node] = group;
        }
    }

    public boolean validPath(int n, int[][] edges, int start, int end) {
        return new Graph(n, edges).areConnected(start, end);
    }

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, 3, new int[][]{{0, 1}, {1, 2}, {2, 0}}, 0, 2},
                {false, 6, new int[][]{{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}}, 0, 5}
        });
    }
}
