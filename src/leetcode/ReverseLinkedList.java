package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;

public class ReverseLinkedList {

  @Test
  public void test() {
    assertEquals(list(5, 4, 3, 2, 1), reverseList(list(1, 2, 3, 4, 5)));
  }

  public static ListNode reverseList(ListNode head) {
    ListNode newHead = null;
    while (head != null) {
      ListNode next = head.next;
      head.next = newHead;
      newHead = head;
      head = next;
    }
    return newHead;
  }

}
