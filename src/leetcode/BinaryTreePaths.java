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

    static List<String> result = new ArrayList<>();

    static StringBuffer sb = new StringBuffer();

    public List<String> binaryTreePaths(TreeNode root) {
        result.clear();
        sb.setLength(0);
        binaryTreePathsII(root);
        return result;
    }

    public void binaryTreePathsII(TreeNode root) {
        if (root == null) return;
        String val = sb.length() == 0 ? String.valueOf(root.val) : "->" + root.val;
        sb.append(val);
        if (isLeaf(root)) result.add(sb.toString());
        else {
            if (root.left != null) binaryTreePathsII(root.left);
            if (root.right != null) binaryTreePathsII(root.right);
        }
        sb.delete(sb.length() - val.length(), sb.length());
    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
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
