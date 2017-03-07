package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;

public class RemoveNthNodeFromEndofList {

  /*
  Given a linked list, remove the nth node from the end of list and return its head.
  
  For example,  
   Given linked list: 1->2->3->4->5, and n = 2.  
   After removing the second node from the end, the linked list becomes 1->2->3->5.
  
  Note:
  Given n will always be valid.
  Try to do this in one pass. 
  */

  @Test
  public void test() {
    assertEquals(list(1, 2, 3, 5), removeNthFromEnd(list(1, 2, 3, 4, 5), 2));
  }

  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode root = new ListNode(0, head), fast, slow;
    for (fast = head; n-- > 0; fast = fast.next);
    for (slow = root; fast != null; fast = fast.next, slow = slow.next); // to the end
    slow.next = slow.next.next; // delete
    return root.next;
  }

}
