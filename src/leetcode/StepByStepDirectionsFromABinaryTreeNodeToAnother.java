package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class StepByStepDirectionsFromABinaryTreeNodeToAnother {
    /*
    You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
    Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
    'L' means to go from a node to its left child node.
    'R' means to go from a node to its right child node.
    'U' means to go from a node to its parent node.
    Return the step-by-step directions of the shortest path from node s to node t.

    Example 1:
    Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
    Output: "UURL"
    Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.

    Example 2:
    Input: root = [2,1], startValue = 2, destValue = 1
    Output: "L"
    Explanation: The shortest path is: 2 → 1.

    Constraints:
    The number of nodes in the tree is n.
    2 <= n <= 105
    1 <= Node.val <= n
    All the values in the tree are unique.
    1 <= startValue, destValue <= n
    startValue != destValue
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"UURL", TreeNode.tree("5,1,3,n,n,n,2,6,n,n,4,n,n"), 3, 6},
                {"L", TreeNode.tree("2,1,n,n,n"), 2, 1}
        });
    }

    boolean find(TreeNode root, int val, StringBuilder sb) {
        if (root.val == val) return true;
        if (root.left != null && find(root.left, val, sb)) sb.append('L');
        else if (root.right != null && find(root.right, val, sb)) sb.append('R');
        return sb.length() > 0;
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder s = new StringBuilder(), d = new StringBuilder();
        find(root, startValue, s);
        find(root, destValue, d);
        int rootToLowestCommonAncestor = 0;
        for (int sl = s.length(), dl = d.length(), size = Math.min(sl, dl), sLast = sl - 1, dLast = dl - 1; rootToLowestCommonAncestor < size && s.charAt(sLast--) == d.charAt(dLast--); rootToLowestCommonAncestor++) {}
        return "U".repeat(s.length() - rootToLowestCommonAncestor) + d.reverse().substring(rootToLowestCommonAncestor);
    }
}
