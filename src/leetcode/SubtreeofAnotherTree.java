package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class SubtreeofAnotherTree {

  /*
  Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. 
  A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
  
  Example 1:
  Given tree s:
  
      3
     / \
    4   5
   / \
  1   2
  
  Given tree t:
  
    4 
   / \
  1   2
  
  Return true, because t has the same structure and node values with a subtree of s.
  
  Example 2:
  Given tree s:
  
      3
     / \
    4   5
   / \
  1   2
     /
    0
  
  Given tree t:
  
    4
   / \
  1   2
  
  Return false. 
  */

  @Test
  public void test() {
    assertTrue(isSubtree(tree("3,4,1,n,n,2,n,n,5,n,n"), tree("4,1,n,n,2,n,n")));
    assertFalse(isSubtree(tree("3,4,1,n,n,2,0,n,n,n,5,n,n"), tree("4,1,n,n,2,n,n")));
  }

  public boolean isSubtree(TreeNode s, TreeNode t) {
    return t.equals(s) || (Objects.nonNull(s) && (isSubtree(s.left, t) || isSubtree(s.right, t)));
  }

}
