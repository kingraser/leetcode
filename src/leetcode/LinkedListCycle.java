/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class LinkedListCycle {

  //Given a linked list, determine if it has a cycle in it.

  @Test
  public void test() {
    assertFalse(hasCycle(list(1, 2, 3)));
    ListNode node = list(1, 2, 3);
    node.last().next = node;
    assertTrue(hasCycle(node));
  }

  // 2 pointers slow fast
  public boolean hasCycle(ListNode head) {
    for (ListNode slow = head, fast = head; fast != null && fast.next != null;)
      if ((slow = slow.next) == (fast = fast.next.next)) return true;
    return false;
  }

}
