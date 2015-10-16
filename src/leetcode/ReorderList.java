/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class ReorderList {
    /*
    Given a singly linked list L : L 0 → L 1 → · · · → L n−1 → L n , 
    reorder it to: L 0 → L n → L 1 → L n−1 → L 2 → L n−2 → · · ·
    You must do this in-place without altering the nodes’ values.
    For example, Given {1,2,3,4}, reorder it to {1,4,2,3}.
    */

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // cut at middle
        slow = reverse(slow);
        ListNode curr = head;
        while (curr.next != null) {//merge two lists
            ListNode tmp = curr.next;
            curr.next = slow;
            slow = slow.next;
            curr.next.next = tmp;
            curr = tmp;
        }
        curr.next = slow;
    }

    ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = head;
        for (ListNode curr = head.next, next = curr.next; curr != null; prev = curr, curr = next, next = next != null
                ? next.next : null)
            curr.next = prev;
        head.next = null;
        return prev;
    }
}
