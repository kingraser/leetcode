/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

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
        Assert.assertEquals(Lists.newArrayList(3, 2, 1), postorderTraversal(TreeNode.generateTree("1,n,2,3,n,n,n")));
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = Lists.newArrayList();
        postorderTraversal(root, list);
        return list;
    }

    private void postorderTraversal(TreeNode root, List<Integer> list) {
        if (Objects.isNull(root)) return;
        postorderTraversal(root.left, list);
        postorderTraversal(root.right, list);
        list.add(root.val);
    }

}
