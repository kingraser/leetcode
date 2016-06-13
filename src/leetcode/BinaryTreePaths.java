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
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月1日<p>
//-------------------------------------------------------
public class BinaryTreePaths {

    /*
    Given a binary tree, return all root-to-leaf paths.
    
    For example, given the following binary tree:
    
       1
     /   \
    2     3
     \
      5
    
    All root-to-leaf paths are:["1->2->5", "1->3"]
    */

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        dfs(root, new ArrayDeque<>(), result);
        return result;
    }

    private void dfs(TreeNode root, Deque<TreeNode> deque, List<String> result) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            save(deque, result, root);//leaf
            return;
        }
        deque.offerLast(root);
        dfs(root.left, deque, result);
        dfs(root.right, deque, result);
        deque.pollLast();
    }

    private void save(Deque<TreeNode> deque, List<String> result, TreeNode root) {
        StringBuilder sb = new StringBuilder();
        deque.forEach(s -> sb.append(s.val).append("->"));
        result.add(sb.append(root.val).toString());
    }

    @Test
    public void test() {
        List<String> expected = Arrays.asList("1->2->5", "1->3");
        TreeNode root = TreeNode.generateTree("1,2,n,5,n,n,3,n,n");
        Assert.assertEquals(new HashSet<>(expected), new HashSet<>(binaryTreePaths(root)));
    }

}
