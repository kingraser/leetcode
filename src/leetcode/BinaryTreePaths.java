package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BinaryTreePaths {

  /*
  Given a binary tree, return all root-to-leaf paths.
  
  For example, given the following binary tree:
  
     1
   /   \
  2     3
   \
    5
  
  All root-to-leaf paths are:["1->2->5", "1->3"]
  */

  public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();
    dfs(root, new ArrayDeque<>(), result);
    return result;
  }

  private void dfs(TreeNode root, Deque<TreeNode> deque, List<String> result) {
    if (root == null) return;
    if (root.left == null && root.right == null) {
      deque.addLast(root);
      result
          .add(String.join("->", deque.stream().map(node -> Integer.toString(node.val)).collect(Collectors.toList())));
      deque.removeLast();
      return;
    }
    deque.offerLast(root);
    dfs(root.left, deque, result);
    dfs(root.right, deque, result);
    deque.pollLast();
  }

  @Test
  public void test() {
    List<String> expected = Arrays.asList("1->2->5", "1->3");
    TreeNode root = tree("1,2,n,5,n,n,3,n,n");
    assertEquals(new HashSet<>(expected), new HashSet<>(binaryTreePaths(root)));
  }

}
