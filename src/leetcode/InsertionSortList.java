package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;

public class InsertionSortList {

  @Test
  public void test() {
    assertEquals(list(1, 2, 3, 4, 5), insertionSortList(list(5, 2, 3, 1, 4)));
  }

  //Sort a linked list using insertion sort.
  public ListNode insertionSortList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode root = new ListNode(0);
    for (ListNode current = head, previous = root, next; current != null;) {
      for (next = current.next; previous.next != null && previous.next.val < current.val; previous = previous.next);
      current.next = previous.next;
      previous.next = current;
      previous = root;
      current = next;
    }
    return root.next;
  }

}
