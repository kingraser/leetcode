/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

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

    public static Integer max;

    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        PathSum(root);
        return max;
    }

    public int PathSum(TreeNode root) {
        if (root == null) return 0;
        int l = PathSum(root.left), r = PathSum(root.right), s = root.val;
        if (l > 0) s += l;
        if (r > 0) s += r;
        if (s > max) max = s;
        return Math.max(0, Math.max(r, l)) + root.val;
    }

}
