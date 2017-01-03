/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class MinimumDepthofBinaryTree {

  /*
  Given a binary tree, find its minimum depth.    
  The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
  */

  public int minDepth(TreeNode root) {
    if (null == root) return 0;
    if (root.left == null) return 1 + minDepth(root.right);
    if (root.right == null) return 1 + minDepth(root.left);
    return 1 + Math.min(minDepth(root.left), minDepth(root.right));
  }
}
