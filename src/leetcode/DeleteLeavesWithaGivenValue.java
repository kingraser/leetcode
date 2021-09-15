package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Objects;

/**
 * @author Wit
 */
public class DeleteLeavesWithaGivenValue {
    /*
    Given a binary tree root and an integer target, delete all the leaf nodes with value target.
    Note that once you delete a leaf node with value target, if it's parent node becomes a leaf node and has the value target, it should also be deleted (you need to continue doing that until you can't).

    Example 1:
    Input: root = [1,2,3,2,null,2,4], target = 2
    Output: [1,null,3,null,4]
    Explanation: Leaf nodes in green with value (target = 2) are removed (Picture in left).
    After removing, new nodes become leaf nodes with value (target = 2) (Picture in center).

    Example 2:
    Input: root = [1,3,3,3,2], target = 3
    Output: [1,3,null,null,2]

    Example 3:
    Input: root = [1,2,null,2,null,2], target = 2
    Output: [1]
    Explanation: Leaf nodes in green with value (target = 2) are removed at each step.

    Example 4:
    Input: root = [1,1,1], target = 1
    Output: []

    Example 5:
    Input: root = [1,2,3], target = 1
    Output: [1,2,3]

    Constraints:
    1 <= target <= 1000
    The given binary tree will have between 1 and 3000 nodes.
    Each node's value is between [1, 1000].
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {TreeNode.tree("1,n,3,n,4,n,n"), TreeNode.tree("1,2,2,n,n,n,3,2,n,n,4,n,n"), 2},
                {TreeNode.tree("1,3,n,2,n,n,n"), TreeNode.tree("1,3,3,n,n,2,n,n,3,n,n"), 3},
                {TreeNode.tree("1,n,n"), TreeNode.tree("1,2,2,2,n,n,n,n,n"), 2},
                {TreeNode.tree("n"), TreeNode.tree("1,1,n,n,1,n,n"), 1},
                {TreeNode.tree("1,2,n,n,3,n,n"), TreeNode.tree("1,2,n,n,3,n,n"), 1},
        });
    }

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root.left != null) root.left = removeLeafNodes(root.left, target);
        if (root.right != null) root.right = removeLeafNodes(root.right, target);
        return root.val == target && isLeaf(root) ? null : root;
    }

    public boolean isLeaf(TreeNode node) {
        return Objects.isNull(node.left) && Objects.isNull(node.right);
    }
}
