/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import leetcode.common.TreeLinkNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class PopulatingNextRightPointersinEachNode {

  /* 
  For example,
  Given the following perfect binary tree,
  
           1
         /  \
        2    3
       / \  / \
      4  5  6  7
  
  After calling your function, the tree should look like:
  
           1 -> NULL
         /  \
        2 -> 3 -> NULL
       / \  / \
      4->5->6->7 -> NULL
  */

  public void connect(TreeLinkNode root) {
    connect(root, null, null);
  }

  private void connect(TreeLinkNode left, TreeLinkNode right, TreeLinkNode rightLeft) {
    if (null != left) {
      left.next = right == null ? rightLeft : right;
      connect(left.left, left.right, right == null ? null == rightLeft ? null : rightLeft.left : right.left);
    }
    if (null != right) {
      right.next = rightLeft;
      connect(right.left, right.right, null == rightLeft ? null : rightLeft.left);
    }
  }

  public void connectII(TreeLinkNode root) {
    for (TreeLinkNode pre = root, cur = root; pre != null && pre.left != null; pre = pre.left, cur = pre)
      for (; cur != null; cur.left.next = cur.right, cur.right.next = cur.next != null ? cur.next.left
          : null, cur = cur.next);
  }

}
