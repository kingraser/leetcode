package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class SameTree {

  /*
  Given two binary trees, write a function to check if they are equal or not.    
  Two binary trees are considered equal if they are structurally identical and the nodes have the same value. 
  */

  @Test
  public void test() {
    assertEquals(tree("2,1,n,n,3,n,n"), tree("2,1,n,n,3,n,n"));
    assertNotEquals(tree("1,2,n,n,3,n,n"), tree("2,1,n,n,3,n,n"));
  }

  public boolean isSameTree(TreeNode p, TreeNode q) {
    return Objects.equals(p, q);
  }
}
