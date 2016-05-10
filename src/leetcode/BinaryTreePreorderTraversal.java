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
public class BinaryTreePreorderTraversal {
    /*
    Given a binary tree, return the preorder traversal of its nodes' values.
    
    For example:
    Given binary tree {1,#,2,3},
    
       1
        \
         2
        /
       3
    
    return [1,2,3]. 
    */

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = Lists.newLinkedList();
        if (root == null) return list;
        list.add(root.val);
        list.addAll(preorderTraversal(root.left));
        list.addAll(preorderTraversal(root.right));
        return list;
    }

    @Test
    public void test() {
        TreeNode n1 = TreeNode.generateTree("1,n,2,3,n,n,n");
        Assert.assertEquals(Lists.newArrayList(1, 2, 3), preorderTraversal(n1));
    }
}
