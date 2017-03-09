package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class CountCompleteTreeNodes {

  /*
  Given a complete binary tree, count the number of nodes.
  
  Definition of a complete binary tree from Wikipedia:
  In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. 
  It can have between 1 and 2h nodes inclusive at the last level h.
  */

  public int countNodes(TreeNode root) {
    return countNodes(root, -1, -1);
  }

  public int countNodes(TreeNode root, int left, int right) {
    if (Objects.isNull(root)) return 0;
    if (left < 0) left = getDepth(root.left, true);
    if (right < 0) right = getDepth(root.right, false);
    if (left == right) return (2 << left) - 1;
    return 1 + countNodes(root.left, --left, -1) + countNodes(root.right, -1, --right);
  }

  private int getDepth(TreeNode node, boolean isLeft) {
    return Objects.isNull(node) ? 0 : 1 + getDepth(isLeft ? node.left : node.right, isLeft);
  }

  @Test
  public void test() {
    assertEquals(5, countNodes(tree("3,9,n,n,20,15,n,n,7,n,n")));
  }
}
