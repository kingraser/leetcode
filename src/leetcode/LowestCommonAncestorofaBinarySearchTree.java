package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import static leetcode.common.TreeNode.tree;

public class LowestCommonAncestorofaBinarySearchTree {

  /*
       6
      / \
     2   8
    / \ / \
   0  4 7  9
     / \
    3   5
  For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. 
  Another example is LCA of nodes 2 and 4 is 2
  */

    @Test
    public void test() {
        TreeNode four = tree("4,3,n,n,5,n,n"),
                two = new TreeNode(2, new TreeNode(0), four),
                eight = tree("8,7,n,n,9,n,n"),
                six = new TreeNode(6, two, eight);
        TestUtil.testEquals(new Object[][]{
                {six, six, two, eight},
                {two, six, two, four}
        });
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }
}
