package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;

public class PartitionList {

  /*
  Given a linked list and a value x, 
  partition it such that all nodes less than x come before nodes greater than or equal to x.
  
  You should preserve the original relative order of the nodes in each of the two partitions.
  
  For example,
  Given 1->4->3->2->5->2 and x = 3,
  return 1->2->2->4->3->5.   
  */

  public ListNode partition(ListNode head, int x) {
    ListNode leftHead = new ListNode(0), rightHead = new ListNode(0), left = leftHead, right = rightHead;
    for (ListNode current = head; current != null; current = current.next)
      if (current.val < x) left = left.next = current;
      else right = right.next = current;
    left.next = rightHead.next;
    right.next = null;
    return leftHead.next;
  }

  @Test
  public void test() {
    assertEquals(list(1, 2, 2, 4, 3, 5), partition(list(1, 4, 3, 2, 5, 2), 3));
  }
}
