/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        List<List<Integer>> result = new ArrayList<>();
        traverse(root, 1, result);
        return result;
    }

    private void traverse(TreeNode root, int level, List<List<Integer>> result) {
        if (Objects.isNull(root)) return;
        if (level > result.size()) result.add(new ArrayList<>());
        result.get(level - 1).add(root.val);
        traverse(root.left, level + 1, result);
        traverse(root.right, level + 1, result);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        TreeNode root = TreeNode.generateTree("3,9,n,n,20,15,n,n,7,n,n");
        Assert.assertEquals(
                Lists.newArrayList(Lists.newArrayList(3), Lists.newArrayList(9, 20), Lists.newArrayList(15, 7)),
                levelOrder(root));
    }

}
