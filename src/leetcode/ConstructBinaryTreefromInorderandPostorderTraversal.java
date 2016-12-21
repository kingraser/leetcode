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
public class ConstructBinaryTreefromInorderandPostorderTraversal {

  @Test
  public void test() {
    assertEquals(tree("1,2,n,4,n,n,3,5,n,n,n"), buildTree(new int[] { 2, 4, 1, 5, 3 }, new int[] { 4, 2, 5, 3, 1 }));
  }

  public TreeNode buildTree(int[] inOrder, int[] postOrder) {
    return buildTree(inOrder, postOrder, 0, inOrder.length, 0, postOrder.length);
  }

  private TreeNode buildTree(int[] inOrder, int[] postOrder, int iFirst, int iLast, int pFirst, int pLast) {
    if (pFirst == pLast || iFirst == iLast) return null;
    TreeNode root = new TreeNode(postOrder[pLast - 1]);
    int inRootPos = find(inOrder, iFirst, iLast, postOrder[pLast - 1]), leftSize = inRootPos - iFirst;
    root.left = buildTree(inOrder, postOrder, iFirst, inRootPos, pFirst, pFirst + leftSize);
    root.right = buildTree(inOrder, postOrder, inRootPos + 1, iLast, pFirst + leftSize, pLast - 1);
    return root;
  }

  private int find(int[] A, int start, int end, int target) {
    for (int i = start; i < end; i++)
      if (A[i] == target) return i;
    return -1;
  }

}
