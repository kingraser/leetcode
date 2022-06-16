package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import leetcode.common.ListNode;

public class RemoveDuplicatesfromSortedListII {

  /*
  Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
  
  For example,
  Given 1->2->3->3->4->4->5, return 1->2->5.
  Given 1->1->1->2->3, return 2->3. 
  */

  @Test
  public void test() {
    assertEquals(list(1, 2, 5), deleteDuplicates(list(1, 2, 3, 3, 4, 4, 5)));
    assertEquals(list(2, 3), deleteDuplicates(list(1, 1, 1, 2, 3)));

    assertEquals(list(1), deleteDuplicates(list(1, 2, 2)));
    assertNull(deleteDuplicates(list(1, 1)));
  }

  public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) return head;
    if (head.val == head.next.val) {
      for (; head.next != null && head.val == head.next.val; head = head.next);
      return deleteDuplicates(head.next);
    } else head.next = deleteDuplicates(head.next);
    return head;
  }
}
