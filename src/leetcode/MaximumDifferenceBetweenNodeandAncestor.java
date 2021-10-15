package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumDifferenceBetweenNodeandAncestor {
    /*
    Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
    A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.

    Example 1:
    Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
    Output: 7
    Explanation: We have various ancestor-node differences, some of which are given below :
    |8 - 3| = 5
    |3 - 7| = 4
    |8 - 1| = 7
    |10 - 13| = 3
    Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.

    Example 2:
    Input: root = [1,null,2,null,0,3]
    Output: 3

    Constraints:
    The number of nodes in the tree is in the range [2, 5000].
    0 <= Node.val <= 10^5
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {7, TreeNode.tree("8,3,1,n,n,6,4,n,n,7,n,n,10,n,14,13,n,n,n")},
                {3, TreeNode.tree("1,n,2,n,0,3,n,n,n")}
        });
    }

    public int maxAncestorDiff(TreeNode root) {
        return maxAncestorDiff(root, root.val, root.val);
    }

    int maxAncestorDiff(TreeNode root, int min, int max) {
        return root == null ? max - min : Math.max(maxAncestorDiff(root.left, min = Math.min(min, root.val), max = Math.max(max, root.val)), maxAncestorDiff(root.right, min, max));
    }
}
