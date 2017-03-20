package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

  private static void traverse(TreeNode node, int level, List<List<Integer>> result) {
    if (Objects.isNull(node)) return;
    if (level == result.size()) result.add(new ArrayList<>());
    result.get(level).add(node.val);
    traverse(node.left, level + 1, result);
    traverse(node.right, level + 1, result);
  }

  @Test
  public void test() {
    assertEquals(Arrays.asList(Arrays.asList(3), Arrays.asList(9, 20), Arrays.asList(15, 7)),
        levelOrder(tree("3,9,n,n,20,15,n,n,7,n,n")));
  }

}
