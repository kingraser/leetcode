/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

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

    //广度优先遍历,用一个布尔值记录是从左到右还是从右到左,每一层结束就翻转一下

    //递归
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = Lists.newArrayList();
        traverse(root, 1, result, true);
        return result;
    }

    private void traverse(TreeNode root, int level, List<List<Integer>> result, Boolean leftToRight) {
        if (root == null) return;
        if (level > result.size()) result.add(Lists.newLinkedList());
        if (leftToRight) ((LinkedList<Integer>)result.get(level - 1)).addLast(root.val);
        else ((LinkedList<Integer>)result.get(level - 1)).addFirst(root.val);
        traverse(root.left, level + 1, result, !leftToRight);
        traverse(root.right, level + 1, result, !leftToRight);
    }

    //迭代
    public List<List<Integer>> zigzagLevelOrderII(TreeNode root) {
        List<List<Integer>> result = Lists.newArrayList();
        if (Objects.isNull(root)) return result;
        Queue<TreeNode> q = Lists.newLinkedList(Arrays.asList(root, null));// level separator
        boolean leftToRight = true; //left to right         
        for (List<Integer> level = Lists.newArrayList(); !q.isEmpty();) {// one level's elements
            TreeNode cur = q.poll();
            if (cur != null) {
                level.add(cur.val);
                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            } else {
                if (!leftToRight) Collections.reverse(level);
                result.add(Lists.newArrayList(level));
                level.clear();
                leftToRight = !leftToRight;
                if (q.size() > 0) q.add(null);
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Assert.assertEquals(
                Lists.newArrayList(Lists.newArrayList(3), Lists.newArrayList(20, 9), Lists.newArrayList(15, 7)),
                zigzagLevelOrder(root));
        Assert.assertEquals(
                Lists.newArrayList(Lists.newArrayList(3), Lists.newArrayList(20, 9), Lists.newArrayList(15, 7)),
                zigzagLevelOrderII(root));
    }

}
