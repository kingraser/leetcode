/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月14日<p>
//-------------------------------------------------------
public class RecoverBinarySearchTree {

  /*
  Two elements of a binary search tree (BST) are swapped by mistake.
  
  Recover the tree without changing its structure.
  Note:
  A solution using O(n) space is pretty straight forward. Could you devise a constant space solution? 
  */

  private static TreeNode mistake1, mistake2, prev;

  public void recoverTree(TreeNode root) {
    inOrder(root);
    swap(mistake1, mistake2);
  }

  private void swap(TreeNode t1, TreeNode t2) {
    int tmp = t1.val;
    t1.val = t2.val;
    t2.val = tmp;
  }

  private void inOrder(TreeNode root) {
    if (root.left != null) inOrder(root.left);
    if (prev != null && root.val < prev.val) {
      if (mistake1 == null) mistake1 = prev;
      mistake2 = root;
    }
    prev = root;
    if (root.right != null) inOrder(root.right);
  }

}
