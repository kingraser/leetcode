package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class InorderSuccessorinBST {

  /*
  Given a binary search tree and a node in it, find the in-order successor of that node in the BST.  
  Note: If the given node has no in-order successor in the tree, return null.
  */

  @Test
  public void test() {
    TreeNode root = tree("3,2,1,n,n,n,4,n,5,n,n");
    assertEquals(tree("4,n,5,n,n"), successor(root, new TreeNode(3)));
    assertEquals(tree("2,1,n,n,n"), predecessor(root, new TreeNode(3)));
  }

  public TreeNode successor(TreeNode root, TreeNode p) {
    if (root == null) return null;
    if (root.val <= p.val) return successor(root.right, p);
    TreeNode left = successor(root.left, p);
    return left == null ? root : left;
  }

  public TreeNode predecessor(TreeNode root, TreeNode p) {
    if (root == null) return null;
    if (root.val >= p.val) return predecessor(root.left, p);
    TreeNode right = predecessor(root.right, p);
    return right == null ? root : right;
  }
}
