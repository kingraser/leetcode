/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

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
        List<String> answer = new ArrayList<String>();
        if (root != null) searchBT(root, "", answer);
        return answer;
    }

    private void searchBT(TreeNode root, String path, List<String> answer) {
        if (root.left == null && root.right == null) answer.add(path + root.val);
        if (root.left != null) searchBT(root.left, path + root.val + "->", answer);
        if (root.right != null) searchBT(root.right, path + root.val + "->", answer);
    }

    public TreeNode createTestData() {
        TreeNode node1 = new TreeNode(1), node2 = new TreeNode(2), node3 = new TreeNode(3), node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.right = node5;
        return node1;
    }

    @Test
    public void test() {
        System.out.println(binaryTreePaths(createTestData()));
    }

}
