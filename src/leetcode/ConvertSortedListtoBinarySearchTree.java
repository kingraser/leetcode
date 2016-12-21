/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static leetcode.common.ListNode.list;
import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import leetcode.common.ListNode;
import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月15日<p>
//-------------------------------------------------------
public class ConvertSortedListtoBinarySearchTree {

  //Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST. 
  public TreeNode sortedListToBST(ListNode head) {
    List<Integer> list = new ArrayList<>();
    for (; null != head; list.add(head.val), head = head.next);
    return build(list, 0, list.size());
  }

  private TreeNode build(List<Integer> list, int left, int right) {
    if (left >= right) return null;
    int mid = (left + right) >> 1;
    return new TreeNode(list.get(mid), build(list, left, mid), build(list, mid + 1, right));
  }

  @Test
  public void test() {
    TreeNode expected = tree("2,1,n,n,3,n,n");
    assertEquals(expected, sortedListToBST(list(1, 2, 3)));
  }

}
