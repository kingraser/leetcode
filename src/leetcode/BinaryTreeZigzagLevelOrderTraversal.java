/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月18日<p>
//-------------------------------------------------------
public class BinaryTreeZigzagLevelOrderTraversal {

    /*
    Given a binary tree, return the zigzag level order traversal of its nodes' values. 
    (ie, from left to right, then right to left for the next level and alternate between).
    
    For example:Given binary tree {3,9,20,#,#,15,7},
    
         3
        / \
       9  20
         /  \
        15   7
    
    return its zigzag level order traversal as:    
    [
        [3],
        [20,9],
        [15,7]
    ]
    */

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        traverse(root, 0, result);
        for (int i = 1; i < result.size(); i += 2)//odd reverse
            Collections.reverse(result.get(i));
        return result;
    }

    private void traverse(TreeNode root, int level, List<List<Integer>> result) {
        if (root == null) return;
        if (level == result.size()) result.add(new ArrayList<>());
        result.get(level).add(root.val);
        traverse(root.left, ++level, result);
        traverse(root.right, level, result);
    }

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList(Arrays.asList(3), Arrays.asList(20, 9), Arrays.asList(15, 7)),
                zigzagLevelOrder(TreeNode.generateTree("3,9,n,n,20,15,n,n,7,n,n")));
    }

}
