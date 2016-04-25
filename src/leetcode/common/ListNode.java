/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode.common;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class ListNode {

  public static ListNode generateNodes(Integer... array) {
    ListNode head = new ListNode(array[0]), march = head;
    for (int i = 1; i < array.length; i++) {
      march.next = new ListNode(array[i]);
      march = march.next;
    }
    return head;
  }

  public int val;

  public ListNode next = null;

  public ListNode(int x) {
    val = x;
  }

  @Override
  public String toString() {
    return toString(new HashSet<>());
  }

  private String toString(Set<ListNode> fathers) {
    ListNode march = this;
    ToStringHelper helper = MoreObjects.toStringHelper(ListNode.class);
    for (; march != null; march = march.next)
      if (fathers.contains(march)) {
        helper.add("circle val", march.val);
        break;
      } else {
        fathers.add(march);
        helper.add("val", march.val);
      }
    return helper.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (Objects.isNull(o) || !(o instanceof ListNode)) return false;
    ListNode another = (ListNode) o;
    if (!Objects.equals(val, another.val)) return false;
    return Objects.equals(next, another.next);
  }

}
