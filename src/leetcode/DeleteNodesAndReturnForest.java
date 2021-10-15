package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class DeleteNodesAndReturnForest {
    /*
    Given the root of a binary tree, each node in the tree has a distinct value.
    After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
    Return the roots of the trees in the remaining forest. You may return the result in any order.

    Example 1:
    Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
    Output: [[1,2,null,4],[6],[7]]

    Example 2:
    Input: root = [1,2,4,null,3], to_delete = [3]
    Output: [[1,2,4]]

    Constraints:
    The number of nodes in the given tree is at most 1000.
    Each node has a distinct value between 1 and 1000.
    to_delete.length <= 1000
    to_delete contains distinct values between 1 and 1000.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of(TreeNode.tree("1,2,4,n,n,n,n"), TreeNode.tree("6,n,n"), TreeNode.tree("7,n,n")),
                        TreeNode.tree("1,2,4,n,n,5,n,n,3,6,n,n,7,n,n"), new int[]{3, 5}},
                {List.of(TreeNode.tree("1,2,n,n,4,n,n")),
                        TreeNode.tree("1,2,n,3,n,n,4,n,n"), new int[]{3}},
        });
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> result = new ArrayList<>(1000);
        int[] toDelete = new int[1001];
        for (int i : to_delete) toDelete[i]++;
        delNodes(root, true, toDelete, result);
        return result;
    }

    private TreeNode delNodes(TreeNode node, boolean isRoot, int[] toDelete, List<TreeNode> result) {
        if (node == null) return null;
        boolean deleted = toDelete[node.val] > 0;
        if (isRoot && !deleted) result.add(node);
        node.left = delNodes(node.left, deleted, toDelete, result);
        node.right = delNodes(node.right, deleted, toDelete, result);
        return deleted ? null : node;
    }
}
