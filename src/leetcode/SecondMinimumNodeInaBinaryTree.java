package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class SecondMinimumNodeInaBinaryTree {

  /*
  Given a non-empty special binary tree consisting of nodes with the non-negative value, 
  where each node in this tree has exactly two or zero sub-node. 
  If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.
  
  Given such a binary tree, 
  you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
  
  If no such second minimum value exists, output -1 instead.
  
  Example 1:Input: 
    2
   / \
  2   5
     / \
    5   7  
  Output: 5
  Explanation: The smallest value is 2, the second smallest value is 5.
  
  Example 2:Input: 
    2
   / \
  2   2
    
  Output: -1
  Explanation: The smallest value is 2, but there isn't any second smallest value.  
  */

  @Test
  public void test() {
    assertEquals(5, findSecondMinimumValue(tree("2,2,n,n,5,5,n,n,7,n,n")));
    assertEquals(-1, findSecondMinimumValue(tree("2,2,n,n,2,n,n")));
  }

  public int findSecondMinimumValue(TreeNode root) {
    Integer[] result = new Integer[2];
    preOrder(root, result);
    return result[1] == null ? -1 : result[1];
  }

  private void preOrder(TreeNode root, Integer[] result) {
    if (root == null) return;
    if (result[0] == null) result[0] = root.val; // get the smallest element
    else if (result[0] == root.val) ; // duplicate smallest element
    else if (result[1] == null || result[1] > root.val) result[1] = root.val; // get the second smallest element
    else if (result[1] < root.val) return; // prune impossible branches
    preOrder(root.left, result);
    preOrder(root.right, result);
  }

}
