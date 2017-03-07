package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;

public class RotateList {

  /*
  Given a list, rotate the list to the right by k places, where k is non-negative.
  
  For example:
  Given 1->2->3->4->5->NULL and k = 2,
  return 4->5->1->2->3->NULL.
  */

  /*
  First get the length of the list, let we name it l, note k may be larger than l, so make k%=l
  Second let tail.next = head, thus make a circle, move l-k step from tail, break the circle and get the result
  */

  public ListNode rotateRight(ListNode head, int k) {
    if (head == null || k == 0) return head;
    int len = 1;
    ListNode p = head;
    for (; p.next != null; len++, p = p.next);// get length
    p.next = head; // make a circle
    for (int step = 0, length = len - k % len; step < length; p = p.next, step++); //move forward 
    head = p.next; // new head
    p.next = null; // break the circle
    return head;
  }

  @Test
  public void test() {
    assertEquals(list(4, 5, 1, 2, 3), rotateRight(list(1, 2, 3, 4, 5), 2));
  }

}
