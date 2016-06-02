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
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class ReverseLinkedList {

    @Test
    public void test() {
        Assert.assertEquals(ListNode.generateNodes(5, 4, 3, 2, 1), reverseList(ListNode.generateNodes(1, 2, 3, 4, 5)));
        Assert.assertEquals(ListNode.generateNodes(5, 4, 3, 2, 1),
                reverseListII(ListNode.generateNodes(1, 2, 3, 4, 5)));
    }

    //迭代
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev, curr = head, next = curr.next;
        while (curr != null && next != null) {
            prev = curr;
            curr = next;
            next = curr.next;
            curr.next = prev;
        }
        head.next = null;
        return curr;
    }

    //递归
    public ListNode reverseListII(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = head.next;
        head.next = null;
        return reverseList(head, next);
    }

    private ListNode reverseList(ListNode head, ListNode next) {
        if (head == null || next == null) return head;
        ListNode newHead = next, newNext = next.next;
        next.next = head;
        return reverseList(newHead, newNext);
    }
}
