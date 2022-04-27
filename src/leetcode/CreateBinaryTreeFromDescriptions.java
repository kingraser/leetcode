package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Wit
 */
public class CreateBinaryTreeFromDescriptions {
    /*
    You are given a 2D integer array descriptions where descriptions[i] = [parent-i, child-i, isLeft-i] indicates that parent-i is the parent of child-i in a binary tree of unique values. Furthermore,
    If isLeft-i == 1, then child-i is the left child of parent-i.
    If isLeft-i == 0, then child-i is the right child of parent-i.
    Construct the binary tree described by descriptions and return its root.
    The test cases will be generated such that the binary tree is valid.

    Example 1:
    Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
    Output: [50,20,80,15,17,19]
    Explanation: The root node is the node with value 50 since it has no parent.
    The resulting binary tree is shown in the diagram.

    Example 2:
    Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
    Output: [1,2,null,null,3,4]
    Explanation: The root node is the node with value 1 since it has no parent.
    The resulting binary tree is shown in the diagram.

    Constraints:
    1 <= descriptions.length <= 10^4
    descriptions[i].length == 3
    1 <= parent-i, child-i <= 10^5
    0 <= isLeft-i <= 1
    The binary tree described by descriptions is valid.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {TreeNode.treeLevel("50,20,80,15,17,19"), new int[][]{{20, 15, 1}, {20, 17, 0}, {50, 20, 1}, {50, 80, 0}, {80, 19, 1}}},
                {TreeNode.treeLevel("1,2,null,null,3,4"), new int[][]{{1, 2, 1}, {2, 3, 0}, {3, 4, 1}}}
        });
    }

    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>(descriptions.length << 1);
        Set<Integer> children = new HashSet<>(descriptions.length << 1);
        for (int[] description : descriptions) {
            TreeNode parent = map.computeIfAbsent(description[0], TreeNode::new), child = map.computeIfAbsent(description[1], TreeNode::new);
            if (description[2] == 1) parent.left = child;
            else parent.right = child;
            children.add(description[1]);
        }
        for (Map.Entry<Integer, TreeNode> e : map.entrySet()) if (!children.contains(e.getKey())) return e.getValue();
        return null;
    }
}
