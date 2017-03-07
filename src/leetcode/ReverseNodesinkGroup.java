package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;

public class ReverseNodesinkGroup {

  /*
  Given this linked list: 1->2->3->4->5
  For k = 2, you should return: 2->1->4->3->5
  For k = 3, you should return: 3->2->1->4->5          
  */

  @Test
  public void test() {
    assertEquals(list(2, 1, 4, 3, 5), reverseKGroup(list(1, 2, 3, 4, 5), 2));
    assertEquals(list(3, 2, 1, 4, 5), reverseKGroup(list(1, 2, 3, 4, 5), 3));
  }

  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode current = head;
    int count = 0;
    for (; current != null && count != k; current = current.next, count++);
    if (count != k) return head;
    for (current = reverseKGroup(current, k); count-- > 0;) {
      ListNode next = head.next;
      head.next = current;
      current = head;
      head = next;
    }
    return current;
  }

}
