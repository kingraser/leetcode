package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import leetcode.common.TreeNode;

public class MostFrequentSubtreeSum {

  /*
  Given the root of a tree, you are asked to find the most frequent subtree sum. 
  The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). 
  So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
  
  Examples 1
  Input:
  
    5
   /  \
  2   -3
  
  return [2, -3, 4], since all the values happen only once, return all of them in any order.
  
  Examples 2
  Input:
  
    5
   /  \
  2   -5
  
  return [2], since 2 happens twice, however -5 only occur once. 
  */

  @Test
  public void test() {
    int[] actual = findFrequentTreeSum(TreeNode.tree("5,2,n,n,-3,n,n"));
    Arrays.sort(actual);
    assertArrayEquals(new int[] { -3, 2, 4 }, actual);
    assertArrayEquals(new int[] { 2 }, findFrequentTreeSum(TreeNode.tree("5,2,n,n,-5,n,n")));
  }

  int max;

  public int[] findFrequentTreeSum(TreeNode root) {
    max = Integer.MIN_VALUE;
    Map<Integer, Integer> map = new HashMap<>();
    postOrder(root, map);
    return getResult(map);
  }

  private int[] getResult(Map<Integer, Integer> map) {
    return map.entrySet().stream().filter(e -> e.getValue().intValue() == max).mapToInt(e -> e.getKey()).toArray();
  }

  private int postOrder(TreeNode node, Map<Integer, Integer> map) {
    return node == null ? 0 : log(postOrder(node.left, map) + postOrder(node.right, map) + node.val, map);
  }

  private int log(int val, Map<Integer, Integer> map) {
    max = Math.max(max, map.compute(val, (k, v) -> v == null ? 1 : v + 1));
    return val;
  }
}
