/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月15日<p>
//-------------------------------------------------------
public class PathSumII {
    /*
    Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
    For example:
    Given the below binary tree and sum = 22,
    
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
    
    return
    
    [
       [5,4,11,2],
       [5,8,4,5]
    ]
    */

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        pathSum(root, sum, new ArrayDeque<>(), result);
        return result;
    }

    public void pathSum(TreeNode root, int sum, Deque<Integer> deque, List<List<Integer>> result) {
        if (null == root) return;
        deque.add(root.val);
        if ((sum -= root.val) == 0 && root.left == null && root.right == null) result.add(new ArrayList<>(deque));
        pathSum(root.left, sum, deque, result);
        pathSum(root.right, sum, deque, result);
        deque.removeLast();
    }

    @Test
    public void test() {
        TreeNode root = TreeNode.generateTree("5,4,11,7,n,n,2,n,n,n,8,13,n,n,4,5,n,n,1,n,n");
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(5, 4, 11, 2), Arrays.asList(5, 8, 4, 5));
        Assert.assertEquals(expected, pathSum(root, 22));
    }
}
