/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月15日<p>
//-------------------------------------------------------
public class BinaryTreeMaximumPathSum {

  /*
  Given a binary tree, find the maximum path sum.
  
  The path may start and end at any node in the tree.
  
  For example:
  Given the below binary tree,
  
     1
    / \
   2   3
  
  Return 6. 
  */

  @Test
  public void test() {
    assertEquals(6, maxPathSum(tree("1,2,n,n,3,n,n")));
  }

  public int maxPathSum(TreeNode root) {
    int[] max = new int[1];
    max[0] = Integer.MIN_VALUE;
    PathSum(root, max);
    return max[0];
  }

  public int PathSum(TreeNode root, int[] max) {
    if (Objects.isNull(root)) return 0;
    int l = PathSum(root.left, max), r = PathSum(root.right, max);
    max[0] = Math.max(max[0], root.val + Math.max(0, l) + Math.max(0, r));
    return Math.max(0, Math.max(r, l)) + root.val;
  }

}
