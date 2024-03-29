package leetcode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import leetcode.common.TreeNode;

public class PathSum {

  /*
  Given a binary tree and a sum, determine if the tree has a root-to-leaf path 
  such that adding up all the values along the path equals the given sum.
  For example:
  Given the below binary tree and sum = 22,
  
            5
           / \
          4   8
         /   / \
        11  13  4
       /  \      \
      7    2      1
  
  return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
  */

  @Test
  public void test() {
    assertTrue(hasPathSum(TreeNode.tree("5,4,11,7,n,n,2,n,n,n,8,13,n,n,4,n,1,n,n"), 22));
  }

  public boolean hasPathSum(TreeNode root, int sum) {
    return null != root && (null == root.left && null == root.right ? sum == root.val
            : hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val));
  }

}
