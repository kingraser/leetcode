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
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class AddTwoNumbers {

    /*
     给两个链表,反向数位表示整数,返回一个链表表示和
    (2 -> 4 -> 3)342 + (5 -> 6 -> 4)465
    Output: 7 -> 0 -> 8 807
    
    简单模拟
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder s1 = new StringBuilder(), s2 = new StringBuilder();
        for (; l1 != null; l1 = l1.next)
            s1.append(l1.val);
        for (; l2 != null; l2 = l2.next)
            s2.append(l2.val);
        java.math.BigInteger b1 = new java.math.BigInteger(s1.reverse().toString()),
                b2 = new java.math.BigInteger(s2.reverse().toString());
        s1.delete(0, s1.length()).append(b1.add(b2).toString()).reverse();
        ListNode head = new ListNode(0), marcher = head;
        for (int i = 0; i < s1.length(); i++) {
            ListNode temp = new ListNode(Integer.parseInt(s1.substring(i, i + 1)));
            marcher.next = temp;
            marcher = temp;
        }
        return head.next;
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(7);
        l3.next = new ListNode(0);
        l3.next.next = new ListNode(8);

        Assert.assertEquals(l3, addTwoNumbers(l1, l2));
    }

}
