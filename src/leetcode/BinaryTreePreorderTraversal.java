/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

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
        List<Integer> list = new ArrayList<>();
        if (Objects.nonNull(root)) preorderTraversal(root, list);
        return list;
    }

    private void preorderTraversal(TreeNode root, List<Integer> list) {
        list.add(root.val);
        if (Objects.nonNull(root.left)) preorderTraversal(root.left, list);
        if (Objects.nonNull(root.right)) preorderTraversal(root.right, list);
    }

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList(1, 2, 3), preorderTraversal(TreeNode.generateTree("1,n,2,3,n,n,n")));
    }
}
