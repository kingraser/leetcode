/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class AddTwoNumbers {

  /*
  You are given two linked lists representing two non-negative numbers. 
  The digits are stored in reverse order and each of their nodes contain a single digit. 
  Add the two numbers and return it as a linked list.
  
  Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
  Output: 7 -> 0 -> 8
  */

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    return addTwoNumbers(l1, l2, 0);
  }

  private static ListNode addTwoNumbers(ListNode l1, ListNode l2, int c) {
    ListNode node = Objects.isNull(l1) ? Objects.isNull(l2) ? c == 0 ? null : new ListNode(c) : l2 : l1;
    if (Objects.isNull(node)) return null;
    int val = (Objects.isNull(l1) ? 0 : l1.val) + (Objects.isNull(l2) ? 0 : l2.val) + c;
    node.val = val % 10;
    node.next = addTwoNumbers(Objects.isNull(l1) ? null : l1.next, Objects.isNull(l2) ? null : l2.next, val / 10);
    return node;
  }

  @Test
  public void test() {
    assertEquals(list(7, 0, 8), addTwoNumbers(list(2, 4, 3), list(5, 6, 4)));
  }

}
