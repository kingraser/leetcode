/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Objects;
import java.util.Stack;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月18日<p>
//-------------------------------------------------------
public class BinarySearchTreeIterator {

    /*
    Implement an iterator over a binary search tree (BST). 
    Your iterator will be initialized with the root node of a BST.    
    Calling next() will return the next smallest number in the BST.    
    Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, 
    where h is the height of the tree. 
    */
    public class BSTIterator {

        private Stack<TreeNode> stack = new Stack<TreeNode>();

        public BSTIterator(TreeNode root) {
            pushLeft(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            TreeNode node = stack.pop();
            pushLeft(node.right);
            return node.val;
        }

        private void pushLeft(TreeNode node) {
            while (Objects.nonNull(node)) {
                stack.push(node);
                node = node.left;
            }
        }
    }
}
