package leetcode;

import static leetcode.common.ListNode.list;
import static leetcode.ReverseLinkedList.reverseList;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.ListNode;
import leetcode.common.Pair;

public class ReorderList {
  /*
  Given a singly linked list L : L0 → L1 → · · · → Ln−1 → Ln , 
  reorder it to: L0 → Ln → L1 → Ln−1 → L2 → Ln−2 → · · ·
  You must do this in-place without altering the nodes values.
  For example, Given {1,2,3,4}, reorder it to {1,4,2,3}.
  */

  @Test
  public void test() {
    ListNode input = list(1, 2, 3, 4);
    reorderList(input);
    assertEquals(list(1, 4, 2, 3), input);

    input = list(1, 2, 3, 4, 5);
    reorderList(input);
    assertEquals(list(1, 5, 2, 4, 3), input);
  }

  public void reorderList(ListNode head) {
    if (head == null || head.next == null) return;
    Pair<ListNode, ListNode> pair = head.breakFromMiddle();
    merge(pair.key, reverseList(pair.value));
  }

  private ListNode merge(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;
    ListNode next = l1.next;
    l1.next = l2;
    l2.next = merge(next, l2.next);
    return l1;
  }
}
