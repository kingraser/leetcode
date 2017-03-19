package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Objects;
import java.util.Stack;

import org.junit.Test;

import leetcode.common.TreeNode;

public class ConvertBSTtoGreaterTree {

  /*
  Given a Binary Search Tree (BST), 
  convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
  
  Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13
  
  Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
  */

  @Test
  public void test() {
    assertEquals(tree("18,20,n,n,13,n,n"), convertBST(tree("5,2,n,n,13,n,n")));
  }

  public TreeNode convertBST(TreeNode root) {
    convert(root, new Stack<>());
    return root;
  }

  private void convert(TreeNode node, Stack<TreeNode> stack) {
    for (int sum = 0; Objects.nonNull(node) || !stack.isEmpty(); node = node.left) {
      pushRight(node, stack);
      sum = (node = stack.pop()).val += sum;
    }
  }

  private void pushRight(TreeNode node, Stack<TreeNode> stack) {
    for (; Objects.nonNull(node); stack.push(node), node = node.right);
  }

}
