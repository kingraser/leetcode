/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode.common;

import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.collect.Lists;

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

    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString() {
        return toString(Lists.newArrayList());
    };

    private String toString(List<ListNode> fathers) {
        ListNode march = this;
        ToStringHelper helper = MoreObjects.toStringHelper(ListNode.class);
        for (; march != null; march = march.next)
            if (thereIsCircle(march, fathers)) {
                helper.add("circle val", march.val);
                break;
            } else {
                fathers.add(march);
                helper.add("val", march.val);
            }
        return helper.toString();
    }

    private boolean thereIsCircle(ListNode node, List<ListNode> fathers) {
        for (ListNode father : fathers)
            if (node == father) return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o || !(o instanceof ListNode)) return false;
        ListNode another = (ListNode) o;
        if (val != another.val) return false;
        return next == null ? another.next == null : next.equals(another.next);
    }

}
