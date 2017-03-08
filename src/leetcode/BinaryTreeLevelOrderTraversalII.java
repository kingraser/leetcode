package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BinaryTreeLevelOrderTraversalII {

  /*
  Given a binary tree, 
  return the bottom-up level order traversal of its nodes' values. 
  (ie, from left to right, level by level from leaf to root).
  
  For example:
  Given binary tree {3,9,20,#,#,15,7},
  
       3
      / \
     9  20
       /  \
      15   7
      
  return its level order traversal as:   
  [   
      [15,7],
      [9,20],
      [3]
  ]   
  */

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = BinaryTreeLevelOrderTraversal.levelOrder(root);
    Collections.reverse(result);
    return result;
  }

  @Test
  public void test() {
    List<List<Integer>> expected = Arrays.asList(Arrays.asList(15, 7), Arrays.asList(9, 20), Arrays.asList(3));
    assertEquals(expected, levelOrder(tree("3,9,n,n,20,15,n,n,7,n,n")));
  }

}
