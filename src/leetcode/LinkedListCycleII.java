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
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class LinkedListCycleII {

  //Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

  /*
  When fast meets slow,slow does not finish the list,fast has already finish n loops in the circle(1 ≤ n).
  Let slow makes s step, then fast makes 2s step.
  Let circle length is r,then 2s = s + n*r thus s = n*r.
  Let the list length is L,the distance of circle entry to meet point is a,the distance of start point to circle entry is x.
  Thus x + a = n*r = (n–1)r + r = (n − 1)r + L − x
       x = (n − 1)r + (L–x–a)
  We know L–x–a is the distance of meet point to circle entry point.
  Thus, the distance of start to circle entry equals n − 1 circle length + the distance of meet point to circle entry,
  So we set another pointer slow2 start from head, the two slow pointer move one step at a time, they will meet at circle entry.
  */

  @Test
  public void test() {
    ListNode node = list(1, 2, 3, 4, 5);
    node.last().next = node.next(2);
    assertEquals(node.next(2), detectCycle(node));
  }

  public ListNode detectCycle(ListNode head) {
    for (ListNode slow = head, fast = head, slow2 = head; fast != null && fast.next != null;)
      if ((slow = slow.next) == (fast = fast.next.next)) {
        for (; slow2 != slow; slow2 = slow2.next, slow = slow.next);
        return slow2;
      }
    return null;
  }

}
