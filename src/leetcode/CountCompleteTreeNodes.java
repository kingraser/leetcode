/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月21日<p>
//-------------------------------------------------------
public class CountCompleteTreeNodes {
    /*
    Given a complete binary tree, count the number of nodes.
    
    Definition of a complete binary tree from Wikipedia:
    In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. 
    It can have between 1 and 2h nodes inclusive at the last level h.
    */

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int hl = getDepth(root.left, true), hr = getDepth(root.right, false);
        if (hl == hr) return (2 << hl) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int getDepth(TreeNode node, boolean isLeft) {
        if (node == null) return 0;
        return 1 + getDepth(isLeft ? node.left : node.right, isLeft);
    }

    @Test
    public void test() {
        Assert.assertEquals(5, countNodes(TreeNode.generateTree("3,9,n,n,20,15,n,n,7,n,n")));
    }
}
