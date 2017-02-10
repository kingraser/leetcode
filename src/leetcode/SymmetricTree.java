/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class SymmetricTree {
  /*
  Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).    
  For example, this binary tree is symmetric:
  
      1
     / \
    2   2
   / \ / \
  3  4 4  3
  
  But the following is not:
  
      1
     / \
    2   2
     \   \
     3    3
  */

  @Test
  public void test() {
    assertTrue(isSymmetric(tree("1,2,3,n,n,4,n,n,2,4,n,n,3,n,n")));
    assertFalse(isSymmetric(tree("1,2,n,3,n,n,2,n,3,n,n")));
  }

  public boolean isSymmetric(TreeNode root) {
    return root == null ? true : isSymmetric(root.left, root.right);
  }

  boolean isSymmetric(TreeNode left, TreeNode right) {
    if (left == null && right == null) return true;
    if (left == null || right == null) return false;
    return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
  }
}
