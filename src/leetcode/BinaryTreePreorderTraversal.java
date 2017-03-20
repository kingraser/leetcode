package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BinaryTreePreorderTraversal {
  /*
  Given a binary tree, return the preorder traversal of its nodes' values.
  
  For example:
  Given binary tree {1,#,2,3},
  
     1
      \
       2
      /
     3
  
  return [1,2,3]. 
  */

  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    preorderTraversal(root, list);
    return list;
  }

  private void preorderTraversal(TreeNode root, List<Integer> list) {
    if (Objects.isNull(root)) return;
    list.add(root.val);
    preorderTraversal(root.left, list);
    preorderTraversal(root.right, list);
  }

  @Test
  public void test() {
    assertEquals(Arrays.asList(1, 2, 3), preorderTraversal(tree("1,n,2,3,n,n,n")));
  }
}
