package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class FindModeinBinarySearchTree {

  /*
  Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
  
  Assume a BST is defined as follows:
  
    The left subtree of a node contains only nodes with keys less than or equal to the node's key.
    The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
    Both the left and right subtrees must also be binary search trees.
  
  For example:
  Given BST [1,null,2,2],
  
   1
    \
     2
    /
   2
  
  return [2].
  
  Note: If a tree has more than one mode, you can return them in any order.
  
  Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count). 
  */

  @Test
  public void test() {
    assertArrayEquals(new int[] { 2 }, findMode(TreeNode.tree("1,n,2,2,n,n,n")));
  }

  public int[] findMode(TreeNode root) {
    modes = null;
    inOrder(root);
    modes = new int[modeCount];
    modeCount = 0;
    curCount = 0;
    inOrder(root);
    return modes;
  }

  private int curVal, curCount = 0, maxCount = 0, modeCount = 0, modes[];

  private void handleValue(int val) {
    if (val != curVal) {
      curVal = val;
      curCount = 0;
    }
    if (++curCount > maxCount) {
      maxCount = curCount;
      modeCount = 1;
    } else if (curCount == maxCount) {
      if (modes != null) modes[modeCount] = curVal;
      modeCount++;
    }
  }

  private void inOrder(TreeNode root) {
    if (root == null) return;
    inOrder(root.left);
    handleValue(root.val);
    inOrder(root.right);
  }
}
