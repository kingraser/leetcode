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
public class BalancedBinaryTree {

    //Given a binary tree, determine if it is height-balanced. 
    public boolean isBalanced(TreeNode root) {
        if (null == root) return true;
        return isBalanced(root.left) && isBalanced(root.right)
                && Math.abs(getDepth(root.left) - getDepth(root.right)) < 2;
    }

    private int getDepth(TreeNode root) {
        if (null == root) return 0;
        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
    }
}
