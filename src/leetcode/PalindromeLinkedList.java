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
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class PalindromeLinkedList {

    //Given a singly linked list, determine if it is a palindrome.

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode p1 = head, p2 = head, p3 = p1.next, pre = p1;
        for (; p2.next != null//find mid pointer, and reverse head half part
                && p2.next.next != null; p2 = p2.next.next, pre = p1, p1 = p3, p3 = p3.next, p1.next = pre);
        if (p2.next == null) p1 = p1.next;//odd move another step.In even case，do nothing
        for (; p3 != null; p1 = p1.next, p3 = p3.next)
            if (p1.val != p3.val) return false;
        return true;
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(1), l2 = new ListNode(2), l3 = new ListNode(3), l4 = new ListNode(2),
                l5 = new ListNode(1);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        Assert.assertTrue(isPalindrome(l1));
    }

}
