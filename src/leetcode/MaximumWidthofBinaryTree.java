package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import leetcode.common.TreeNode;

public class MaximumWidthofBinaryTree {

  /*
  Given a binary tree, write a function to get the maximum width of the given tree. 
  The width of a tree is the maximum width among all levels. 
  The binary tree has the same structure as a full binary tree, but some nodes are null.
  
  The width of one level is defined as the length between the end-nodes. 
  the leftmost and right most non-null nodes in the level, 
  where the null nodes between the end-nodes are also counted into the length calculation.
  
  Example 1:  
  Input:   
           1
         /   \
        3     2
       / \     \  
      5   3     9   
  Output: 4
  Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
  
  Example 2:  
  Input:   
          1
         /  
        3    
       / \       
      5   3   
  Output: 2
  Explanation: The maximum width existing in the third level with the length 2 (5,3).
  
  Example 3:  
  Input:   
          1
         / \
        3   2 
       /        
      5 
  Output: 2
  Explanation: The maximum width existing in the second level with the length 2 (3,2).
  
  Example 4:  
  Input:   
          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
  Output: 8
  Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
    
  Note: Answer will in the range of 32-bit signed integer. 
  */

  @Test
  public void test() {
    assertEquals(2, widthOfBinaryTree(tree("1,1,5,n,n,3,n,n,n")));
  }

  public int widthOfBinaryTree(TreeNode root) {
    return dfs(root, 0, 1, new ArrayList<>(), new int[1])[0];
  }

  public int[] dfs(TreeNode root, int level, int idx, List<Integer> list, int[] max) {
    if (root == null) return max;
    if (level == list.size()) list.add(idx);
    max[0] = Math.max(max[0], idx + 1 - list.get(level));
    dfs(root.left, ++level, idx <<= 1, list, max);
    dfs(root.right, level, ++idx, list, max);
    return max;
  }

}
