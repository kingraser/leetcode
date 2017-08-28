package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import leetcode.common.TreeNode;

public class EqualTreePartition {

  /*
  Given a binary tree with n nodes, 
  your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.
  
  Example 1:  
  Input:     
    5
   / \
  10 10
    /  \
   2    3
  
  Output: True
  Explanation: 
    5
   / 
  10      
  Sum: 15  
    10
   /  \
  2    3  
  Sum: 15
  
  Example 2:  
  Input:     
    1
   / \
  2  10
    /  \
   2   20  
  Output: False
  Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
  
  Note:  
    The range of tree node value is in the range of [-100000, 100000].
    1 <= n <= 10000  
  */

  @Test
  public void test() {
    assertTrue(checkEqualTree(tree("5,10,n,n,10,2,n,n,3,n,n")));
    assertFalse(checkEqualTree(tree("1,2,n,n,10,2,n,n,20,n,n")));
  }

  public boolean checkEqualTree(TreeNode root) {
    Map<Integer, Integer> map = new HashMap<>();
    int sum = getSum(root, map);
    if (sum == 0) return map.getOrDefault(0, 0) > 1;
    return (sum & 1) == 0 && map.containsKey(sum >> 1);
  }

  private int getSum(TreeNode root, Map<Integer, Integer> map) {
    if (root == null) return 0;
    int cur = root.val + getSum(root.left, map) + getSum(root.right, map);
    map.merge(cur, 1, Integer::sum);
    return cur;
  }

}
