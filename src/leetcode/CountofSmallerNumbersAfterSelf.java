/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月8日;
//-------------------------------------------------------
public class CountofSmallerNumbersAfterSelf {
  /*
  You are given an integer array nums and you have to return a new counts array. 
  The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
  Example:
  
  Given nums = [5, 2, 6, 1] 
  To the right of 5 there are 2 smaller elements (2 and 1).
  To the right of 2 there is only 1 smaller element (1).
  To the right of 6 there is 1 smaller element (1).
  To the right of 1 there is 0 smaller element.
  Return the array [2, 1, 1, 0].  
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(2, 1, 1, 0), countSmaller(new int[] { 5, 2, 6, 1 }));
  }

  public List<Integer> countSmaller(int[] nums) {
    Node root = null;
    int[] result = new int[nums.length];
    for (int i = nums.length - 1; i >= 0; i--)
      root = insert(root, nums[i], result, i, 0);
    return Arrays.stream(result).boxed().collect(Collectors.toList());
  }

  public Node insert(Node root, int val, int[] ans, int index, int preSum) {
    if (root == null) {
      root = new Node(val, 0);
      ans[index] = preSum;
    } else if (root.val > val) {
      root.small++;
      root.left = insert(root.left, val, ans, index, preSum);
    } else root.right = insert(root.right, val, ans, index, root.small + preSum + (root.val < val ? 1 : 0));
    return root;
  }

  class Node {
    int val, small;
    Node left, right;

    public Node(int val, int small) {
      this.val = val;
      this.small = small;
    }
  }
}
