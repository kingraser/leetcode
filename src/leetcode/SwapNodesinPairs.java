/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;
import static leetcode.common.ListNode.list;

import org.junit.Test;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class SwapNodesinPairs {

  /*
  Given a linked list, swap every two adjacent nodes and return its head.
  
  For example,
  Given 1->2->3->4, you should return the list as 2->1->4->3.
  
  Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed. 
  */

  public ListNode swapPairs(ListNode head) {
    if ((head == null) || (head.next == null)) return head;
    ListNode next = head.next;
    head.next = swapPairs(next.next);
    next.next = head;
    return next;
  }

  @Test
  public void test() {
    assertEquals(list(1, 2), swapPairs(list(2, 1)));
  }

}
