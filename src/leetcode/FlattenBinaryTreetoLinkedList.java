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
// <p>@author wit Initial Created at 2015年9月18日<p>
//-------------------------------------------------------
public class FlattenBinaryTreetoLinkedList {

  /*
  Given a binary tree, flatten it to a linked list in-place.
  
  For example,
  Given
  
           1
          / \
         2   5
        / \   \
       3   4   6
  
  The flattened tree should look like:
  
     1
      \
       2
        \
         3
          \
           4
            \
             5
              \
               6
  */

  @Test
  public void test() {
    TreeNode root = tree("1,2,3,n,n,4,n,n,5,n,6,n,n"), expected = tree("1,n,2,n,3,n,4,n,5,n,6,n,n");
    flatten(root);
    assertEquals(expected, root);

  }

  public void flatten(TreeNode root) {
    flatten(root, null);
  }

  private TreeNode flatten(TreeNode root, TreeNode tail) {
    if (root == null) return tail;
    root.right = flatten(root.left, flatten(root.right, tail));
    root.left = null;
    return root;
  }
}
