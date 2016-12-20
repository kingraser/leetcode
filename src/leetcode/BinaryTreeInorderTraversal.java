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
public class BinaryTreeInorderTraversal {

  /*
  Given a binary tree, return the inorder traversal of its nodes' values.
  
  For example:
  Given binary tree {1,#,2,3},
  
     1
      \
       2
      /
     3
  
  return [1,3,2]. 
  */

  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    inorderTraversal(root, list);
    return list;
  }

  private void inorderTraversal(TreeNode root, List<Integer> list) {
    if (root == null) return;
    inorderTraversal(root.left, list);
    list.add(root.val);
    inorderTraversal(root.right, list);
  }

  @Test
  public void test() {
    assertEquals(Arrays.asList(1, 3, 2), inorderTraversal(tree("1,n,2,3,n,n,n")));
  }

}
