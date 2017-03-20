package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BinaryTreePostorderTraversal {

  /*    
  Given a binary tree, return the postorder traversal of its nodes' values.
  
  For example:
  Given binary tree {1,#,2,3},
  
             1
              \
               2
              /
             3
  
  return [3,2,1]. 
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(3, 2, 1), postorderTraversal(tree("1,n,2,3,n,n,n")));
  }

  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    postorderTraversal(root, list);
    return list;
  }

  private void postorderTraversal(TreeNode root, List<Integer> list) {
    if (Objects.isNull(root)) return;
    postorderTraversal(root.left, list);
    postorderTraversal(root.right, list);
    list.add(root.val);
  }

}
