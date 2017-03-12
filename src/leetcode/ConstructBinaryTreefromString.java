package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import leetcode.common.TreeNode;

public class ConstructBinaryTreefromString {

  @Test
  public void test() {
    assertEquals(tree("4,2,3,n,n,1,n,n,6,5,n,n,n"), str2tree("4(2(3)(1))(6(5))"));
  }

  public TreeNode str2tree(String s) {
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
