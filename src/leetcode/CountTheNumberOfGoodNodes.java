package leetcode;

import leetcode.common.UndirectedGraphNode;
import leetcode.util.TestUtil;
import org.junit.Test;

public class CountTheNumberOfGoodNodes {
    /*
    There is an undirected tree with n nodes labeled from 0 to n - 1, and rooted at node 0. You are given a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
    A node is good if all the
    subtrees
    rooted at its children have the same size.
    Return the number of good nodes in the given tree.
    A subtree of treeName is a tree consisting of a node in treeName and all of its descendants.

    Example 1:
    Input: edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
    Output: 7
    Explanation:
    All the nodes of the given tree are good.

    Example 2:
    Input: edges = [[0,1],[1,2],[2,3],[3,4],[0,5],[1,6],[2,7],[3,8]]
    Output: 6
    Explanation:
    There are 6 good nodes in the given tree. They are colored in the image above.

    Example 3:
    Input: edges = [[0,1],[1,2],[1,3],[1,4],[0,5],[5,6],[6,7],[7,8],[0,9],[9,10],[9,12],[10,11]]
    Output: 12
    Explanation:
    All nodes except node 9 are good.

    Constraints:
        2 <= n <= 10^5
        edges.length == n - 1
        edges[i].length == 2
        0 <= ai, bi < n
        The input is generated such that edges represents a valid tree.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {6, TestUtil.getArray("[[6,0],[1,0],[5,1],[2,5],[3,1],[4,3]]")},
                {7, TestUtil.getArray("[[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]")},
                {6, TestUtil.getArray("[[0,1],[1,2],[2,3],[3,4],[0,5],[1,6],[2,7],[3,8]]")},
                {12, TestUtil.getArray("[[0,1],[1,2],[1,3],[1,4],[0,5],[5,6],[6,7],[7,8],[0,9],[9,10],[9,12],[10,11]]")},
        });
    }

    int goodNodesCount;
    UndirectedGraphNode[] map;

    public int countGoodNodes(int[][] edges) {
        goodNodesCount = 0;
        constructGraph(edges);
        getChildCount(map[0]);
        return goodNodesCount;
    }

    void constructGraph(int[][] edges) {
        map = new UndirectedGraphNode[edges.length + 1];
        map[0] = new UndirectedGraphNode(0);
        for (int[] edge : edges)
            if (map[edge[0]] != null) map[edge[0]].neighbors.add(map[edge[1]] = new UndirectedGraphNode(edge[1]));
            else map[edge[1]].neighbors.add(map[edge[0]] = new UndirectedGraphNode(edge[0]));
    }

    int getChildCount(UndirectedGraphNode node) {
        int result = 0, prevGrandChildCount = -1;
        for (UndirectedGraphNode child : node.neighbors) {
            int currentGrandChildCount = getChildCount(child);
            if (prevGrandChildCount == -1) prevGrandChildCount = currentGrandChildCount;
            else if (currentGrandChildCount != prevGrandChildCount) prevGrandChildCount = -2;
            result += ++currentGrandChildCount;
        }
        if (prevGrandChildCount >= -1) goodNodesCount++;
        return result;
    }
}
