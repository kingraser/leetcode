package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class BalanceaBinarySearchTree {
    /*
    Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.
    A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.

    Example 1:
    Input: root = [1,null,2,null,3,null,4,null,null]
    Output: [2,1,3,null,null,null,4]
    Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.

    Example 2:
    Input: root = [2,1,3]
    Output: [2,1,3]

    Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    1 <= Node.val <= 10^5
    */
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        inorderTraverse(root, nodes);
        return sortedArrayToBST(nodes, 0, nodes.size() - 1);
    }

    void inorderTraverse(TreeNode root, List<TreeNode> nodes) {
        if (root == null) return;
        inorderTraverse(root.left, nodes);
        nodes.add(root);
        inorderTraverse(root.right, nodes);
    }

    TreeNode sortedArrayToBST(List<TreeNode> nodes, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) >> 1;
        TreeNode root = nodes.get(mid);
        root.left = sortedArrayToBST(nodes, start, mid - 1);
        root.right = sortedArrayToBST(nodes, mid + 1, end);
        return root;
    }

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {TreeNode.tree("2,1,n,n,3,n,4,n,n"), TreeNode.tree("1,n,2,n,3,n,4,n,n")},
                {TreeNode.tree("2,1,n,n,3,n,n"), TreeNode.tree("2,1,n,n,3,n,n")}
        });
    }
}
