/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Objects;

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

    private ListNode addTwoNumbers(ListNode l1, ListNode l2, int c) {
        ListNode node = Objects.isNull(l1) ? Objects.isNull(l2) ? c == 0 ? null : new ListNode(c) : l2 : l1;
        if (Objects.isNull(node)) return null;
        int val = (Objects.isNull(l1) ? 0 : l1.val) + (Objects.isNull(l2) ? 0 : l2.val) + c;
        node.val = val % 10;
        node.next = addTwoNumbers(Objects.isNull(l1) ? null : l1.next, Objects.isNull(l2) ? null : l2.next, val / 10);
        return node;
    }

    @Test
    public void test() {
        Assert.assertEquals(ListNode.generateNodes(7, 0, 8),
                addTwoNumbers(ListNode.generateNodes(2, 4, 3), ListNode.generateNodes(5, 6, 4)));
    }

}
