/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class InsertionSortList {

  @Test
  public void test() {
    assertEquals(list(1, 2, 3, 4, 5), insertionSortList(list(5, 2, 3, 1, 4)));
  }

  //Sort a linked list using insertion sort.
  public ListNode insertionSortList(ListNode head) {
    ListNode dummy = new ListNode(Integer.MIN_VALUE);//dummy.next = head
    for (ListNode cur = head; cur != null;) {
      ListNode pos = findInsertPos(dummy, cur.val);
      ListNode tmp = cur.next;
      cur.next = pos.next;
      pos.next = cur;
      cur = tmp;
    }
    return dummy.next;
  }

  ListNode findInsertPos(ListNode head, int x) {
    ListNode pre = null;
    for (ListNode cur = head; cur != null && cur.val <= x; pre = cur, cur = cur.next);
    return pre;
  }
}
