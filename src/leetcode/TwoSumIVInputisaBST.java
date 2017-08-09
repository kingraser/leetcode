package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import leetcode.common.TreeNode;

public class TwoSumIVInputisaBST {

  /*
  Given a Binary Search Tree and a target number, 
  return true if there exist two elements in the BST such that their sum is equal to the given target.
  
  Example 1:  
  Input: 
      5
     / \
    3   6
   / \   \
  2   4   7  
  Target = 9  
  Output: True
  
  Example 2:  
  Input: 
      5
     / \
    3   6
   / \   \
  2   4   7  
  Target = 28  
  Output: False  
  */

  @Test
  public void test() {
    TreeNode node = tree("5,3,2,n,n,4,n,n,6,n,7,n,n");
    assertTrue(findTarget(node, 9));
    assertFalse(findTarget(node, 28));
  }

  public boolean findTarget(TreeNode root, int k) {
    List<Integer> list = new ArrayList<>();
    inorder(root, list);
    for (int l = 0, r = list.size() - 1, sum; l < r;)
      if ((sum = list.get(l) + list.get(r)) == k) return true;
      else if (sum < k) l++;
      else r--;
    return false;
  }

  public void inorder(TreeNode root, List<Integer> list) {
    if (root == null) return;
    inorder(root.left, list);
    list.add(root.val);
    inorder(root.right, list);
  }

}
