package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wit
 */
public class AllPossibleFullBinaryTrees {
    /*
    Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.
    Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.
    A full binary tree is a binary tree where each node has exactly 0 or 2 children.

    Example 1:
    Input: n = 7
    Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]

    Example 2:
    Input: n = 3
    Output: [[0,0,0]]

    Constraints:
    1 <= n <= 20
    */
    Map<Integer, List<TreeNode>> cache = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        cache.clear();
        return allPossibleFBTWithCache(n);
    }

    List<TreeNode> allPossibleFBTWithCache(int n) {
        if ((n & 1) == 0) return new ArrayList<>();
        List<TreeNode> result = cache.get(n);
        if (result == null) cache.put(n, result = allPossibleFBTWithoutCache(n));
        return result;
    }

    List<TreeNode> allPossibleFBTWithoutCache(int n) {
        List<TreeNode> result = new ArrayList<>();
        if (n-- == 1) {
            result.add(new TreeNode(0));
            return result;
        }
        for (int i = 1; i < n; i += 2) {
            List<TreeNode> lefts = allPossibleFBTWithCache(i);
            List<TreeNode> rights = allPossibleFBTWithCache(n - i);
            for (TreeNode left : lefts)
                for (TreeNode right : rights)
                    result.add(new TreeNode(0, new TreeNode(left), new TreeNode(right)));
        }
        return result;
    }

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of(TreeNode.tree("0,0,n,n,0,n,n")), 3},
                {List.of(TreeNode.tree("0,0,n,n,0,0,n,n,0,0,n,n,0,n,n"),
                        TreeNode.tree("0,0,n,n,0,0,0,n,n,0,n,n,0,n,n"),
                        TreeNode.tree("0,0,0,n,n,0,n,n,0,0,n,n,0,n,n"),
                        TreeNode.tree("0,0,0,n,n,0,0,n,n,0,n,n,0,n,n"),
                        TreeNode.tree("0,0,0,0,n,n,0,n,n,0,n,n,0,n,n")), 7}
        });
    }
}
