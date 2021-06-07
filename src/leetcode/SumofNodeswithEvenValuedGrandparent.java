package leetcode;

import leetcode.common.TreeNode;

/**
 * @author Wit
 */
public class SumofNodeswithEvenValuedGrandparent {
    /*
    Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)
    If there are no nodes with an even-valued grandparent, return 0.

    Example 1:
    Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
    Output: 18
    Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.

    Constraints:
    The number of nodes in the tree is between 1 and 10^4.
    The value of nodes is between 1 and 100.
    */
    public int sumEvenGrandparent(TreeNode root) {
        return sum(root, 1, 1);
    }

    public int sum(TreeNode node, int parent, int grandParent) {
        return node == null ? 0 : (sum(node.left, node.val, parent) + sum(node.right, node.val, parent) + ((grandParent & 1) == 0 ? node.val : 0));
    }
}
