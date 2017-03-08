package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class ValidateBinarySearchTree {

  /*
  Given a binary tree, determine if it is a valid binary search tree (BST).
  
  Assume a BST is defined as follows:
  
  The left subtree of a node contains only nodes with keys less than the node's key.
  The right subtree of a node contains only nodes with keys greater than the node's key.
  Both the left and right subtrees must also be binary search trees.    
  */

  @Test
  public void test() {
    assertTrue(isValidBST(tree("2,1,n,n,3,n,n")));
    assertFalse(isValidBST(tree("1,2,n,n,3,n,n")));
  }

  public boolean isValidBST(TreeNode root) {
    return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private boolean isValidBST(TreeNode root, int left, int right) {
    return Objects.isNull(root) ? true
        : root.val > left && root.val < right && isValidBST(root.left, left, root.val)
            && isValidBST(root.right, root.val, right);
  }

}
