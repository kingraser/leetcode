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
public class RemoveLinkedListElements {

    /*
    Example
    Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
    Return: 1 --> 2 --> 3 --> 4 --> 5 
    */

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        for (ListNode p = head; p.next != null;)
            if (p.next.val == val) p.next = p.next.next;
            else p = p.next;
        return head.val == val ? head.next : head;
    }

    public ListNode removeElementsII(ListNode head, int val) {
        if (head == null) return null;
        if (head.val == val) return removeElements(head.next, val);
        head.next = removeElements(head.next, val);
        return head;
    }

}
