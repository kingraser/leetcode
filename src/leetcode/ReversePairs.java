package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReversePairs {

  /*
  Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
  
  You need to return the number of important reverse pairs in the given array.
  
  Example1:
  
  Input: [1,3,2,3,1]
  Output: 2
  
  Example2:
  
  Input: [2,4,3,5,1]
  Output: 3
  
  Note:
    The length of the given array will not exceed 50,000.
    All the numbers in the input array are in the range of 32-bit integer.
  */

  @Test
  public void test() {
    assertEquals(2, reversePairs(new int[] { 1, 3, 2, 3, 1 }));
    assertEquals(3, reversePairs(new int[] { 2, 4, 3, 5, 1 }));
    assertEquals(9, reversePairs(new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE,
        Integer.MIN_VALUE, Integer.MAX_VALUE }));
  }

  int sum;

  public int reversePairs(int[] nums) {
    sum = 0;
    Node root = null;
    for (int i = nums.length - 1; i >= 0; i--) {
      sum(root, nums[i] / 2d);
      root = add(root, nums[i]);
    }
    return sum;
  }

  private void sum(Node node, double half) {
    if (node == null) return;
    if (half == node.val) sum += node.leftCount;
    else if (half < node.val) sum(node.left, half);
    else {
      sum += node.leftCount + node.count;
      sum(node.right, half);
    }
  }

  private Node add(Node node, int val) {
    if (node == null) return new Node(val);
    if (val == node.val) node.count += 1;
    else if (val > node.val) node.right = add(node.right, val);
    else {
      node.leftCount++;
      node.left = add(node.left, val);
    }
    return node;
  }

  class Node {
    int val, leftCount = 0, count = 1;
    Node left, right;

    public Node(int v) {
      this.val = v;
    }
  }
}
