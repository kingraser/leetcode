package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class CountUnivalueSubtrees {

  /*
  Given a binary tree, count the number of uni-value subtrees.  
  A Uni-value subtree means all nodes of the subtree have the same value.
  
  For example:
  Given binary tree,
  
              5
             / \
            1   5
           / \   \
          5   5   5
  
  return 4.
  */

  @Test
  public void test() {
    assertEquals(4, countUnivalSubtrees(tree("5,1,5,n,n,5,n,n,5,n,5,n,n")));
  }

  int count = 0;

  /**
   * @param root root of a tree T
   * @param v the given value
   * @return Whether all nodes in the tree T have the given value v
   */
  boolean isUniValue(TreeNode root, int v) {
    if (root == null) return true;
    if (!isUniValue(root.left, root.val) | !isUniValue(root.right, root.val)) return false;
    count++;
    return root.val == v;
  }

  public int countUnivalSubtrees(TreeNode root) {
    isUniValue(root, Integer.MIN_VALUE);
    return count;
  }

}
