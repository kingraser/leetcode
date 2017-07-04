package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class MergeTwoBinaryTrees {

  /*
  Given two binary trees and imagine that when you put one of them to cover the other, 
  some nodes of the two trees are overlapped while the others are not.
  
  You need to merge them into a new binary tree. 
  The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. 
  Otherwise, the NOT null node will be used as the node of new tree.
  
  Example 1:  
  Input: 
  Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
  Output: 
  Merged tree:
       3
      / \
     4   5
    / \   \ 
   5   4   7
  
  Note: The merging process must start from the root nodes of both trees. 
  */

  @Test
  public void test() {
    assertEquals(TreeNode.tree("3,4,5,n,n,4,n,n,5,n,7,n,n"),
        mergeTrees(TreeNode.tree("1,3,5,n,n,n,2,n,n"), TreeNode.tree("2,1,n,4,n,n,3,n,7,n,n")));
  }

  public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
    return t1 == null ? t2
        : t2 == null ? t1 : new TreeNode(t1.val + t2.val, mergeTrees(t1.left, t2.left), mergeTrees(t1.right, t2.right));
  }

}
