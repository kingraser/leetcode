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
        return addTwoNumbers(l1, l2, 0);
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
        if (l1 == null) return addTwoNumbers(l2, carry);
        if (l2 == null) return addTwoNumbers(l1, carry);
        l1.next = addTwoNumbers(l1.next, l2.next, add(l1, l2.val + carry));
        return l1;
    }

    private ListNode addTwoNumbers(ListNode l, int i) {
        if (l == null) return i == 0 ? null : new ListNode(i);
        if (add(l, i) == 1) l.next = addTwoNumbers(l.next, 1);
        return l;
    }

    private int add(ListNode l, int i) {
        l.val += i;
        if (l.val < 10) return 0;
        l.val -= 10;
        return 1;
    }

    @Test
    public void test() {
        Assert.assertEquals(ListNode.generateNodes(7, 0, 8),
                addTwoNumbers(ListNode.generateNodes(2, 4, 3), ListNode.generateNodes(5, 6, 4)));
    }

}
