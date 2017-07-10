package leetcode;

import static leetcode.BinaryTreeLevelOrderTraversal.levelOrder;
import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import leetcode.common.TreeNode;

public class AverageofLevelsinBinaryTree {

  /*
  Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
  
  Example 1:
  
  Input:
    3
   / \
  9  20
    /  \
   15   7
  Output: [3, 14.5, 11]
  Explanation:
  The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
  
  Note:  
    The range of node's value is in the range of 32-bit signed integer.  
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(3d, 14.5d, 11d), averageOfLevels(tree("3,9,n,n,20,15,n,n,7,n,n")));
  }

  public List<Double> averageOfLevels(TreeNode root) {
    return levelOrder(root).stream().map(list -> list.stream().mapToDouble(i -> i).average().getAsDouble())
        .collect(Collectors.toList());
  }

}
