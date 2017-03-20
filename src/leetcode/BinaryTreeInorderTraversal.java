package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BinaryTreeInorderTraversal {

  /*
  Given a binary tree, return the inorder traversal of its nodes' values.
  
  For example:
  Given binary tree {1,#,2,3},
  
     1
      \
       2
      /
     3
  
  return [1,3,2]. 
  */

  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    inorderTraversal(root, list);
    return list;
  }

  private void inorderTraversal(TreeNode node, List<Integer> list) {
    if (Objects.isNull(node)) return;
    inorderTraversal(node.left, list);
    list.add(node.val);
    inorderTraversal(node.right, list);
  }

  @Test
  public void test() {
    assertEquals(Arrays.asList(1, 3, 2), inorderTraversal(tree("1,n,2,3,n,n,n")));
  }

}
