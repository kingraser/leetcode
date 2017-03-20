package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import org.junit.Test;

import leetcode.common.TreeNode;

public class ClosestBinarySearchTreeValueII {

  /*
  Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
  
  Note:  
    Given target value is a floating point.
    You may assume k is always valid, that is: k ≤ total nodes.
    You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
   
  Follow up:
  Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
  
  Hint:  
  1. Consider implement these two helper functions:
  　　i. getPredecessor(N), which returns the next smaller node to N.
  　　ii. getSuccessor(N), which returns the next larger node to N.
  2. Try to assume that each node has a parent pointer, it makes the problem much easier.
  3. Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
  4. You would need two stacks to track the path in finding predecessor and successor node separately.
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(2, 3, 4), closestKValues(tree("4,2,1,n,n,3,n,n,5,n,n"), 3, 3));
  }

  public List<Integer> closestKValues(TreeNode root, double target, int k) {
    LinkedList<Integer> result = new LinkedList<>();
    Stack<TreeNode> stack = new Stack<>();
    for (TreeNode node = root; Objects.nonNull(node) || !stack.empty(); node = node.right) {
      for (; Objects.nonNull(node); stack.push(node), node = node.left);
      node = stack.pop();
      if (result.size() < k) result.addLast(node.val);
      else if (Math.abs(node.val - target) < Math.abs(result.peekFirst() - target)) {
        result.pollFirst();
        result.addLast(node.val);
      } else break;
    }
    return result;
  }
}
