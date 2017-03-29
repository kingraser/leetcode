package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.ListNode;

public class OddEvenLinkedList {

  /*
  Given a singly linked list, group all odd nodes together followed by the even nodes. 
  Please note here we are talking about the node number and not the value in the nodes.    
  You should try to do it in place. 
  The program should run in O(1) space complexity and O(nodes) time complexity.    
  Example:
  Given 1->2->3->4->5->NULL,
  return 1->3->5->2->4->NULL.    
  Note:
  The relative order inside both the even and odd groups should remain as it was in the input.
  The first node is considered odd, the second node even and so on ... 
  */

  @Test
  public void test() {
    assertEquals(list(1, 3, 5, 2, 4), oddEvenList(list(1, 2, 3, 4, 5)));
  }

  public ListNode oddEvenList(ListNode head) {
    if (Objects.isNull(head) || Objects.isNull(head.next)) return head;
    ListNode odd = head, even = head.next, evenHead = even;
    while (Objects.nonNull(even) && Objects.nonNull(even.next)) {
      odd = odd.next = even.next;
      even = even.next = odd.next;
    }
    odd.next = evenHead;
    return head;
  }
}
