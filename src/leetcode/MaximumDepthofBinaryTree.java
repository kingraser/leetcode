package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class MaximumDepthofBinaryTree {

  @Test
  public void test() {
    assertEquals(4, maxDepth(tree("1,2,n,n,3,4,5,n,n,n,6,n,n")));
  }

  public int maxDepth(TreeNode root) {
    return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
  }
}
