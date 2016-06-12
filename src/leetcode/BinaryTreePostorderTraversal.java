/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class BinaryTreePostorderTraversal {

    /*    
    Given a binary tree, return the postorder traversal of its nodes' values.
    
    For example:
    Given binary tree {1,#,2,3},
    
               1
                \
                 2
                /
               3
    
    return [3,2,1]. 
    */

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList(3, 2, 1), postorderTraversal(TreeNode.generateTree("1,n,2,3,n,n,n")));
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversal(root, list);
        return list;
    }

    private void postorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) return;
        postorderTraversal(root.left, list);
        postorderTraversal(root.right, list);
        list.add(root.val);
    }

}
