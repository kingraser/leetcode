package leetcode;

import leetcode.common.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * @author Wit
 */
public class SearchInABinarySearchTree {
    /*
    Given the root node of a binary search tree (BST) and a value. You need to find the node in the BST that the node's value equals the given value. Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.
    For example,
    Given the tree:
            4
           / \
          2   7
         / \
        1   3
    And the value to search: 2
    You should return this subtree:
          2
         / \
        1   3
    In the example above, if we want to search the value 5, since there is no node with value 5, we should return NULL.

    Note that an empty tree is represented by NULL, therefore you would see the expected output (serialized tree format) as [], not null.
    */

    public TreeNode searchBST(TreeNode root, int val) {
        return Objects.isNull(root) || root.val == val ? root : val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    @Test
    public void test() {
        TreeNode root = TreeNode.tree("4,2,1,n,n,3,n,n,7,n,n");
        Assert.assertEquals(TreeNode.tree("2,1,n,n,3,n,n"), searchBST(root, 2));
        Assert.assertNull(searchBST(root, 5));
    }
}
