package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BinaryTreeLongestConsecutiveSequence {
  /*
  Given a binary tree, find the length of the longest consecutive sequence path.
  The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
  For example,
  
   1
    \
     3
    / \
   2   4
        \
         5
  
  Longest consecutive sequence path is 3-4-5, so return 3.
  
     2
      \
       3
      / 
     2    
    / 
   1
  
  Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
  */

  @Test
  public void test() {
    assertEquals(3, longestConsecutive(tree("1,n,3,2,n,n,4,n,5,n,n")));
    assertEquals(2, longestConsecutive(tree("2,n,3,2,1,n,n,n,n")));
  }

  int res;

  int longestConsecutive(TreeNode root) {
    if (root == null) return 0;
    res = 0;
    dfs(root, root.val - 1, 0);
    return res;
  }

  void dfs(TreeNode root, int previousValue, int previousLength) {
    if (root == null) return;
    res = Math.max(res, previousLength = root.val == ++previousValue ? ++previousLength : 1);
    dfs(root.left, root.val, previousLength);
    dfs(root.right, root.val, previousLength);
  }
}
