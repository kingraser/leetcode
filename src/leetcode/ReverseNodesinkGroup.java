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
public class ReverseNodesinkGroup {

    /*
    Given this linked list: 1->2->3->4->5
    For k = 2, you should return: 2->1->4->3->5
    For k = 3, you should return: 3->2->1->4->5          
    */

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode march = head, nextMarch;
        int k1 = k;
        while (march != null && k1-- > 1)
            march = march.next;
        if (k1 > 1 || march == null) return head;
        nextMarch = march.next;
        reverseList(head, march);
        head.next = reverseKGroup(nextMarch, k);
        return march;
    }

    public ListNode reverseList(ListNode head, ListNode end) {
        if (head == null) return head;
        ListNode prev, curr = head, next = curr.next;
        head.next = null;
        while (curr != end) {
            prev = curr;
            curr = next;
            next = curr.next;
            curr.next = prev;
        }
        return curr;
    }

    @Test
    public void test() {
        Assert.assertEquals(ListNode.generateNodes(2, 1, 4, 3, 5),
                reverseKGroup(ListNode.generateNodes(1, 2, 3, 4, 5), 2));
        Assert.assertEquals(ListNode.generateNodes(3, 2, 1, 4, 5),
                reverseKGroup(ListNode.generateNodes(1, 2, 3, 4, 5), 3));
    }
}
