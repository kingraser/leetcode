package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class BinaryTreePruning {
    /*
    Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
    A subtree of a node `node` is node plus every node that is a descendant of node.

    Example 1:
    Input: root = [1,null,0,0,1]
    Output: [1,null,0,null,1]
    Explanation:
    Only the red nodes satisfy the property "every subtree not containing a 1".
    The diagram on the right represents the answer.

    Example 2:
    Input: root = [1,0,1,0,0,0,1]
    Output: [1,null,1,null,1]

    Example 3:
    Input: root = [1,1,0,1,1,0,1,0]
    Output: [1,1,0,1,1,null,1]

    Constraints:
    The number of nodes in the tree is in the range [1, 200].
    Node.val is either 0 or 1.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {TreeNode.tree("1,n,0,n,1,n,n"), TreeNode.tree("1,n,0,0,n,n,1,n,n")},
                {TreeNode.tree("1,n,1,n,1,n,n"), TreeNode.tree("1,0,0,n,n,0,n,n,1,0,n,n,1,n,n")},
                {TreeNode.tree("1,1,1,n,n,1,n,n,0,n,1,n,n"), TreeNode.tree("1,1,1,0,n,n,n,1,n,n,0,0,n,n,1,n,n")}
        });
    }

    public TreeNode pruneTree(TreeNode root) {
        if (root.left != null) root.left = pruneTree(root.left);
        if (root.right != null) root.right = pruneTree(root.right);
        return root.isLeaf() && root.val != 1 ? null : root;
    }
}
