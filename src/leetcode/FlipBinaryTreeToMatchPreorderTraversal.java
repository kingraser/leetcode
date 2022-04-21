package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class FlipBinaryTreeToMatchPreorderTraversal {

    /*
    You are given the root of a binary tree with n nodes, where each node is uniquely assigned a value from 1 to n. You are also given a sequence of n values voyage, which is the desired pre-order traversal of the binary tree.
    Any node in the binary tree can be flipped by swapping its left and right subtrees. For example, flipping node 1 will have the following effect:
    Flip the smallest number of nodes so that the pre-order traversal of the tree matches voyage.
    Return a list of the values of all flipped nodes. You may return the answer in any order. If it is impossible to flip the nodes in the tree to make the pre-order traversal match voyage, return the list [-1].

    Example 1:
    Input: root = [1,2], voyage = [2,1]
    Output: [-1]
    Explanation: It is impossible to flip the nodes such that the pre-order traversal matches voyage.

    Example 2:
    Input: root = [1,2,3], voyage = [1,3,2]
    Output: [1]
    Explanation: Flipping node 1 swaps nodes 2 and 3, so the pre-order traversal matches voyage.

    Example 3:
    Input: root = [1,2,3], voyage = [1,2,3]
    Output: []
    Explanation: The tree's pre-order traversal already matches voyage, so no nodes need to be flipped.

    Constraints:
    The number of nodes in the tree is n.
    n == voyage.length
    1 <= n <= 100
    1 <= Node.val, voyage[i] <= n
    All the values in the tree are unique.
    All the values in voyage are unique.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of(-1), TreeNode.tree("1,2,n,n,n"), new int[]{2, 1}},
                {List.of(1), TreeNode.tree("1,2,n,n,3,n,n"), new int[]{1, 3, 2}},
                {List.of(), TreeNode.tree("1,2,n,n,3,n,n"), new int[]{1, 2, 3}},
        });
    }

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>(voyage.length);
        ArrayDeque<TreeNode> stack = new ArrayDeque<>(voyage.length) {{push(root);}};
        TreeNode node;
        for (int i = 0; !stack.isEmpty(); ) {
            if ((node = stack.pop()).val != voyage[i++]) return List.of(-1);
            if (node.right != null) {
                if (node.right.val == voyage[i] && node.left != null) {
                    res.add(node.val);
                    stack.push(node.left);
                    stack.push(node.right);
                    continue;
                }
                stack.push(node.right);
            }
            if (node.left != null) stack.push(node.left);
        }
        return res;
    }
}
