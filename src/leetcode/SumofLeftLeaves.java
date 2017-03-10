package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class SumofLeftLeaves {

  @Test
  public void test() {
    assertEquals(24, sumOfLeftLeaves(tree("3,9,n,n,20,15,n,n,7,n,n")));
  }

  public int sumOfLeftLeaves(TreeNode node) {
    return sumOfLeftLeaves(node, false);
  }

  private int sumOfLeftLeaves(TreeNode node, boolean isLeft) {
    if (Objects.isNull(node)) return 0;
    if (Objects.isNull(node.left) && Objects.isNull(node.right)) return isLeft ? node.val : 0;
    return sumOfLeftLeaves(node.left, true) + sumOfLeftLeaves(node.right, false);
  }
}
