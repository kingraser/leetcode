package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import leetcode.common.TreeNode;

public class ConstructBinaryTreefromString {

  /*
  You need to construct a binary tree from a string consisting of parenthesis and integers.  
  The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. 
  The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.  
  You always start to construct the left child node of the parent first if it exists.
  
  Example:  
  Input: "4(2(3)(1))(6(5))"
  Output: return the tree root node representing the following tree:
  
       4
     /   \
    2     6
   / \   / 
  3   1 5   
  
  Note: There will only be '(', ')', '-' and '0' ~ '9' in the input string.  
  */

  @Test
  public void test() {
    assertEquals(tree("4,2,3,n,n,1,n,n,6,5,n,n,n"), str2tree("4(2(3)(1))(6(5))"));
  }

  public TreeNode str2tree(String s) {
    if (s == null || s.length() == 0) return null;
    Stack<TreeNode> stack = new Stack<>();
    boolean[] isNegative = new boolean[1];
    Integer[] num = new Integer[1];
    for (char c : s.toCharArray())
      if (c == '-') isNegative[0] = true;
      else if (c >= '0' && c <= '9') num[0] = (num[0] == null ? 0 : num[0]) * 10 + c - '0';
      else if (c == '(' && num[0] != null) stack.push(getNode(isNegative, num));
      else if (c == ')') {
        TreeNode node = num[0] != null ? getNode(isNegative, num) : stack.pop();
        if (stack.peek().left == null) stack.peek().left = node;
        else stack.peek().right = node;
      }
    return stack.isEmpty() ? getNode(isNegative, num) : stack.pop();
  }

  private TreeNode getNode(boolean[] isNegative, Integer[] num) {
    TreeNode result = new TreeNode(isNegative[0] ? -num[0] : num[0]);
    isNegative[0] = false;
    num[0] = null;
    return result;
  }

}
