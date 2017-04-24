package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BinaryTreeTilt {

  /*
  Given a binary tree, return the tilt of the whole tree.  
  The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. 
  Null node has tilt 0.
  
  The tilt of the whole tree is defined as the sum of all nodes' tilt.
  
  Example:  
  Input: 
         1
       /   \
      2     3
  Output: 1
  Explanation: 
  Tilt of node 2 : 0
  Tilt of node 3 : 0
  Tilt of node 1 : |2-3| = 1
  Tilt of binary tree : 0 + 0 + 1 = 1
  
  Note:  
    The sum of node values in any subtree won't exceed the range of 32-bit integer.
    All the tilt values won't exceed the range of 32-bit integer.  
  */

  @Test
  public void test() {
    assertEquals(1, findTilt(tree("1,2,n,n,3,n,n")));
  }

  public int findTilt(TreeNode root) {
    int[] result = new int[1];
    postOrder(root, result);
    return result[0];
  }

  public int postOrder(TreeNode node, int[] result) {
    if (Objects.isNull(node)) return 0;
    int left = postOrder(node.left, result), right = postOrder(node.right, result);
    result[0] += Math.abs(left - right);
    return left + right + node.val;
  }

}
