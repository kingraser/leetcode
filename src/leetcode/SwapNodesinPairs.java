package leetcode;

import org.junit.Assert;
import org.junit.Test;

import leetcode.common.ListNode;

/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class SwapNodesinPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode result = head.next;
        for (ListNode first = head, second = first.next, temp = first; first != null
                && second != null; temp.next = second, first.next = second.next, second.next = first, temp = first, first = first.next, second = first != null
                        ? first.next : second);
        return result;
    }

    public ListNode swapPairsII(ListNode head) {
        if ((head == null) || (head.next == null)) return head;
        ListNode n = head.next;
        head.next = swapPairsII(head.next.next);
        n.next = head;
        return n;
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(1), l2 = new ListNode(2);
        ListNode L1 = new ListNode(1), L2 = new ListNode(2);

        l1.next = l2;
        L2.next = L1;

        Assert.assertEquals(L2, swapPairsII(l1));
    }

}
