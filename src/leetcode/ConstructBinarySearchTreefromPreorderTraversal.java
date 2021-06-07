package leetcode;

import leetcode.common.TreeNode;

/**
 * @author Wit
 */
public class ConstructBinarySearchTreefromPreorderTraversal {
    /*
    Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.
    It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.
    A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.
    A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.

    Example 1:
                     8
                    / \
                   5   10
                  / \    \
                 1   7    12
    Input: preorder = [8,5,1,7,10,12]
    Output: [8,5,10,1,7,null,12]

    Example 2:
    Input: preorder = [1,3]
    Output: [1,null,3]

    Constraints:
    1 <= preorder.length <= 100
    1 <= preorder[i] <= 10^8
    All the values of preorder are unique.
    */
    public TreeNode bstFromPreorder(int[] preOrders) {
        return bstFromPreorder(preOrders, Integer.MAX_VALUE, new int[1]);
    }

    public TreeNode bstFromPreorder(int[] preOrders, int bound, int[] idx) {
        if (idx[0] == preOrders.length || preOrders[idx[0]] > bound) return null;
        TreeNode root = new TreeNode(preOrders[idx[0]++]);
        root.left = bstFromPreorder(preOrders, root.val, idx);
        root.right = bstFromPreorder(preOrders, bound, idx);
        return root;
    }
}
