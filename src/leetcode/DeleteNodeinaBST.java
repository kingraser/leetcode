package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class DeleteNodeinaBST {

  /*
  Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
  
  Basically, the deletion can be divided into two stages:
  
    Search for a node to remove.
    If the node is found, delete the node.
  
  Note: Time complexity should be O(height of tree).
  
  Example:
  
  root = [5,3,6,2,4,null,7]
  key = 3
  
      5
     / \
    3   6
   / \   \
  2   4   7
  
  Given key to delete is 3. So we find the node with value 3 and delete it.
  
  One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
  
      5
     / \
    4   6
   /     \
  2       7
  
  Another valid answer is [5,2,6,null,4,null,7].
  
    5
   / \
  2   6
   \   \
    4   7
  */

  @Test
  public void test() {
    assertEquals(tree("5,2,n,4,n,n,6,n,7,n,n"), deleteNode(tree("5,3,2,n,n,4,n,n,6,n,7,n,n"), 3));
  }

  public TreeNode deleteNode(TreeNode root, int key) {
    if (Objects.isNull(root)) return null;
    if (root.val == key) return merge(root.left, root.right);
    else if (root.val > key) root.left = deleteNode(root.left, key);
    else root.right = deleteNode(root.right, key);
    return root;
  }

  private TreeNode merge(TreeNode left, TreeNode right) {
    if (Objects.isNull(left)) return right;
    if (Objects.isNull(right)) return left;
    left.right = merge(left.right, right);
    return left;
  }

}
