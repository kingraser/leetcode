package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class FindLeftMostElement {

  /*
  Given a binary tree, find the leftmost value in the last row of the tree.
  Example 1:  
  Input:
  
    2
   / \
  1   3
  
  Output:
  1
  
  Example 2:  
  Input:
  
        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7
  
  Output:
  7
  */

  @Test
  public void test() {
    assertEquals(1, findLeftMostNode(tree("2,1,n,n,3,n,n")));
    assertEquals(7, findLeftMostNode(tree("1,2,4,n,n,n,3,5,7,n,n,n,6,n,n")));
  }

  public int findLeftMostNode(TreeNode root) {
    int[] result = new int[] { -1, 0 };//first for the row, second for the left most value of the row
    search(root, 0, result);
    return result[1];
  }

  private void search(TreeNode node, int row, int[] result) {
    if (node == null) return;
    if (result[0] < row) {
      result[0] = row;
      result[1] = node.val;
    }
    search(node.left, row + 1, result);
    search(node.right, row + 1, result);
  }

}
