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
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class ReverseLinkedList {

  @Test
  public void test() {
    assertEquals(list(5, 4, 3, 2, 1), reverseList(list(1, 2, 3, 4, 5)));
  }

  public static ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode next = head.next;
    head.next = null;
    return reverseList(head, next);
  }

  private static ListNode reverseList(ListNode head, ListNode next) {
    if (head == null || next == null) return head;
    ListNode newHead = next, newNext = next.next;
    next.next = head;
    return reverseList(newHead, newNext);
  }
}
