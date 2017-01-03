/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Test;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class PalindromeLinkedList {

  //Given a singly linked list, determine if it is a palindrome.

  public boolean isPalindrome(ListNode head) {
    if (head == null) return true;
    ListNode p1 = head, p2 = head, p3 = p1.next, pre = p1;
    for (; p2.next != null && p2.next.next != null;) {//find mid pointer, and reverse head half part
      p2 = p2.next.next;
      pre = p1;
      p1 = p3;
      p3 = p3.next;
      p1.next = pre;
    }
    if (p2.next == null) p1 = p1.next;//odd move another step.In even case，do nothing
    for (; p3 != null; p1 = p1.next, p3 = p3.next)
      if (p1.val != p3.val) return false;
    return true;
  }

  @Test
  public void test() {
    assertTrue(isPalindrome(list(1, 2, 3, 2, 1)));
  }

}
