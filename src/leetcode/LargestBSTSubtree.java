package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class LargestBSTSubtree {

  /*
  Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), 
  where largest means subtree with largest number of nodes in it.
  
  Note:
  A subtree must include all of its descendants.
  Here's an example:
  
      10
     / \
    5#  15
   / \   \ 
  1#  8#  7
  
  The Largest BST Subtree in this case is the highlighted one. 
  The return value is the subtree's size, which is 3.  
  Hint: You can recursively use algorithm similar to <leetcode.ValidateBinarySearchTree> at each node of the tree, 
  which will result in O(nlogn) time complexity.
  Follow up:
  Can you figure out ways to solve it with O(n) time complexity?
  */

  @Test
  public void test() {
    assertEquals(3, largestBSTSubtree(tree("10,5,1,n,n,8,n,n,15,n,7,n,n")));
  }

  class BST {
    int count, min, max;

    public BST(int result, int min, int max) {
      this.count = result;
      this.min = min;
      this.max = max;
    }
  }

  public int largestBSTSubtree(TreeNode root) {
    return Math.abs(BSTSubstree(root).count);
  }

  private BST BSTSubstree(TreeNode root) {
    if (root == null) return new BST(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
    BST left = BSTSubstree(root.left), right = BSTSubstree(root.right);
    if (left.count < 0 || right.count < 0 || root.val < left.max || root.val > right.min)
      return new BST(Math.max(Math.abs(left.count), Math.abs(right.count)) * -1, 0, 0);
    return new BST(left.count + right.count + 1, Math.min(root.val, left.min), Math.max(root.val, right.max));
  }
}
