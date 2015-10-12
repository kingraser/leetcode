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
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class RotateList {

    /*
    Given a list, rotate the list to the right by k places, where k is non-negative.
    
    For example:
    Given 1->2->3->4->5->NULL and k = 2,
    return 4->5->1->2->3->NULL.
    */

    /*
    先遍历一遍,得出链表长度len,注意 k可能大于len,因此令k%=len。
    将尾节点next指针指向首节点,形成一个环,接着往后跑len−k步,从这里断开,就是要求的结果了。
    */

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        int len = 1;
        ListNode p = head;
        for (; p.next != null; len++, p = p.next);// 求长度
        p.next = head; // 首尾相连
        for (int step = 0, length = (len - k % len) % len; step < length; p = p.next, step++); //接着往后跑
        head = p.next; // 新的首节点
        p.next = null; // 断开环
        return head;
    }

    @Test
    public void test() {
        Assert.assertEquals(ListNode.generateNodes(4, 5, 1, 2, 3),
                rotateRight(ListNode.generateNodes(1, 2, 3, 4, 5), 2));
    }

}
