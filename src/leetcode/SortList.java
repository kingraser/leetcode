/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static leetcode.common.ListNode.list;
import static leetcode.MergeTwoSortedLists.mergeTwoLists;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;
import leetcode.common.Pair;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class SortList {
  //Sort a linked list in O(n log n) time using constant space complexity.

  @Test
  public void test() {
    assertEquals(list(1, 2, 3, 4, 5, 6), sortList(list(2, 3, 1, 6, 4, 5)));
  }

  public static ListNode sortList(ListNode head) {
    if (head == null || head.next == null) return head;
    Pair<ListNode, ListNode> pair = head.breakFromMiddle();
    return mergeTwoLists(sortList(pair.key), sortList(pair.value));
  }
}
