package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

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

  private void dfs(TreeNode node, Deque<String> deque, List<String> result) {
    if (Objects.isNull(node)) return;
    if (node.isLeaf()) {
      deque.addLast("" + node.val);
      result.add(String.join("->", deque));
      deque.pollLast();
      return;
    }
    deque.addLast("" + node.val);
    dfs(node.left, deque, result);
    dfs(node.right, deque, result);
    deque.pollLast();
  }

  @Test
  public void test() {
    assertEquals(Arrays.asList("1->2->5", "1->3"), binaryTreePaths(tree("1,2,n,5,n,n,3,n,n")));
  }

}
