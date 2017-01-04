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
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class MergeTwoSortedLists {

  @Test
  public void test() {
    ListNode l1 = list(1, 3, 5), l2 = list(2, 4, 6);
    assertEquals(list(1, 2, 3, 4, 5, 6), mergeTwoLists(l1, l2));
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;
    if (l1.val < l2.val) {
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    }
    l2.next = mergeTwoLists(l1, l2.next);
    return l2;
  }
}
