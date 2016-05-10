/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Objects;

import org.junit.Assert;
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
        Assert.assertEquals(6, maxPathSum(TreeNode.generateTree("1,2,n,n,3,n,n")));
    }

    public static Integer max;

    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        PathSum(root);
        return max;
    }

    public int PathSum(TreeNode root) {
        if (Objects.isNull(root)) return 0;
        int l = PathSum(root.left), r = PathSum(root.right), s = root.val;
        s += (l > 0 ? l : 0) + (r > 0 ? r : 0);
        if (s > max) max = s;
        return Math.max(0, Math.max(r, l)) + root.val;
    }

}
