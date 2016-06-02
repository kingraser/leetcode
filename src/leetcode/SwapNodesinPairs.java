/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class SwapNodesinPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode result = head.next;
        for (ListNode first = head, second; first != null && first.next != null;) {
            first.next = (second = first.next).next;
            second.next = first;
            first = first.next;
        }
        return result;
    }

    public ListNode swapPairsII(ListNode head) {
        if ((head == null) || (head.next == null)) return head;
        ListNode next = head.next;
        head.next = swapPairsII(next.next);
        next.next = head;
        return next;
    }

    @Test
    public void test() {
        Assert.assertEquals(ListNode.generateNodes(1, 2), swapPairs(ListNode.generateNodes(2, 1)));
        Assert.assertEquals(ListNode.generateNodes(1, 2), swapPairsII(ListNode.generateNodes(2, 1)));
    }

}
