/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class BinaryTreeInorderTraversal {
    /*
    Given a binary tree, return the inorder traversal of its nodes' values.
    
    For example:
    Given binary tree {1,#,2,3},
    
       1
        \
         2
        /
       3
    
    return [1,3,2]. 
    */

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = Lists.newArrayList();
        inorderTraversal(root, list);
        return list;
    }

    private void inorderTraversal(TreeNode root, List<Integer> list) {
        if (null == root) return;
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }

    @Test
    public void test() {
        TreeNode n1 = new TreeNode(1), n2 = new TreeNode(2), n3 = new TreeNode(3);
        n1.right = n2;
        n2.left = n3;
        Assert.assertEquals(Lists.newArrayList(1, 3, 2), inorderTraversal(n1));
    }

}
