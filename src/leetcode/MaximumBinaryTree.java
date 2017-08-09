package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class MaximumBinaryTree {

  /*
  Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
    The root is the maximum number in the array.
    The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
    The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
  Construct the maximum tree by the given array and output the root node of this tree.
  
  Example 1:  
  Input: [3,2,1,6,0,5]
  Output: return the tree root node representing the following tree:  
      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1  
  Note:  
    The size of the given array will be in the range [1,1000].  
  */

  @Test
  public void test() {
    assertEquals(tree("6,3,n,2,n,1,n,n,5,0,n,n,n"), constructMaximumBinaryTree(new int[] { 3, 2, 1, 6, 0, 5 }));
  }

  public TreeNode constructMaximumBinaryTree(int[] nums) {
    return constructMaximumBinaryTree(nums, 0, nums.length);
  }

  private TreeNode constructMaximumBinaryTree(int[] nums, int left, int right) {
    if (left == right) return null;
    int maxIdx = findMaxIdx(nums, left, right);
    return new TreeNode(nums[maxIdx], constructMaximumBinaryTree(nums, left, maxIdx),
        constructMaximumBinaryTree(nums, maxIdx + 1, right));
  }

  private int findMaxIdx(int[] nums, int left, int right) {
    int result = left;
    for (int i = left + 1; i < right; i++)
      if (nums[result] < nums[i]) result = i;
    return result;
  }

}
