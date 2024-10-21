package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import static leetcode.common.TreeNode.treeLevel;

public class KthLargestPerfectSubtreeSizeInBinaryTree {
    /*
    You are given the root of a binary tree and an integer k.
    Return an integer denoting the size of the kth-largest perfect binary subtree, or -1 if it doesn't exist.
    A perfect binary tree is a tree where all leaves are on the same level, and every parent has two children.

    Example 1:
    Input: root = [5,3,6,5,2,5,7,1,8,null,null,6,8], k = 2
    Output: 3
    Explanation:
    The roots of the perfect binary subtrees are highlighted in black. Their sizes, in non-increasing order are [3, 3, 1, 1, 1, 1, 1, 1].
    The 2nd largest size is 3.

    Example 2:
    Input: root = [1,2,3,4,5,6,7], k = 1
    Output: 7
    Explanation:
    The sizes of the perfect binary subtrees in non-increasing order are [7, 3, 3, 1, 1, 1, 1]. The size of the largest perfect binary subtree is 7.

    Example 3:
    Input: root = [1,2,3,null,4], k = 3
    Output: -1
    Explanation:
    The sizes of the perfect binary subtrees in non-increasing order are [1, 1]. There are fewer than 3 perfect binary subtrees.

    Constraints:
        The number of nodes in the tree is in the range [1, 2000].
        1 <= Node.val <= 2000
        1 <= k <= 1024
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {3, treeLevel("5,3,6,5,2,5,7,1,8,null,null,6,8"), 2},
                {7, treeLevel("1,2,3,4,5,6,7"), 1},
                {-1, treeLevel("1,2,3,null,4"), 3},
        });
    }

    static final int MAX_NODES = 2_000, PERFECT_DEPTH_END = 32 - Integer.numberOfLeadingZeros(MAX_NODES + 1);
    int[] prefectDepthCount;

    int perfectDepth(TreeNode node) {
        if (node == null) return 0;
        int prefectDepthLeft = perfectDepth(node.left), perfectDepthRight = perfectDepth(node.right);
        if (prefectDepthLeft == perfectDepthRight && ++prefectDepthLeft > 0) {
            prefectDepthCount[prefectDepthLeft]++;
            return prefectDepthLeft;
        } else return -1;
    }

    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        prefectDepthCount = new int[PERFECT_DEPTH_END];
        perfectDepth(root);
        int d = PERFECT_DEPTH_END;
        while (--d > 0 && (k -= prefectDepthCount[d]) > 0) {}
        return d > 0 ? (1 << d) - 1 : -1;
    }
}
