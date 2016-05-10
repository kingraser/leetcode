/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

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

    @Test
    public void test() {
        BSTIterator iterator = new BSTIterator(TreeNode.generateTree("3,9,n,n,20,15,n,n,7,n,n"));
        List<Integer> expected = new ArrayList<>();
        while (iterator.hasNext())
            expected.add(iterator.next());
        Assert.assertEquals(Lists.newArrayList(9, 3, 15, 20, 7), expected);
    }

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
