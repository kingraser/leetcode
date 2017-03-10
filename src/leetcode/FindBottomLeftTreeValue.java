package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import leetcode.common.TreeNode;

public class FindBottomLeftTreeValue {

  /*
  Given a binary tree, find the leftmost value in the last row of the tree.
  
  Example 1:  
  Input:
  
    2
   / \
  1   3
  
  Output: 1
  
  Example 2:  
  Input:
  
        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7
  
  Output: 7
  
  Note: You may assume the tree (i.e., the given root node) is not NULL. 
  */

  @Test
  public void test() {
    assertEquals(1, findLeftMostNode(tree("2,1,n,n,3,n,n")));
    assertEquals(7, findLeftMostNode(tree("1,2,4,n,n,n,3,5,7,n,n,n,6,n,n")));
  }

  public int findLeftMostNode(TreeNode root) {
    List<List<Integer>> list = new ArrayList<>();
    search(root, 0, list);
    return list.get(list.size() - 1).get(0);
  }

  private void search(TreeNode root, int i, List<List<Integer>> list) {
    if (root == null) return;
    if (list.size() == i) list.add(new ArrayList<>());
    list.get(i).add(root.val);
    search(root.left, i + 1, list);
    search(root.right, i + 1, list);
  }
}
