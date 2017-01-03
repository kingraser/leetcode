/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Objects;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class SameTree {

  /*
  Given two binary trees, write a function to check if they are equal or not.    
  Two binary trees are considered equal if they are structurally identical and the nodes have the same value. 
  */

  public boolean isSameTree(TreeNode p, TreeNode q) {
    return Objects.equals(p, q);
  }
}
