package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;

public class RemoveDuplicatesfromSortedList {

  /*
  Given a sorted linked list, delete all duplicates such that each element appear only once.  
  For example,
  Given 1->1->2, return 1->2.
  Given 1->1->2->3->3, return 1->2->3
  */

  @Test
  public void test() {
    assertEquals(list(1, 2), deleteDuplicates(list(1, 1, 2)));
    assertEquals(list(1, 2, 3), deleteDuplicates(list(1, 1, 2, 3, 3)));
  }

  public ListNode deleteDuplicates(ListNode head) {
    for (ListNode current = head; current != null && current.next != null;)
      if (current.val == current.next.val) current.next = current.next.next;
      else current = current.next;
    return head;
  }

}
