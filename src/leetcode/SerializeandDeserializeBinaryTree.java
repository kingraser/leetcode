/*

 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Stack;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月29日<p>
//-------------------------------------------------------
public class SerializeandDeserializeBinaryTree {

  /*
  Serialization is the process of converting a data structure or object into a sequence of bits 
  so that it can be stored in a file or memory buffer, 
  or transmitted across a network connection link 
  to be reconstructed later in the same or another computer environment.
  
  Design an algorithm to serialize and deserialize a binary tree. 
  There is no restriction on how your serialization/deserialization algorithm should work. 
  You just need to ensure that a binary tree can be serialized to a string 
  and this string can be deserialized to the original tree structure.
  
  For example, you may serialize the following tree
  
       1
      / \
     2   3
        / \
       4   5
  
  as "[1,2,3,null,null,4,5]". 
  You do not necessarily need to follow this format, 
  so please be creative and come up with different approaches yourself.
  Note: Do not use class member/global/static variables to store states. 
  Your serialize and deserialize algorithms should be stateless. 
  */

  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    serialize(root, sb);
    return sb.toString();
  }

  private void serialize(TreeNode root, StringBuilder sb) {
    if (root == null) {
      sb.append('n');
      return;
    }
    sb.append(root.val).append(',');
    serialize(root.left, sb);
    sb.append(',');
    serialize(root.right, sb);
  }

  public TreeNode deserialize(String data) {
    String[] nodes = data.split(",");
    Stack<TreeNode> stack = new Stack<>();
    for (int i = nodes.length - 1; i >= 0; i--)
      if (nodes[i].charAt(0) == 'n') stack.push(null);
      else {
        TreeNode node = new TreeNode(Integer.parseInt(nodes[i]));
        node.left = stack.pop();
        node.right = stack.pop();
        stack.push(node);
      }
    return stack.peek();
  }
}
