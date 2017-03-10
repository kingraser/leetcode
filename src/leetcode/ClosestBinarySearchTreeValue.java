package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class ClosestBinarySearchTreeValue {

  /*
  Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target. 
  Note:
    Given target value is a floating point.
    You are guaranteed to have only one unique value in the BST that is closest to the target.
  */

  @Test
  public void test() {
    assertEquals(3, closestValue(tree("1,2,n,n,3,n,n"), 4));
  }

  public int closestValue(TreeNode root, double target) {
    int result = root.val;
    for (; root != null; root = root.val > target ? root.left : root.right)
      if (Math.abs(target - root.val) < Math.abs(target - result)) result = root.val;
    return result;
  }

}
