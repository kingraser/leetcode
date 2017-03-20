package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BinaryTreeLongestConsecutiveSequence {

  /*
  Given a binary tree, find the length of the longest consecutive sequence path.
  The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
  The longest consecutive path need to be from parent to child (cannot be the reverse).
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

  int longestConsecutive(TreeNode node) {
    if (Objects.isNull(node)) return 0;
    int[] longest = new int[1];
    dfs(node, node.val - 1, 0, longest);
    return longest[0];
  }

  void dfs(TreeNode node, int previousValue, int previousLength, int[] longest) {
    if (Objects.isNull(node)) return;
    longest[0] = Math.max(longest[0], previousLength = node.val == ++previousValue ? ++previousLength : 1);
    dfs(node.left, node.val, previousLength, longest);
    dfs(node.right, node.val, previousLength, longest);
  }
}
