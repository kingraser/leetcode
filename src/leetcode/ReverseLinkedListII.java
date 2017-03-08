package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;

public class ReverseLinkedListII {

  /*
  reverse a linked list from position m to n. Do it in-place and in one-pass.    
  For example:
  Given 1->2->3->4->5->NULL, m = 2 and n = 4,  
  return 1->4->3->2->5->NULL.
  
  Note:
  Given m, n satisfy the following condition:
  1 ≤ m ≤ n ≤ length of list.  
  */

  @Test
  public void test() {
    assertEquals(list(1, 4, 3, 2, 5), reverseBetween(list(1, 2, 3, 4, 5), 2, 4));
  }

  public ListNode reverseBetween(ListNode head, int m, int n) {
    ListNode root = new ListNode(0, head), pre = root, start, next;
    for (int i = m - 1; i-- > 0; pre = pre.next);
    next = (start = pre.next).next;
    for (int i = n - m; i-- > 0;) {
      start.next = next.next;
      next.next = pre.next;
      pre.next = next;
      next = start.next;
    }
    return root.next;
  }

}
