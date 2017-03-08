package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class PathSumII {
  /*
  Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
  For example:
  Given the below binary tree and sum = 22,
  
            5
           / \
          4   8
         /   / \
        11  13  4
       /  \    / \
      7    2  5   1
  
  return
  
  [
     [5,4,11,2],
     [5,8,4,5]
  ]
  */

  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(root, sum, new ArrayDeque<>(), result);
    return result;
  }

  public void dfs(TreeNode root, int sum, Deque<Integer> deque, List<List<Integer>> result) {
    if (Objects.isNull(root)) return;
    deque.add(root.val);
    if ((sum -= root.val) == 0 && root.left == null && root.right == null) result.add(new ArrayList<>(deque));
    dfs(root.left, sum, deque, result);
    dfs(root.right, sum, deque, result);
    deque.pollLast();
  }

  @Test
  public void test() {
    TreeNode root = tree("5,4,11,7,n,n,2,n,n,n,8,13,n,n,4,5,n,n,1,n,n");
    List<List<Integer>> expected = Arrays.asList(Arrays.asList(5, 4, 11, 2), Arrays.asList(5, 8, 4, 5));
    assertEquals(new HashSet<>(expected), new HashSet<>(pathSum(root, 22)));
  }
}
