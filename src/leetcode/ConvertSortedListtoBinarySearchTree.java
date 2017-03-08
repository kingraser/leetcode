package leetcode;

import static leetcode.common.ListNode.list;
import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;
import leetcode.common.TreeNode;

public class ConvertSortedListtoBinarySearchTree {

  //Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST. 
  public TreeNode sortedListToBST(ListNode head) {
    return head == null ? null : toBST(head, null);
  }

  public TreeNode toBST(ListNode head, ListNode tail) {
    if (head == tail) return null;
    ListNode slow = head, fast = head;
    while (fast != tail && fast.next != tail) {
      fast = fast.next.next;
      slow = slow.next;
    }
    return new TreeNode(slow.val, toBST(head, slow), toBST(slow.next, tail));
  }

  @Test
  public void test() {
    TreeNode expected = tree("2,1,n,n,3,n,n");
    assertEquals(expected, sortedListToBST(list(1, 2, 3)));
  }

}
