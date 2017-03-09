package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.RandomListNode;

public class CopyListwithRandomPointer {

  /*
  A linked list is given such that each node contains an additional random pointer 
  which could point to any node in the list or null.
  
  Return a deep copy of the list. 
  */

  @Test
  public void test() {
    RandomListNode zero = new RandomListNode(0), one = new RandomListNode(1), two = new RandomListNode(2);
    zero.next = one;
    one.next = two;
    zero.random = two;
    one.random = zero;
    two.random = one;

    assertEquals(zero, copyRandomList(zero));
  }

  public RandomListNode copyRandomList(RandomListNode head) {
    return head.clone();
  }

}
