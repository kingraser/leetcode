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
public class BinaryTreeLevelOrderTraversal {

    /*
    Given a binary tree, return the level order traversal of its nodes' values.
    (ie, from left to right, level by level).
    
    For example:
    Given binary tree {3,9,20,#,#,15,7},
    
         3
        / \
       9  20
         /  \
        15   7
        
    return its level order traversal as:   
    [
        [3],
        [9,20],
        [15,7]
    ]   
    */

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = Lists.newArrayList();
        traverse(root, 1, result);
        return result;
    }

    private void traverse(TreeNode root, int level, List<List<Integer>> result) {
        if (root == null) return;
        if (level > result.size()) result.add(Lists.newArrayList());
        result.get(level - 1).add(root.val);
        traverse(root.left, level + 1, result);
        traverse(root.right, level + 1, result);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        TreeNode root = new TreeNode(3), secl = new TreeNode(9), secr = new TreeNode(20), thirdl = new TreeNode(15),
                thirdr = new TreeNode(7);
        root.left = secl;
        root.right = secr;
        secr.left = thirdl;
        secr.right = thirdr;
        Assert.assertEquals(
                Lists.newArrayList(Lists.newArrayList(3), Lists.newArrayList(9, 20), Lists.newArrayList(15, 7)),
                levelOrder(root));
    }

}
