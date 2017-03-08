package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class RecoverBinarySearchTree {

  /*
  Two elements of a binary search tree (BST) are swapped by mistake.
  
  Recover the tree without changing its structure.
  Note:
  A solution using O(n) space is pretty straight forward. Could you devise a constant space solution? 
  */

  @Test
  public void test() {
    TreeNode root = tree("1,2,n,n,3,n,n");
    recoverTree(root);
    assertEquals(tree("2,1,n,n,3,n,n"), root);
  }

  private TreeNode mistake1, mistake2, previous;
  private boolean hasFound;

  public void recoverTree(TreeNode root) {
    hasFound = false;
    inOrder(root);
    swap(mistake1, mistake2);
  }

  private void swap(TreeNode t1, TreeNode t2) {
    int tmp = t1.val;
    t1.val = t2.val;
    t2.val = tmp;
  }

  private void inOrder(TreeNode root) {
    if (hasFound || root == null) return;
    inOrder(root.left);
    if (previous != null && root.val < previous.val) {
      mistake1 = previous;
      mistake2 = root;
      hasFound = true;
      return;
    }
    previous = root;
    inOrder(root.right);
  }

}
