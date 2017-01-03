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
// <p>@author wit Initial Created at 2015年9月18日<p>
//-------------------------------------------------------
public class ReverseLinkedListII {

  /*
  reverse a linked list from position m to n. Do it in-place and in one-pass.    
  For example:
  Given 1->2->3->4->5->NULL, m = 2 and n = 4,
  
  return 1->4->3->2->5->NULL.
  
  Note:
  Given m, n satisfy the following condition:
  1 ≤ m ≤ n ≤ length of list.  
  */

  public ListNode reverseBetween(ListNode head, int m, int n) {
    ListNode before = null, start, end, after = null, ptr = head;
    for (int i = 1; i < m; before = ptr, ptr = ptr.next, i++);//find m
    start = end = ptr;
    for (int i = m; i <= n; i++) {//reverse
      after = ptr.next;
      ptr.next = end;
      end = ptr;
      ptr = after;
    }
    if (before == null) head = end;
    else before.next = end;
    start.next = after;
    return head;
  }

  @Test
  public void test() {
    ListNode expected = list(1, 4, 3, 2, 5), input = list(1, 2, 3, 4, 5);
    assertEquals(expected, reverseBetween(input, 2, 4));
  }

}
