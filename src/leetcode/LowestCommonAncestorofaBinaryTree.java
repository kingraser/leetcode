package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class LowestCommonAncestorofaBinaryTree {
  /*
  Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
  
  According to the definition of LCA on Wikipedia: 
  “The lowest common ancestor is defined between two nodes v and w as the lowest node in T 
  that has both v and w as descendants (where we allow a node to be a descendant of itself).”
  
          _______3______
         /              \
      ___5__          ___1__
     /      \        /      \
     6      _2       0       8
           /  \
           7   4
  
  For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. 
  Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
  */

  @Test
  public void test() {
    TreeNode one = tree("1,0,n,n,8,n,n"), five = tree("5,6,n,n,2,7,n,n,4,n,n"), root = new TreeNode(3, five, one);
    assertEquals(root, lowestCommonAncestor(root, five, one));
    assertEquals(five, lowestCommonAncestor(root, five, new TreeNode(4)));
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (Objects.isNull(root) || root == p || root == q) return root;
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    return Objects.isNull(left) ? right : Objects.isNull(right) ? left : root;
  }
}
