package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import leetcode.common.TreeNode;

public class FindLeavesofBinaryTree {

  /*
  Given a binary tree, find all leaves and then remove those leaves. Then repeat the previous steps until the tree is empty.  
  Example:
  Given binary tree
  
          1
         / \
        2   3
       / \     
      4   5    
  
  Returns [4, 5, 3], [2], [1].
  
  Explanation:  
  1. Remove the leaves [4, 5, 3] from the tree
  
          1
         / 
        2          
  
  2. Remove the leaf [2] from the tree
  
          1          
  
  3. Remove the leaf [1] from the tree
  
          []         
  
  Returns [4, 5, 3], [2], [1].
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(Arrays.asList(4, 5, 3), Arrays.asList(2), Arrays.asList(1)),
        findLeaves(tree("1,2,4,n,n,5,n,n,3,n,n")));
  }

  public List<List<Integer>> findLeaves(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(root, result);
    return result;
  }

  /**
   * @param node 
   * @param result
   * @return depth of node. leaf is 0
   */
  private int dfs(TreeNode node, List<List<Integer>> result) {
    if (node == null) return -1;
    int level = 1 + Math.max(dfs(node.left, result), dfs(node.right, result));
    while (result.size() < level + 1)
      result.add(new ArrayList<>());
    result.get(level).add(node.val);
    return level;
  }
}
