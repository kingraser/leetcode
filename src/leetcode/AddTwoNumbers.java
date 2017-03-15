package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.ListNode;

public class AddTwoNumbers {

  /*
  You are given two linked lists representing two non-negative numbers. 
  The digits are stored in reverse order and each of their nodes contain a single digit. 
  Add the two numbers and return it as a linked list.
  
  Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
  Output: 7 -> 0 -> 8
  */

  @Test
  public void test() {
    assertEquals(list(7, 0, 8), addTwoNumbers(list(2, 4, 3), list(5, 6, 4)));
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    return addTwoNumbers(l1, l2, 0);
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
    ListNode node = Objects.isNull(l1) ? Objects.isNull(l2) ? carry == 0 ? null : new ListNode(carry) : l2 : l1;
    if (Objects.isNull(node)) return node;
    node.val = (carry += getValue(l1) + getValue(l2)) % 10;
    node.next = addTwoNumbers(getNext(l1), getNext(l2), carry / 10);
    return node;
  }

  public static int getValue(ListNode node) {
    return Objects.isNull(node) ? 0 : node.val;
  }

  public static ListNode getNext(ListNode node) {
    return Objects.isNull(node) ? null : node.next;
  }

}
