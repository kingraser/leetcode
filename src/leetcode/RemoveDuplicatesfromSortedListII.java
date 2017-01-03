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
// <p>@author wit Initial Created at 2015年10月21日<p>
//-------------------------------------------------------
public class RemoveDuplicatesfromSortedListII {
  /*
  Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
  
  For example,
  Given 1->2->3->3->4->4->5, return 1->2->5.
  Given 1->1->1->2->3, return 2->3. 
  */

  @Test
  public void test() {
    assertEquals(list(1), deleteDuplicatesII(list(1, 2, 2)));
    assertEquals(list(1, 2, 5), deleteDuplicatesII(list(1, 2, 3, 3, 4, 4, 5)));
    assertEquals(list(2, 3), deleteDuplicatesII(list(1, 1, 1, 2, 3)));
    assertEquals(null, deleteDuplicatesII(list(1, 1)));
  }

  //递归
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null) return null;
    boolean hasDup = false, hasNext = false;
    for (; head.next != null; head = head.next)
      if (head.val == head.next.val) hasDup = true;
      else if (hasDup) hasDup = false;
      else {
        hasNext = true;
        break;
      }
    if (hasDup && !hasNext) return null;
    head.next = deleteDuplicates(head.next);
    return head;
  }

  //迭代
  public ListNode deleteDuplicatesII(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode HEAD = new ListNode(-1), insert = HEAD;
    boolean hasDup = false;
    for (; head.next != null; head = head.next)
      if (head.val == head.next.val) hasDup = true;
      else if (hasDup) hasDup = false;
      else {
        insert.next = head;
        insert = insert.next;
      }
    insert.next = hasDup ? null : head;
    return HEAD.next;
  }
}
