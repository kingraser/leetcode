/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class BinaryTreePreorderTraversal {
  /*
  Given a binary tree, return the preorder traversal of its nodes' values.
  
  For example:
  Given binary tree {1,#,2,3},
  
     1
      \
       2
      /
     3
  
  return [1,2,3]. 
  */

  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    if (root != null) preorderTraversal(root, list);
    return list;
  }

  private void preorderTraversal(TreeNode root, List<Integer> list) {
    list.add(root.val);
    if (root.left != null) preorderTraversal(root.left, list);
    if (root.right != null) preorderTraversal(root.right, list);
  }

  @Test
  public void test() {
    assertEquals(Arrays.asList(1, 2, 3), preorderTraversal(tree("1,n,2,3,n,n,n")));
  }
}
