package leetcode;

import static leetcode.BinaryTreeLevelOrderTraversal.levelOrder;
import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BinaryTreeZigzagLevelOrderTraversal {

  /*
  Given a binary tree, return the zigzag level order traversal of its nodes' values. 
  (ie, from left to right, then right to left for the next level and alternate between).
  
  For example:Given binary tree {3,9,20,#,#,15,7},
  
       3
      / \
     9  20
       /  \
      15   7
  
  return its zigzag level order traversal as:    
  [
      [3],
      [20,9],
      [15,7]
  ]
  */

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> result = levelOrder(root);
    for (int i = 1; i < result.size(); i += 2) // odd reverse
      Collections.reverse(result.get(i));
    return result;
  }

  @Test
  public void test() {
    assertEquals(Arrays.asList(Arrays.asList(3), Arrays.asList(20, 9), Arrays.asList(15, 7)),
        zigzagLevelOrder(tree("3,9,n,n,20,15,n,n,7,n,n")));
  }

}
