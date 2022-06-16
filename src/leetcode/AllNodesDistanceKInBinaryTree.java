package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.*;

/**
 * @author Wit
 */
public class AllNodesDistanceKInBinaryTree {
    /*
    Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.
    You can return the answer in any order.

    Example 1:
    Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
    Output: [7,4,1]
    Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.

    Example 2:
    Input: root = [1], target = 1, k = 3
    Output: []

    Constraints:
    The number of nodes in the tree is in the range [1, 500].
    0 <= Node.val <= 500
    All the values Node.val are unique.
    target is the value of one of the nodes in the tree.
    0 <= k <= 1000
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of(7, 4, 1), TreeNode.treeLevel("3,5,1,6,2,0,8,null,null,7,4"), TreeNode.treeLevel("5"), 2},
                {List.of(), TreeNode.treeLevel("1"), TreeNode.treeLevel("1"), 3}
        });
    }

    TreeNode target;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>(500);
        Map<Integer, TreeNode> parentMap = new HashMap<>(1000);
        find(target.val, root, this.target = null, parentMap);
        findChildrenWithDistanceK(k, this.target, result);
        if (k > 0) findParentWithDistanceK(k, this.target, parentMap, result);
        return result;
    }

    boolean find(int val, TreeNode node, TreeNode parent, Map<Integer, TreeNode> parentMap) {
        if (node == null) return false;
        parentMap.put(node.val, parent);
        if (node.val == val) {
            target = node;
            return true;
        }
        return find(val, node.left, node, parentMap) || find(val, node.right, node, parentMap);
    }

    void findChildrenWithDistanceK(int k, TreeNode node, List<Integer> list) {
        if (node == null) return;
        if (k-- == 0) {
            list.add(node.val);
            return;
        }
        findChildrenWithDistanceK(k, node.left, list);
        findChildrenWithDistanceK(k, node.right, list);
    }

    void findParentWithDistanceK(int k, TreeNode node, Map<Integer, TreeNode> parentMap, List<Integer> list) {
        if (k-- == 0) {
            list.add(node.val);
            return;
        }
        TreeNode parent = parentMap.get(node.val);
        if (parent == null) return;
        findParentWithDistanceK(k, parent, parentMap, list);
        if (k > 0) findChildrenWithDistanceK(--k, parent.left == node ? parent.right : parent.left, list);
    }

}
