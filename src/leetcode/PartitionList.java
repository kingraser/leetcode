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
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class PartitionList {
  /*
  Given a linked list and a value x, 
  partition it such that all nodes less than x come before nodes greater than or equal to x.
  
  You should preserve the original relative order of the nodes in each of the two partitions.
  
  For example,
  Given 1->4->3->2->5->2 and x = 3,
  return 1->2->2->4->3->5.   
  */

  public ListNode partition(ListNode head, int x) {
    ListNode left = new ListNode(-1), right = new ListNode(-1), l = left, r = right;
    for (ListNode cur = head; cur != null; cur = cur.next)
      if (cur.val < x) {
        l.next = cur;
        l = l.next;
      } else {
        r.next = cur;
        r = r.next;
      }
    l.next = right.next;
    r.next = null;
    return left.next;
  }

  @Test
  public void test() {
    assertEquals(list(1, 2, 2, 4, 3, 5), partition(list(1, 4, 3, 2, 5, 2), 3));
  }
}
