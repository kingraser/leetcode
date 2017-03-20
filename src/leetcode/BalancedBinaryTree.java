package leetcode;

import static leetcode.common.TreeNode.getDepth;
import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BalancedBinaryTree {

  /*
  Given a binary tree, determine if it is height-balanced.
  
  For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1. 
  */

  @Test
  public void test() {
    assertTrue(isBalanced(tree("1,2,n,n,3,n,n")));
    assertFalse(isBalanced(tree("1,n,2,n,3,n,n")));
  }

  // Given a binary tree, determine if it is height-balanced.
  public boolean isBalanced(TreeNode root) {
    if (Objects.isNull(root)) return true;
    return isBalanced(root.left) && isBalanced(root.right) && Math.abs(getDepth(root.left) - getDepth(root.right)) < 2;
  }

}
