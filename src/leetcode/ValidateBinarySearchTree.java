/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月14日<p>
//-------------------------------------------------------
public class ValidateBinarySearchTree {

    /*
    Given a binary tree, determine if it is a valid binary search tree (BST).
    
    Assume a BST is defined as follows:
    
    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.    
    */

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, int left, int right) {
        if (root == null) return true;
        return root.val > left && root.val < right && isValidBST(root.left, left, root.val)
                && isValidBST(root.right, root.val, right);
    }

}
