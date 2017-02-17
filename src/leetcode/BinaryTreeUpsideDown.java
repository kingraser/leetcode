package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BinaryTreeUpsideDown {
  /*
  Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, 
  flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
  For example:
  Given a binary tree {1,2,3,4,5},
  
      1
     / \
    2   3
   / \
  4   5
  
  return the root of the binary tree [4,5,2,#,#,3,1].
  
     4
    / \
   5   2
      / \
     3   1  
  */

  /*
      1
     / \
    2   3
   / \
  4   5
  
      1
     / 
    2-- 3
   / 
  4 --5
  */

  @Test
  public void test() {
    assertEquals(tree("4,5,n,n,2,3,n,n,1,n,n"), upsideDownBinaryTree(tree("1,2,4,n,n,5,n,n,3,n,n")));
  }

  public TreeNode upsideDownBinaryTree(TreeNode root) {
    if (Objects.isNull(root) || Objects.isNull(root.left) && Objects.isNull(root.right)) return root;
    TreeNode newRoot = upsideDownBinaryTree(root.left);
    root.left.left = root.right;
    root.left.right = root;
    root.left = null;
    root.right = null;
    return newRoot;
  }

}
