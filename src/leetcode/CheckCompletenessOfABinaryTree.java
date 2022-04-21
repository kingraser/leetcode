package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;

/**
 * @author Wit
 */
public class CheckCompletenessOfABinaryTree {
    /*
    Given the root of a binary tree, determine if it is a complete binary tree.
    In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

    Example 1:
    Input: root = [1,2,3,4,5,6]
    Output: true
    Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.

    Example 2:
    Input: root = [1,2,3,4,5,null,7]
    Output: false
    Explanation: The node with value 7 isn't as far left as possible.

    Constraints:
    The number of nodes in the tree is in the range [1, 100].
    1 <= Node.val <= 1000
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, TreeNode.tree("1,2,4,n,n,5,n,n,3,6,n,n,n")},
                {false, TreeNode.tree("1,2,4,n,n,5,n,n,3,n,7,n,n")}
        });
    }

    // state: 0 for only accept leaf, 1 for accept other legal node
    public boolean isCompleteTree(TreeNode root) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>(100) {{add(root);}};
        for (int maxSize = 1, size = 1, state = 1; !queue.isEmpty(); state = (size = queue.size()) == (maxSize <<= 1) ? 1 : 0)
            for (TreeNode node; size-- > 0; )
                if ((node = queue.poll()).left == null) {
                    if (node.right != null) return false;
                    state = 0;
                } else if (state == 1) {
                    queue.add(node.left);
                    if (node.right == null) state = 0;
                    else queue.add(node.right);
                } else return false;
        return true;
    }
}
