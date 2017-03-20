package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
    List<List<Integer>> result = new ArrayList<>();
    zigzagLevelOrder(root, 0, result);
    return result;
  }

  private void zigzagLevelOrder(TreeNode node, int level, List<List<Integer>> result) {
    if (Objects.isNull(node)) return;
    if (level == result.size()) result.add(new LinkedList<>());
    result.get(level).add((level & 1) == 1 ? 0 : result.get(level).size(), node.val);
    zigzagLevelOrder(node.left, level + 1, result);
    zigzagLevelOrder(node.right, level + 1, result);
  }

  @Test
  public void test() {
    assertEquals(Arrays.asList(Arrays.asList(3), Arrays.asList(20, 9), Arrays.asList(15, 7)),
        zigzagLevelOrder(tree("3,9,n,n,20,15,n,n,7,n,n")));
  }

}
