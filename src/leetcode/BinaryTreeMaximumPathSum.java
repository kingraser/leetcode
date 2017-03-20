package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BinaryTreeMaximumPathSum {

  /*
  Given a binary tree, find the maximum path sum.  
  The path may start and end at any node in the tree.
  
  For example:
  Given the below binary tree,
  
     1
    / \
   2   3
  
  Return 6. 
  */

  @Test
  public void test() {
    assertEquals(6, maxPathSum(tree("1,2,n,n,3,n,n")));
  }

  public int maxPathSum(TreeNode root) {
    int[] max = new int[] { Integer.MIN_VALUE };
    PathSum(root, max);
    return max[0];
  }

  public int PathSum(TreeNode root, int[] max) {
    if (Objects.isNull(root)) return 0;
    int left = PathSum(root.left, max), right = PathSum(root.right, max);
    max[0] = Math.max(max[0], root.val + Math.max(0, left) + Math.max(0, right));
    return Math.max(0, Math.max(right, left)) + root.val;
  }

}
