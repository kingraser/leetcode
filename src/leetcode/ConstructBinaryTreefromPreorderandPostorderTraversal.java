package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ConstructBinaryTreefromPreorderandPostorderTraversal {
    /*
    Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.
    If there exist multiple answers, you can return any of them.

    Example 1:
    Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
    Output: [1,2,3,4,5,6,7]

    Example 2:
    Input: preorder = [1], postorder = [1]
    Output: [1]

    Constraints:
    1 <= preorder.length <= 30
    1 <= preorder[i] <= preorder.length
    All the values of preorder are unique.
    postorder.length == preorder.length
    1 <= postorder[i] <= postorder.length
    All the values of postorder are unique.
    It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same binary tree.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {TreeNode.tree("1,2,4,n,n,5,n,n,3,6,n,n,7,n,n"), new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 5, 2, 6, 7, 3, 1}},
                {TreeNode.tree("1,n,n"), new int[]{1}, new int[]{1}}
        });
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return constructFromPrePost(pre, post, new int[1], new int[1]);
    }

    TreeNode constructFromPrePost(int[] pre, int[] post, int[] preIdx, int[] postIdx) {
        TreeNode root = new TreeNode(pre[preIdx[0]++]);
        if (root.val != post[postIdx[0]]) root.left = constructFromPrePost(pre, post, preIdx, postIdx);
        if (root.val != post[postIdx[0]]) root.right = constructFromPrePost(pre, post, preIdx, postIdx);
        postIdx[0]++;
        return root;
    }
}
