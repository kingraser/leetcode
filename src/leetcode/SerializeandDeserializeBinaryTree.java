package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

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

  @Test
  public void test() {
    TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), new TreeNode(5)));
    assertEquals(root, deserialize(serialize(root)));
  }

  public String serialize(TreeNode root) {
    return root.toString();
  }

  public TreeNode deserialize(String data) {
    return TreeNode.tree(data);
  }
}
