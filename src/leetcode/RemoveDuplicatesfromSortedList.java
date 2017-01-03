/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class RemoveDuplicatesfromSortedList {

  /*
  Given 1->1->2, return 1->2.
  Given 1->1->2->3->3, return 1->2->3
  */

  public ListNode deleteDuplicates(ListNode head) {
    for (ListNode march = head; march != null && march.next != null;)
      if (march.val == march.next.val) march.next = march.next.next;
      else march = march.next;
    return head;
  }

}
