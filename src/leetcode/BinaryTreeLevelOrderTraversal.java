package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BinaryTreeLevelOrderTraversal {

  /*
  Given a binary tree, return the level order traversal of its nodes' values.
  (ie, from left to right, level by level).
  
  For example:
  Given binary tree {3,9,20,#,#,15,7},
  
       3
      / \
     9  20
       /  \
      15   7
      
  return its level order traversal as:   
  [
      [3],
      [9,20],
      [15,7]
  ]   
  */

  public static List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    traverse(root, 0, result);
    return result;
  }

  private static void traverse(TreeNode root, int level, List<List<Integer>> result) {
    if (root == null) return;
    if (level == result.size()) result.add(new ArrayList<>());
    result.get(level).add(root.val);
    traverse(root.left, level + 1, result);
    traverse(root.right, level + 1, result);
  }

  @Test
  public void test() {
    TreeNode root = tree("3,9,n,n,20,15,n,n,7,n,n");
    List<List<Integer>> expected = Arrays.asList(Arrays.asList(3), Arrays.asList(9, 20), Arrays.asList(15, 7));
    assertEquals(expected, levelOrder(root));
  }

}
