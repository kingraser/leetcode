package leetcode;

import static leetcode.common.ListNode.list;
import static leetcode.ReverseLinkedList.reverseList;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;

public class AddTwoNumbersII {

  /*
  You are given two linked lists representing two non-negative numbers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
  
  You may assume the two numbers do not contain any leading zero, except the number 0 itself.
  
  Follow up:
  What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
  
  Example:
  
  Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
  Output: 7 -> 8 -> 0 -> 7
  */

  @Test
  public void test() {
    assertEquals(list(7, 8, 0, 7), addTwoNumbers(list(7, 2, 4, 3), list(5, 6, 4)));
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    return reverseList(AddTwoNumbers.addTwoNumbers(reverseList(l1), reverseList(l2)));
  }
}