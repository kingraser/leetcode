/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年3月16日;
//-------------------------------------------------------
public class HouseRobberIII {
  /*
  The thief has found himself a new place for his thievery again. 
  There is only one entrance to this area, called the "root." 
  Besides the root, each house has one and only one parent house. 
  After a tour, the smart thief realized that "all houses in this place forms a binary tree". 
  It will automatically contact the police if two directly-linked houses were broken into on the same night.
  
  Determine the maximum amount of money the thief can rob tonight without alerting the police.
  
  Example 1:
  
       3
      / \
     2   3
      \   \ 
       3   1
  
  Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
  
  Example 2:
  
       3
      / \
     4   5
    / \   \ 
   1   3   1
  
  Maximum amount of money the thief can rob = 4 + 5 = 9. 
  */

  @Test
  public void test() {
    assertEquals(7, rob(tree("2,1,n,3,n,n,4,n,n")));
    assertEquals(7, rob(tree("3,2,n,3,n,n,3,n,1,n,n")));
    assertEquals(7, rob(tree("4,1,2,3,n,n,n,n,n")));
    assertEquals(9, rob(tree("3,4,1,n,n,3,n,n,5,n,1,n,n")));
    assertEquals(0, rob(null));
  }

  public int rob(TreeNode root) {
    int[] res = robSub(root);
    return Math.max(res[0], res[1]);
  }

  //first:without current node, second:with current node 
  private int[] robSub(TreeNode root) {
    if (root == null) return new int[2];
    int[] left = robSub(root.left), right = robSub(root.right);
    return new int[] { Math.max(left[0], left[1]) + Math.max(right[0], right[1]), root.val + left[0] + right[0] };
  }
}
