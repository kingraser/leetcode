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
// <p>@author wit Initial Created at 2015年10月14日<p>
//-------------------------------------------------------
public class ConstructBinaryTreefromPreorderandInorderTraversal {

  @Test
  public void test() {
    assertEquals(tree("1,2,n,4,n,n,3,5,n,n,n"), buildTree(new int[] { 1, 2, 4, 3, 5 }, new int[] { 2, 4, 1, 5, 3 }));
  }

  public TreeNode buildTree(int[] preOrder, int[] inOrder) {
    return buildTree(preOrder, inOrder, 0, preOrder.length, 0, inOrder.length);
  }

  private TreeNode buildTree(int[] preOrder, int[] inOrder, int pFirst, int pLast, int iFirst, int iLast) {
    if (pFirst == pLast || iFirst == iLast) return null;
    TreeNode root = new TreeNode(preOrder[pFirst]);
    int inRootPos = find(inOrder, iFirst, iLast, preOrder[pFirst]), leftSize = inRootPos - iFirst;
    root.left = buildTree(preOrder, inOrder, pFirst + 1, pFirst + leftSize + 1, iFirst, iFirst + leftSize);
    root.right = buildTree(preOrder, inOrder, pFirst + leftSize + 1, pLast, inRootPos + 1, iLast);
    return root;
  }

  private int find(int[] A, int start, int end, int target) {
    for (int i = start; i < end; i++)
      if (A[i] == target) return i;
    return -1;
  }
}
