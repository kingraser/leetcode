package leetcode;

import leetcode.common.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @author Wit
 */
public class CompleteBinaryTreeInserter {
    /*
    A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.
    Design an algorithm to insert a new node to a complete binary tree keeping it complete after the insertion.
    Implement the CBTInserter class:
    CBTInserter(TreeNode root) Initializes the data structure with the root of the complete binary tree.
    int insert(int v) Inserts a TreeNode into the tree with value Node.val == val so that the tree remains complete, and returns the value of the parent of the inserted TreeNode.
    TreeNode get_root() Returns the root node of the tree.

    Example 1:
    Input
    ["CBTInserter", "insert", "insert", "get_root"]
    [[[1, 2]], [3], [4], []]
    Output
    [null, 1, 2, [1, 2, 3, 4]]
    Explanation
    CBTInserter cBTInserter = new CBTInserter([1, 2]);
    cBTInserter.insert(3);  // return 1
    cBTInserter.insert(4);  // return 2
    cBTInserter.get_root(); // return [1, 2, 3, 4]

    Constraints:
    The number of nodes in the tree will be in the range [1, 1000].
    0 <= Node.val <= 5000
    root is a complete binary tree.
    0 <= val <= 5000
    At most 104 calls will be made to insert and get_root.
    */
    @Test
    public void test() {
        CBTInserter instance = new CBTInserter(TreeNode.tree("1,2,n,n,n"));
        Assert.assertEquals(1, instance.insert(3));
        Assert.assertEquals(2, instance.insert(4));
        Assert.assertEquals(TreeNode.tree("1,2,4,n,n,n,3,n,n"), instance.get_root());
    }

    public static class CBTInserter {
        TreeNode root, node;
        LinkedList<TreeNode> nodes = new LinkedList<>();

        public CBTInserter(TreeNode root) {for (nodes.add(this.root = root); !nodes.isEmpty() && (node = nodes.peek()).left != null && node.right != null; nodes.add(node.left), nodes.add(node.right)) nodes.poll();}

        public int insert(int val) {
            if ((node = nodes.peek()).left == null) node.left = new TreeNode(val);
            else {
                nodes.poll();
                nodes.add(node.left);
                nodes.add(node.right = new TreeNode(val));
            }
            return node.val;
        }

        public TreeNode get_root() {return this.root;}
    }
}
