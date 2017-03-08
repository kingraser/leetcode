package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class PathSumIII {
  /*
  You are given a binary tree in which each node contains an integer value.  
  Find the number of paths that sum to a given value.  
  The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
  The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.  
  Example:
  
  root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
  
        10
       /  \
      5   -3
     / \    \
    3   2   11
   / \   \
  3  -2   1
  
  Return 3. The paths that sum to 8 are:
  
  1.  5 -> 3
  2.  5 -> 2 -> 1
  3. -3 -> 11
  */

  @Test
  public void test() {
    TreeNode root = tree("10,5,3,3,n,n,-2,n,n,2,n,1,n,n,-3,n,11,n,n");
    assertEquals(3, pathSum(root, 8, true));
  }

  public int pathSum(TreeNode node, int sum, boolean isOriginal) {
    return null == node ? 0
        : pathSum(node.left, sum - node.val, false) + pathSum(node.right, sum - node.val, false)
            + (node.val == sum ? 1 : 0)
            + (isOriginal ? pathSum(node.left, sum, true) + pathSum(node.right, sum, true) : 0);
  }

}
