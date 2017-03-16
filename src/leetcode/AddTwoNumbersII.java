package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;

public class AddTwoNumbersII {

  /*
  You are given two linked lists representing two non-negative numbers. 
  The most significant digit comes first and each of their nodes contain a single digit. 
  Add the two numbers and return it as a linked list.  
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
    assertEquals(list(1, 0, 9, 9, 8), addTwoNumbers(list(9, 9, 9, 9), list(9, 9, 9)));
  }

  public ListNode addTwoNumbers(ListNode node1, ListNode node2) {
    int len1 = node1.size(), len2 = node2.size(), difference = Math.abs(len1 - len2), val;
    if (len1 < len2) { // make sure list1 is not shorter than list2 
      ListNode temp = node1;
      node1 = node2;
      node2 = temp;
    }
    ListNode root = new ListNode(0), current = root, lastNot9 = current;
    for (; difference > 0; node1 = node1.next, difference--)
      if ((current = current.next = new ListNode(node1.val)).val != 9) lastNot9 = current;
    for (; node1 != null; node1 = node1.next, node2 = node2.next) {
      if ((val = node1.val + node2.val) > 9)
        for (lastNot9.val++; lastNot9.next != null; lastNot9.next.val = 0, lastNot9 = lastNot9.next);
      if ((current = current.next = new ListNode(val % 10)).val != 9) lastNot9 = current;
    }
    return (root.val == 1) ? root : root.next;
  }

}
