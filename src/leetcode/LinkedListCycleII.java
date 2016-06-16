/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class LinkedListCycleII {

    //Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

    /*
            当fast与slow相遇时,slow肯定没有遍历完链表,而fast已经在环内循环了n圈 (1 ≤ n)。
            假设slow走了s步,则fast走了2s步(fast步数还等于 s加上在环上多转的 n圈),
            设环长为r,则: 2s = s + nr 即 s = nr
            设整个链表长 L,环入口点与相遇点距离为 a,起点到环入口点的距离为 x,则
            x + a = nr = (n–1)r + r = (n − 1)r + L − x
            x = (n − 1)r + (L–x–a)
            L–x–a为相遇点到环入口点的距离,由此可知,
            从链表头到环入口点等于n − 1圈内环+相遇点到环入口点,
            于是我们可以从 head开始另设一个指针 slow2,两个慢指针每次前进一步,它俩一定会在环入口点相遇。
    */

    public ListNode detectCycle(ListNode head) {
        for (ListNode slow = head, fast = head, slow2 = head; fast != null && fast.next != null;)
            if ((slow = slow.next) == (fast = fast.next.next)) {
                for (; slow2 != slow; slow2 = slow2.next, slow = slow.next);
                return slow2;
            }
        return null;
    }

}
