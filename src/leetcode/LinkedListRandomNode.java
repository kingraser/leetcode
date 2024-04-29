package leetcode;

import static leetcode.common.ListNode.list;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import leetcode.common.ListNode;

public class LinkedListRandomNode {

  /*
  Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
  
  Follow up:
  What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?
  
  Example:
  
  // Init a singly linked list [1,2,3].
  ListNode head = new ListNode(1);
  head.next = new ListNode(2);
  head.next.next = new ListNode(3);
  Solution solution = new Solution(head);
  
  // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
  solution.getRandom(); 
  */

  @Test
  public void test() {
    RandomListNode node = new RandomListNode(list(1, 2, 3));
    Set<Integer> set = new HashSet<>(Set.of(1, 2, 3));
    int count = 0;
    for (; !set.isEmpty(); count++)
      set.remove(node.getRandom());
    assertTrue(count < 20);
    System.out.println(count);
  }

  static class RandomListNode {
    ListNode root;
    Random random = new Random();

    public RandomListNode(ListNode root) {
      this.root = root;
    }

    @SuppressWarnings("DataFlowIssue")
    public int getRandom() {
      ListNode result = null, current = root;
      for (int n = 1; current != null; current = current.next)
        if (random.nextInt(n++) == 0) result = current;
      return result.val;
    }
  }
}
