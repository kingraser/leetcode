package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;

public class PlusOneLinkedList {

  /*
  Given a non-negative number represented as a singly linked list of digits, plus one to the number.
  The digits are stored such that the most significant digit is at the head of the list.
  
  Example:
  Input:1->2->3
  Output:1->2->4
  */

  @Test
  public void test() {
    assertEquals(list(1, 2, 4), plusOne(list(1, 2, 3)));
  }

  public ListNode plusOne(ListNode head) {
    ListNode root = new ListNode(0, head), lastNot9 = root, node;
    for (node = head; node != null; lastNot9 = node.val != 9 ? node : lastNot9, node = node.next);
    for (lastNot9.val++, node = lastNot9.next; node != null; node.val = 0, node = node.next);
    return root.val == 1 ? root : head;
  }
}
