/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class SortList {
  //Sort a linked list in O(n log n) time using constant space complexity.

  //常数空间且 O(nlogn),单链表适合用归并排序,双向链表适合用快速排序。
  //本题可以复用Merge Two Sorted Lists的代码
  public ListNode sortList(ListNode head) {
    if (null == head || null == head.next) return head;
    ListNode slow = head, fast = head, pre = null;
    while (null != fast && null != fast.next) {
      fast = fast.next.next;
      pre = slow;
      slow = slow.next;
    }
    pre.next = null;//中间断开
    ListNode lhalf = sortList(head);
    ListNode rhalf = sortList(slow);
    return merge(lhalf, rhalf);
  }

  private ListNode merge(ListNode lhalf, ListNode rhalf) {
    ListNode dummyhead = new ListNode(0), p = dummyhead;
    for (; null != lhalf && null != rhalf; p = p.next)
      if (lhalf.val < rhalf.val) {
        p.next = lhalf;
        lhalf = lhalf.next;
      } else {
        p.next = rhalf;
        rhalf = rhalf.next;
      }
    p.next = null == lhalf ? rhalf : lhalf;
    return dummyhead.next;
  }
}
