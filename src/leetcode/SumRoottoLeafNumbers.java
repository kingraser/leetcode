package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class SumRoottoLeafNumbers {

  /*
  For example,
  
    1
   / \
  2   3
  
  The root-to-leaf path 1->2 represents the number 12.
  The root-to-leaf path 1->3 represents the number 13.
  
  Return the sum = 12 + 13 = 25. 
  */

  @Test
  public void test() {
    assertEquals(25, sumNumbers(tree("1,2,n,n,3,n,n")));
  }

  public int sumNumbers(TreeNode root) {
    return dfs(root, 0);
  }

  private int dfs(TreeNode root, int sum) {
    if (Objects.isNull(root)) return 0;
    if (Objects.isNull(root.left) && Objects.isNull(root.right)) return sum * 10 + root.val;
    return dfs(root.left, sum = sum * 10 + root.val) + dfs(root.right, sum);
  }

}
