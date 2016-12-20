/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode.common;

import java.util.Objects;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class ListNode {
  public int val;
  public ListNode next = null;

  public ListNode(int x) {
    val = x;
  }

  @Override
  public boolean equals(Object o) {
    if (null == o || !(o instanceof ListNode)) return false;
    ListNode another = (ListNode) o;
    if (val != another.val) return false;
    return Objects.equals(next, another.next);
  }

  public static ListNode list(int... array) {
    if (Objects.isNull(array) || array.length == 0) return null;
    ListNode head = new ListNode(array[0]), march = head;
    for (int i = 1; i < array.length; march = march.next = new ListNode(array[i++]));
    return head;
  }
}
