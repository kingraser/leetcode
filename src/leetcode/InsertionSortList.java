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
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class InsertionSortList {

    @Test
    public void test() {
        Assert.assertEquals(ListNode.generateNodes(1, 2, 3, 4, 5),
                insertionSortList(ListNode.generateNodes(5, 3, 2, 1, 4)));
    }

    //Sort a linked list using insertion sort.
    public ListNode insertionSortList(ListNode head) {
        ListNode prev = new ListNode(Integer.MIN_VALUE);//prev.next = head
        for (ListNode cur = head; cur != null;) {
            ListNode pos = findInsertPos(prev, cur.val);
            ListNode tmp = cur.next;
            cur.next = pos.next;
            pos.next = cur;
            cur = tmp;
        }
        return prev.next;
    }

    ListNode findInsertPos(ListNode head, int x) {
        ListNode pre = null;
        for (ListNode cur = head; cur != null && cur.val <= x; pre = cur, cur = cur.next);
        return pre;
    }
}
