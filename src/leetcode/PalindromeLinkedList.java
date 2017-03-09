package leetcode;

import static leetcode.common.ListNode.list;
import static leetcode.ReverseLinkedList.reverseList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.ListNode;

public class PalindromeLinkedList {

  //Given a singly linked list, determine if it is a palindrome.

  public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) return true;
    ListNode slow = head, fast = head, prev = null;
    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    if (Objects.nonNull(prev)) prev.next = null;
    return head.equals(reverseList(Objects.isNull(fast) ? slow : slow.next));
  }

  @Test
  public void test() {
    assertTrue(isPalindrome(list(1)));
    assertTrue(isPalindrome(null));
    assertTrue(isPalindrome(list(1, 2, 3, 2, 1)));
    assertFalse(isPalindrome(list(1, 2)));
  }

}
