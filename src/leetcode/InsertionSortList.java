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
public class InsertionSortList {

    //Sort a linked list using insertion sort.
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);//dummy.next = head
        for (ListNode cur = head; cur != null;) {
            ListNode pos = findInsertPos(dummy, cur.val);
            ListNode tmp = cur.next;
            cur.next = pos.next;
            pos.next = cur;
            cur = tmp;
        }
        return dummy.next;
    }

    ListNode findInsertPos(ListNode head, int x) {
        ListNode pre = null;
        for (ListNode cur = head; cur != null && cur.val <= x; pre = cur, cur = cur.next);
        return pre;
    }
}
