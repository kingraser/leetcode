/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;

import org.junit.Test;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class MergekSortedLists {

  @Test
  public void test() {
    assertEquals(list(1, 2, 3, 4, 5, 6, 7, 8, 9),
        mergeKLists(new ListNode[] { list(1, 3, 5), list(2, 4, 6), list(7, 8, 9) }));
  }

  public ListNode mergeKLists(ListNode[] lists) {
    if (Objects.isNull(lists) || lists.length == 0) return null;
    ListNode head = new ListNode(0), march = head;
    PriorityQueue<ListNode> set = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
    Arrays.stream(lists).filter(Objects::nonNull).forEach(n -> set.add(n));
    while ((march.next = set.poll()) != null)
      if ((march = march.next).next != null) set.add(march.next);
    return head.next;
  }

}
