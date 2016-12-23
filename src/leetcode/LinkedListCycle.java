/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class LinkedListCycle {

  //Given a linked list, determine if it has a cycle in it.

  // 2 pointers slow fast
  public boolean hasCycle(ListNode head) {
    for (ListNode slow = head, fast = head; fast != null && fast.next != null;)
      if ((slow = slow.next) == (fast = fast.next.next)) return true;
    return false;
  }

}
