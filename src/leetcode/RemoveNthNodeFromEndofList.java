/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class RemoveNthNodeFromEndofList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode root = new ListNode(0), fast, slow;
        root.next = head;
        for (fast = root; --n > 0; fast = fast.next);
        for (slow = root; fast != null; fast = fast.next, slow = slow.next);//to the end
        slow.next = slow.next.next;//del
        return root.next;
    }

}
