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
public class RemoveLinkedListElements {

  /*
  Example
  Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
  Return: 1 --> 2 --> 3 --> 4 --> 5 
  */

  @Test
  public void test() {
    assertEquals(list(1, 2, 3, 4, 5), removeElements(list(1, 2, 6, 3, 4, 5), 6));
  }

  public ListNode removeElements(ListNode head, int val) {
    if (head == null) return null;
    if (head.val == val) return removeElements(head.next, val);
    head.next = removeElements(head.next, val);
    return head;
  }

}
