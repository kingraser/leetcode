/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class MergekSortedLists {

    /*优先队列*/

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode head = new ListNode(0), march = head, temp;
        PriorityQueue<ListNode> set = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {

            public int compare(ListNode o1, ListNode o2) {
                return o1.val == o2.val ? 0 : o1.val < o2.val ? -1 : 1;
            }
        });
        for (int i = 0; i < lists.length; i++)
            if (lists[i] != null) set.add(lists[i]);
        while (!set.isEmpty()) {
            temp = set.poll();
            if (temp.next != null) set.add(temp.next);
            march.next = temp;
            march = march.next;
        }
        return head.next;
    }

}
