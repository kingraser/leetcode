/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月19日<p>
//-------------------------------------------------------
public class KthSmallestElementinaBST {
  /*
  Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.    
  Note:
  You may assume k is always valid, 1 ≤ k ≤ BST's total elements.    
  Follow up:
  What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
  How would you optimize the kthSmallest routine?
  Hint:
      Try to utilize the property of a BST.
      What if you could modify the BST node's structure?
      The optimal runtime complexity is O(height of BST).
  */

  @Test
  public void test() {
    assertEquals(3, kthSmallest(tree("1,n,2,n,3,n,4,n,5,n,n"), 3));
  }

  public int kthSmallest(TreeNode root, int k) {
    int count = countNodes(root.left);
    if (k <= count) return kthSmallest(root.left, k);
    if (k > count + 1) return kthSmallest(root.right, k - 1 - count); // 1 is counted as current node
    return root.val;
  }

  private int countNodes(TreeNode node) {
    if (node == null) return 0;
    return 1 + countNodes(node.left) + countNodes(node.right);
  }

}
