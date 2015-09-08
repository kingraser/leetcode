/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.math.BigInteger;

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
        char[] result = new StringBuilder().append(getNum(l1).add(getNum(l2))).reverse().toString().toCharArray();
        ListNode head = new ListNode(0), marcher = head, temp;
        for (int i = 0; i < result.length; i++, marcher.next = temp, marcher = temp)
            temp = new ListNode(result[i] - '0');
        return head.next;
    }

    public BigInteger getNum(ListNode node) {
        StringBuilder sb = new StringBuilder();
        for (; node != null; node = node.next)
            sb.append(node.val);
        return new BigInteger(sb.reverse().toString());
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
