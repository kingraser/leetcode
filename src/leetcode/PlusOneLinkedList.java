package leetcode;

import static leetcode.AddTwoNumbers.addTwoNumbers;
import static leetcode.common.ListNode.list;
import static leetcode.ReverseLinkedList.reverseList;
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
    return reverseList(addTwoNumbers(reverseList(head), null, 1));
  }
}
