/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class IntersectionofTwoLinkedLists {
    /*
    Write a program to find the node at which the intersection of two singly linked lists begins.
    For example, the following two linked lists:
    
    A:     a1 → a2
                                                       ↘
                    c1 → c2 → c3
                                                       ↗            
    B:b1 → b2 → b3
    
    begin to intersect at node c1.    
    Notes:    
        If the two linked lists have no intersection at all, return null.
        The linked lists must retain their original structure after the function returns.
        You may assume there are no cycles anywhere in the entire linked structure.
        Your code should preferably run in O(n) time and use only O(1) memory.
     */

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        for (; p1 != p2; p1 = p1 == null ? headB : p1.next, p2 = p2 == null ? headA : p2.next);
        return p1;
    }

}
