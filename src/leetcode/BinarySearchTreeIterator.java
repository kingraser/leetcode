/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;

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
    assertEquals(Arrays.asList(3, 9, 20, 15, 7), Lists.newArrayList(new BSTIterator(tree("3,9,n,n,20,15,n,n,7,n,n"))));
  }

  public class BSTIterator implements Iterator<Integer> {
    private Deque<TreeNode> deque = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {
      if (Objects.nonNull(root)) deque.add(root);
    }

    public boolean hasNext() {
      return !deque.isEmpty();
    }

    public Integer next() {
      TreeNode node = deque.pollFirst();
      if (Objects.nonNull(node.left)) deque.addLast(node.left);
      if (Objects.nonNull(node.right)) deque.addLast(node.right);
      return node.val;
    }
  }
}
