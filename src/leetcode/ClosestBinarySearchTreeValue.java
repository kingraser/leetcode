package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

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
    double rootDiff = Math.abs(target - root.val), left = Double.MAX_VALUE, right = Double.MAX_VALUE,
        leftDiff = Objects.isNull(root.left) ? left : Math.abs(target - (left = closestValue(root.left, target))),
        rightDiff = Objects.isNull(root.right) ? right : Math.abs(target - (right = closestValue(root.right, target)));
    return rootDiff < leftDiff && rootDiff < rightDiff ? root.val : leftDiff < rightDiff ? (int) left : (int) right;
  }
}
