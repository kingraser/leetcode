package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class MinimumAbsoluteDifferenceinBST {

  /*
  Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
  
  Example:  
  Input:
  
   1
    \
     3
    /
   2
  
  Output:
  1
  
  Explanation:
  The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
  Note: There are at least two nodes in this BST. 
  */

  @Test
  public void test() {
    assertEquals(1, getMinimumDifference(tree("1,n,3,2,n,n,n")));
  }

  int min = Integer.MAX_VALUE;
  Integer previous = null;

  public int getMinimumDifference(TreeNode root) {
    init();
    inOrder(root);
    return min;
  }

  private void inOrder(TreeNode root) {
    if (root == null) return;
    inOrder(root.left);
    if (previous != null) min = Math.min(min, root.val - previous);
    previous = root.val;
    inOrder(root.right);
  }

  private void init() {
    min = Integer.MAX_VALUE;
    previous = null;
  }
}
