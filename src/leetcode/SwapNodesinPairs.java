package leetcode;

import static org.junit.Assert.assertEquals;
import static leetcode.common.ListNode.list;

import org.junit.Test;

import leetcode.common.ListNode;

public class SwapNodesinPairs {

  /*
  Given a linked list, swap every two adjacent nodes and return its head.
  
  For example,
  Given 1->2->3->4, you should return the list as 2->1->4->3.
  
  Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed. 
  */

  @Test
  public void test() {
    assertEquals(list(2, 1, 4, 3), swapPairs(list(1, 2, 3, 4)));
  }

  public ListNode swapPairs(ListNode head) {
    if ((head == null) || (head.next == null)) return head;
    ListNode next = head.next;
    head.next = swapPairs(next.next);
    next.next = head;
    return next;
  }

}
