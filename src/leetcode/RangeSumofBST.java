package leetcode;

import leetcode.common.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * @author Wit
 */
public class RangeSumofBST {
    /*
    Given the root node of a binary search tree, return the sum of values of all nodes with a value in the range [low, high].

    Example 1:
            10
           /  \
          5    15
         / \     \
        3   7     18
    Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
    Output: 32

    Example 2:
            10
           /  \
          5    15
         / \   / \
        3   7 13  18
       /   /
      1   6
    Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
    Output: 23

    Constraints:
    The number of nodes in the tree is in the range [1, 2 * 10^4].
    1 <= Node.val <= 10^5
    1 <= low <= high <= 10^5
    All Node.val are unique.
    */

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (Objects.isNull(root)) return 0;
        int sum = 0;
        if (root.val > high) sum += rangeSumBST(root.left, low, high);
        else if (root.val < low) sum += rangeSumBST(root.right, low, high);
        else sum += root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        return sum;
    }

    @Test
    public void test() {
        Assert.assertEquals(32, rangeSumBST(TreeNode.tree("10,5,3,n,n,7,n,n,15,n,18,n,n"), 7, 15));
        Assert.assertEquals(23, rangeSumBST(TreeNode.tree("10,5,3,1,n,n,n,7,6,n,n,n,15,13,n,n,18,n,n"), 6, 10));
    }
}
