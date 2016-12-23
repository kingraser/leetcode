/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import leetcode.common.ListNode;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年8月23日;
//-------------------------------------------------------
public class LinkedListRandomNode {

  class Solution1 {

    ListNode root;

    Random random = new Random();

    public Solution1(ListNode root) {
      this.root = root;
    }

    public int getRandom() {
      ListNode result = null, current = root;
      for (int n = 1; current != null; current = current.next)
        if (random.nextInt(n++) == 0) result = current;
      return result.val;
    }
  }

  class Solution2 {

    ListNode root;

    int count = -1;

    Random random = new Random();

    public Solution2(ListNode root) {
      this.root = root;
    }

    public int getRandom() {
      if (count == -1) count = getLength(root);
      return get(root, random.nextInt(count)).val;
    }

    private ListNode get(ListNode node, int count) {
      for (; count-- > 0; node = node.next);
      return node;
    }

    public int getLength(ListNode node) {
      int count = 0;
      for (; node != null; count++, node = node.next);
      return count;
    }
  }

  class Solution3 {

    ListNode root;

    List<Integer> list;

    Random random = new Random();

    public Solution3(ListNode root) {
      this.root = root;
    }

    public int getRandom() {
      if (list == null) copy(root);
      return list.get(random.nextInt(list.size()));
    }

    public void copy(ListNode node) {
      list = new ArrayList<>();
      for (; node != null; list.add(node.val), node = node.next);
    }
  }
}
