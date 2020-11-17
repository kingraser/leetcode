package leetcode;

import leetcode.common.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Wit
 */
public class LeafSimilarTrees {
    /*
    Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
            3
           / \
          5   1
         / \ / \
        6  2 9  8
          / \
         7   4
    For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
    Two binary trees are considered leaf-similar if their leaf value sequence is the same.
    Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

    Example 1:
            3
           / \
          5   1
         / \ / \
        6  2 9  8
          / \
         7   4

            3
           / \
          5   1
         / \ / \
        6  7 4  2
               / \
              9   8
    Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
    Output: true

    Example 2:
    Input: root1 = [1], root2 = [1]
    Output: true

    Example 3:
    Input: root1 = [1], root2 = [2]
    Output: false

    Example 4:
    Input: root1 = [1,2], root2 = [2,2]
    Output: true

    Example 5:
        1
       / \
      2   3

        1
       / \
      3   2
    Input: root1 = [1,2,3], root2 = [1,3,2]
    Output: false

    Constraints:
    The number of nodes in each tree will be in the range [1, 200].
    Both of the given trees will have values in the range [0, 200].
    */

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        LinkedList<Integer> list = new LinkedList<>();
        getLeaves(root1, list);
        return leafSimilar(list, root2) && list.isEmpty();
    }

    public void getLeaves(TreeNode root, List<Integer> list) {
        if (Objects.isNull(root)) return;
        if (isLeaf(root)) list.add(root.val);
        else {
            getLeaves(root.left, list);
            getLeaves(root.right, list);
        }
    }

    public boolean leafSimilar(LinkedList<Integer> leaves, TreeNode root) {
        return Objects.isNull(root) || ((isLeaf(root)) ? Objects.equals(root.val, leaves.pollFirst()) : leafSimilar(leaves, root.left) && leafSimilar(leaves, root.right));
    }

    boolean isLeaf(TreeNode node) {
        return Objects.isNull(node.left) && Objects.isNull(node.right);
    }

    @Test
    public void test() {
        TreeNode one = TreeNode.tree("1,n,n");
        Assert.assertTrue(leafSimilar(TreeNode.tree("3,5,6,n,n,2,7,n,n,4,n,n,1,9,n,n,8,n,n"), TreeNode.tree("3,5,6,n,n,7,n,n,1,4,n,n,2,9,n,n,8,n,n")));
        Assert.assertTrue(leafSimilar(one, one));
        Assert.assertFalse(leafSimilar(one, TreeNode.tree("2,n,n")));
        Assert.assertTrue(leafSimilar(TreeNode.tree("1,2,n,n,n"), TreeNode.tree("2,2,n,n,n")));
        Assert.assertFalse(leafSimilar(TreeNode.tree("1,2,n,n,3,n,n"), TreeNode.tree("1,3,n,n,2,n,n")));
    }
}
