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
        List<List<Integer>> result = Lists.newArrayList();
        pathSum(root, sum, Lists.newArrayList(), result);
        return result;
    }

    public void pathSum(TreeNode root, int sum, List<Integer> list, List<List<Integer>> result) {
        if (null == root) return;
        list.add(root.val);
        sum -= root.val;
        if (sum == 0 && root.left == null && root.right == null) result.add(Lists.newArrayList(list));
        pathSum(root.left, sum, list, result);
        pathSum(root.right, sum, list, result);
        list.remove(list.size() - 1);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        List<List<Integer>> expected = Lists.newArrayList(Lists.newArrayList(0, 1), Lists.newArrayList(0, 1));
        Assert.assertEquals(expected, pathSum(root, 1));
    }
}
