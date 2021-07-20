package leetcode;

import leetcode.common.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Wit
 */
public class CousinsinBinaryTree {
    /*
    In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
    Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
    We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
    Return true if and only if the nodes corresponding to the values x and y are cousins.

    Example 1:
    Input: root = [1,2,3,4], x = 4, y = 3
    Output: false

    Example 2:
    Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
    Output: true

    Example 3:
    Input: root = [1,2,3,null,4], x = 2, y = 3
    Output: false

    Constraints:
    The number of nodes in the tree will be between 2 and 100.
    Each node has a unique integer value from 1 to 100.
    */

    @Test
    public void test() {
        Assert.assertTrue(isCousins(TreeNode.tree("1,2,n,4,n,n,3,n,5,n,n"), 5, 4));
        Assert.assertFalse(isCousins(TreeNode.tree("1,2,n,4,n,n,3,n,n"), 4, 3));
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode[]> queue = new LinkedList<>() {{offer(new TreeNode[]{root, null});}};
        for (TreeNode xParent = null, yParent = null, node, nodes[]; xParent == null && yParent == null && !queue.isEmpty(); )
            for (int i = 0, size = queue.size(), val; i++ < size; ) {
                if ((node = (nodes = queue.poll())[0]) == null) continue;
                if ((val = node.val) == x) xParent = nodes[1];
                else if (val == y) yParent = nodes[1];
                if (xParent != null && yParent != null) return xParent != yParent;
                queue.offer(new TreeNode[]{node.left, node});
                queue.offer(new TreeNode[]{node.right, node});
            }
        return false;
    }
}
