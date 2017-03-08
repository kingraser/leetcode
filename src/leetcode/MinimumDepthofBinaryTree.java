package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class MinimumDepthofBinaryTree {

  /*
  Given a binary tree, find its minimum depth.    
  The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
  */

  @Test
  public void test() {
    assertEquals(2, minDepth(tree("1,2,n,n,3,4,n,n,n")));
  }

  public int minDepth(TreeNode root) {
    if (null == root) return 0;
    if (root.left == null) return 1 + minDepth(root.right);
    if (root.right == null) return 1 + minDepth(root.left);
    return 1 + Math.min(minDepth(root.left), minDepth(root.right));
  }
}
