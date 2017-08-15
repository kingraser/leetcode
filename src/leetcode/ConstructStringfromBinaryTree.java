package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class ConstructStringfromBinaryTree {

  /*
  You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.  
  The null node needs to be represented by empty parenthesis pair "()". 
  And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.
  
  Example 1:  
  Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4       
  Output: "1(2(4))(3)"  
  Explanation: Originally it needs to be "1(2(4)())(3()())", 
  but you need to omit all the unnecessary empty parenthesis pairs. 
  And it will be "1(2(4))(3)".
  
  Example 2:  
  Input: Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4   
  Output: "1(2()(4))(3)"  
  Explanation: Almost the same as the first example, 
  except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
  */

  @Test
  public void test() {
    assertEquals("1(2(4))(3)", tree2str(tree("1,2,4,n,n,n,3,n,n")));
    assertEquals("1(2()(4))(3)", tree2str(tree("1,2,n,4,n,n,3,n,n")));
  }

  public String tree2str(TreeNode t) {
    if (t == null) return "";
    if (t.isLeaf()) return Integer.toString(t.val);
    if (t.left == null) return t.val + "()(" + tree2str(t.right) + ")";
    if (t.right == null) return t.val + "(" + tree2str(t.left) + ")";
    return t.val + "(" + tree2str(t.left) + ")" + "(" + tree2str(t.right) + ")";
  }

}
