package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class DiameterofBinaryTree {

  /*
  Given a binary tree, you need to compute the length of the diameter of the tree. 
  The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
  This path may or may not pass through the root.
  
  Example:
  Given a binary tree
  
          1
         / \
        2   3
       / \     
      4   5    
  
  Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
  
  Note: The length of path between two nodes is represented by the number of edges between them. 
  */

  @Test
  public void test() {
    assertEquals(3, diameterOfBinaryTree(tree("1,2,4,n,n,5,n,n,3,n,n")));
  }

  public int diameterOfBinaryTree(TreeNode root) {
    return dfs(root)[0];
  }

  // first for the result, second for the depth
  private int[] dfs(TreeNode node) {
    if (Objects.isNull(node)) return new int[2];
    int[] left = dfs(node.left), right = dfs(node.right);
    return new int[] { Math.max(left[1] + right[1], Math.max(left[0], right[0])), 1 + Math.max(left[1], right[1]) };
  }

}
