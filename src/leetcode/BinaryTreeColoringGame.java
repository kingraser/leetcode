package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class BinaryTreeColoringGame {
    /*
    Two players play a turn based game on a binary tree. We are given the root of this binary tree, and the number of nodes n in the tree. n is odd, and each node has a distinct value from 1 to n.
    Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y with 1 <= y <= n and y != x. The first player colors the node with value x red, and the second player colors the node with value y blue.
    Then, the players take turns starting with the first player. In each turn, that player chooses a node of their color (red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)
    If (and only if) a player cannot choose such a node in this way, they must pass their turn. If both players pass their turn, the game ends, and the winner is the player that colored more nodes.
    You are the second player. If it is possible to choose such a y to ensure you win the game, return true. If it is not possible, return false.

    Example 1:
    Input: root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
    Output: true
    Explanation: The second player can choose the node with value 2.

    Example 2:
    Input: root = [1,2,3], n = 3, x = 1
    Output: false

    Constraints:
    The number of nodes in the tree is n.
    1 <= x <= n <= 100
    n is odd.
    1 <= Node.val <= n
    All the values of the tree are unique.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, TreeNode.tree("1,2,4,8,n,n,9,n,n,5,10,n,n,11,n,n,3,6,n,n,7,n,n"), 11, 3},
                {false, TreeNode.tree("1,2,n,n,3,n,n"), 3, 1}
        });
    }

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        int half = n >> 1, children[] = new int[2];
        travel(root, x, children);
        return children[0] > half || children[1] > half || children[0] + children[1] + 1 <= half;
    }

    void travel(TreeNode root, int x, int[] children) {
        if (null == root || children[0] != 0) return;
        if (root.val != x) {
            travel(root.left, x, children);
            travel(root.right, x, children);
            return;
        }
        children[0] = count(root.left);
        children[1] = count(root.right);
    }

    int count(TreeNode node) {
        return node == null ? 0 : 1 + count(node.left) + count(node.right);
    }
}
