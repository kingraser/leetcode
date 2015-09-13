/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class ReverseLinkedList {

    //迭代
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        ListNode prev, curr = head, next = head.next;
        head.next = null;
        while (curr != null && next != null) {
            prev = curr;
            curr = next;
            next = next.next;
            curr.next = prev;
        }
        return curr;
    }

    //递归
    public ListNode reverseListII(ListNode head) {
        if (head == null) return head;
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
