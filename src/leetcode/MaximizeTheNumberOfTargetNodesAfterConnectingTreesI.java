package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static leetcode.util.TestUtil.getArray;

public class MaximizeTheNumberOfTargetNodesAfterConnectingTreesI {
    /*
    There exist two undirected trees with n and m nodes, with distinct labels in ranges [0, n - 1] and [0, m - 1], respectively.
    You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.
    You are also given an integer k.
    Node u is target to node v if the number of edges on the path from u to v is less than or equal to k.
    Note that a node is always target to itself.
    Return an array of n integers answer, where answer[i] is the maximum possible number of nodes target to node i of the first tree if you have to connect one node from the first tree to another node in the second tree.
    Note that queries are independent of each other. That is, for every query you will remove the added edge before proceeding to the next query.

    Example 1:
    Input: edges1 = [[0,1],[0,2],[2,3],[2,4]], edges2 = [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]], k = 2
    Output: [9,7,9,8,8]
    Explanation:
        For i = 0, connect node 0 from the first tree to node 0 from the second tree.
        For i = 1, connect node 1 from the first tree to node 0 from the second tree.
        For i = 2, connect node 2 from the first tree to node 4 from the second tree.
        For i = 3, connect node 3 from the first tree to node 4 from the second tree.
        For i = 4, connect node 4 from the first tree to node 4 from the second tree.

    Example 2:
    Input: edges1 = [[0,1],[0,2],[0,3],[0,4]], edges2 = [[0,1],[1,2],[2,3]], k = 1
    Output: [6,3,3,3,3]
    Explanation:
    For every i, connect node i of the first tree with any node of the second tree.

    Constraints:
        2 <= n, m <= 1000
        edges1.length == n - 1
        edges2.length == m - 1
        edges1[i].length == edges2[i].length == 2
        edges1[i] = [ai, bi]
        0 <= ai, bi < n
        edges2[i] = [ui, vi]
        0 <= ui, vi < m
        The input is generated such that edges1 and edges2 represent valid trees.
        0 <= k <= 1000
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[]{18, 18, 18, 18, 18, 18, 18, 18},
                        getArray("[[2,1],[7,3],[0,4],[7,5],[2,6],[0,2],[0,7]]"),
                        getArray("[[3,0],[1,2],[5,1],[6,3],[9,4],[5,6],[7,5],[9,7],[8,9]]"),
                        7},
                {new int[]{9, 7, 9, 8, 8}, getArray("[[0,1],[0,2],[2,3],[2,4]]"), getArray("[[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]]"), 2},
                {new int[]{6, 3, 3, 3, 3}, getArray("[[0,1],[0,2],[0,3],[0,4]]"), getArray("[[0,1],[1,2],[2,3]]"), 1},
        });
    }

    /**
     * @noinspection OptionalGetWithoutIsPresent
     */
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int result[] = maxTargetNodes(edges1, k), result2 = Arrays.stream(maxTargetNodes(edges2, k - 1)).max().getAsInt();
        for (int i = 0; i < result.length; i++) result[i] += result2;
        return result;
    }

    int[] maxTargetNodes(int[][] edges, int k) {
        int[] result = new int[edges.length + 1];
        List<List<Integer>> graph = new ArrayList<>(result.length);
        for (int i = 0; i < result.length; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        for (int i = 0; i < result.length; i++) dfs(i, i, graph, result, k, -1);
        return result;
    }

    void dfs(int root, int node, List<List<Integer>> graph, int[] result, int k, int parent) {
        if (k-- < 0) return;
        result[root]++;
        for (Integer child : graph.get(node)) if (child != parent) dfs(root, child, graph, result, k, node);
    }
}
