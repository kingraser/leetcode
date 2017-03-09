package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

import org.junit.Test;

import com.google.common.collect.Lists;

import leetcode.common.TreeNode;

public class BinarySearchTreeIterator {

  /*
  Implement an iterator over a binary search tree (BST). 
  Your iterator will be initialized with the root node of a BST.    
  Calling next() will return the next smallest number in the BST.    
  Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, 
  where h is the height of the tree. 
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(1, 2, 3), Lists.newArrayList(new BSTIterator(tree("2,1,n,n,3,n,n"))));
  }

  public class BSTIterator implements Iterator<Integer> {
    private Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
      pushLeft(root);
    }

    public boolean hasNext() {
      return !stack.isEmpty();
    }

    public Integer next() {
      TreeNode node = stack.pop();
      pushLeft(node.right);
      return node.val;
    }

    private void pushLeft(TreeNode node) {
      for (; node != null; stack.push(node), node = node.left);
    }
  }
}
