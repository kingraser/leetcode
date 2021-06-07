package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class DeepestLeavesSum {
    /*
    Given the root of a binary tree, return the sum of values of its deepest leaves.

    Example 1:
                        1
                       / \
                      2   3
                     / \   \
                    4   5   6
                   /         \
                  7           8
    Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
    Output: 15

    Example 2:
                        6
                       / \
                      7    8
                     / \  / \
                    2  7  1  3
                   /  / \     \
                  9  1   4     5
    Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
    Output: 19

    Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    1 <= Node.val <= 100
    */
    public int deepestLeavesSum(TreeNode root) {
        int[] result = new int[1];
        deepestLeavesSum(root, new int[1], result, 1);
        return result[0];
    }

    void deepestLeavesSum(TreeNode root, int[] maxDepth, int[] sumOfDeepestLeaves, int depth) {
        if (root.left != null) deepestLeavesSum(root.left, maxDepth, sumOfDeepestLeaves, depth + 1);
        if (root.right != null) deepestLeavesSum(root.right, maxDepth, sumOfDeepestLeaves, depth + 1);
        if (!root.isLeaf()) return;
        if (maxDepth[0] == depth) sumOfDeepestLeaves[0] += root.val;
        else if (depth > maxDepth[0]) {
            sumOfDeepestLeaves[0] = root.val;
            maxDepth[0] = depth;
        }
    }

    @Test
    public void test() throws NoSuchMethodException {
        TestUtil.testEquals(this, this.getClass().getMethod("deepestLeavesSum", TreeNode.class), new Object[][]{
                {15, TreeNode.tree("1,2,4,7,n,n,n,5,n,n,3,n,6,n,8,n,n")},
                {19, TreeNode.tree("6,7,2,9,n,n,n,7,1,n,n,4,n,n,8,1,n,n,3,n,5,n,n")}
        });
    }
}
