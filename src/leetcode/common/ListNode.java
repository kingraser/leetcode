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
        for (; march != null && !thereIsCircle(march, fathers); fathers.add(march), helper.add("val",
                march.val), march = march.next);
        return helper.toString();
    }

    private boolean thereIsCircle(ListNode node, List<ListNode> fathers) {
        for (ListNode father : fathers)
            if (node == father) return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (!(o instanceof ListNode)) return false;
        ListNode another = (ListNode) o;
        if (val != another.val) return false;
        return next == null ? another.next == null : next.equals(another);
    }

}
