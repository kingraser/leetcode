/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode.common;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

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
        ListNode march = this;
        ToStringHelper helper = MoreObjects.toStringHelper(ListNode.class);
        for (; march != null; helper.add("val", march.val), march = march.next);
        return helper.toString();
    };

    @Override
    public boolean equals(Object o) {
        if (o instanceof ListNode) {
            ListNode another = (ListNode) o;
            if (val == another.val) {
                if (next == null) {
                    if (another.next == null) return true;
                } else if (another.next != null) return next.equals(another.next);
            }
        }
        return false;
    }

}
