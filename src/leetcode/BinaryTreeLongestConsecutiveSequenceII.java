package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BinaryTreeLongestConsecutiveSequenceII {

  /*
  Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.  
  Especially, this path can be either increasing or decreasing. 
  For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. 
  On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
  
  Example 1:  
  Input:
        1
       / \
      2   3
  Output: 2
  Explanation: The longest consecutive path is [1, 2] or [2, 1].
  
  Example 2:  
  Input:
        2
       / \
      1   3
  Output: 3
  Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].  
  */

  @Test
  public void test() {
    assertEquals(2, longestConsecutive(tree("1,2,n,n,3,n,n")));
    assertEquals(3, longestConsecutive(tree("2,1,n,n,3,n,n")));
  }

  int max;

  public int longestConsecutive(TreeNode root) {
    max = 0;
    if (Objects.nonNull(root)) longestPath(root);
    return max;
  }

  public int[] longestPath(TreeNode root) {
    int increase = 1, decrease = 1;
    if (Objects.nonNull(root.left)) {
      int[] left = longestPath(root.left);
      if (root.val == root.left.val + 1) decrease = left[1] + 1;
      else if (root.val == root.left.val - 1) increase = left[0] + 1;
    }
    if (Objects.nonNull(root.right)) {
      int[] right = longestPath(root.right);
      if (root.val == root.right.val + 1) decrease = Math.max(decrease, right[1] + 1);
      else if (root.val == root.right.val - 1) increase = Math.max(increase, right[0] + 1);
    }
    max = Math.max(max, decrease + increase - 1);
    return new int[] { increase, decrease };
  }

}
