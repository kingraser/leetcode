package leetcode;

import leetcode.common.TreeNode;

import java.util.Objects;

/**
 * @author Wit
 */
public class UnivaluedBinaryTree {
    /*
    A binary tree is univalued if every node in the tree has the same value.
    Return true if and only if the given tree is univalued.

    Example 1:
    Input: [1,1,1,1,1,null,1]
    Output: true

    Example 2:
    Input: [2,2,2,5,2]
    Output: false

    Note:
    The number of nodes in the given tree will be in the range [1, 100].
    Each node's value will be an integer in the range [0, 99].
    */

    public boolean isUnivalTree(TreeNode root) {
        return Objects.isNull(root) || (Objects.isNull(root.left) || root.val == root.left.val && isUnivalTree(root.left)) && (Objects.isNull(root.right) || root.val == root.right.val && isUnivalTree(root.right));
    }
}
