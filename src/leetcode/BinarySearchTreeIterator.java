package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.IntStream;

import org.junit.Test;

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
    Iterator<Integer> iterator = new BSTIterator(tree("2,1,n,n,3,n,n"));
    assertTrue(IntStream.range(1, 4).allMatch(i -> i == iterator.next()));
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
      if (!hasNext()) throw new NoSuchElementException();
      TreeNode node = stack.pop();
      pushLeft(node.right);
      return node.val;
    }

    private void pushLeft(TreeNode node) {
      for (; Objects.nonNull(node); stack.push(node), node = node.left);
    }
  }
}
