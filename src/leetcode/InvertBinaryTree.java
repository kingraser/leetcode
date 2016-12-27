/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class InvertBinaryTree {

  @Test
  public void test() {
    assertEquals(tree("1,3,5,n,n,n,2,n,4,n,n"), invertTree(tree("1,2,4,n,n,n,3,n,5,n,n")));
  }

  public TreeNode invertTree(TreeNode root) {
    if (root == null) return root;
    TreeNode left = root.left;
    root.left = invertTree(root.right);
    root.right = invertTree(left);
    return root;
  }
}
