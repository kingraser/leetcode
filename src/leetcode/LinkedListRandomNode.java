/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.Random;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年8月23日;
//-------------------------------------------------------
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

  ListNode root;
  Random random = new Random();

  public LinkedListRandomNode(ListNode root) {
    this.root = root;
  }

  public int getRandom() {
    ListNode result = null, current = root;
    for (int n = 1; current != null; current = current.next)
      if (random.nextInt(n++) == 0) result = current;
    return result.val;
  }

}
