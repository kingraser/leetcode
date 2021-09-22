package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * @author Wit
 */
public class MaximumLevelSumofaBinaryTree {
    /*
    Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
    Return the smallest level x such that the sum of all the values of nodes at level x is maximal.

    Example 1:
    Input: root = [1,7,0,7,-8,null,null]
    Output: 2
    Explanation:
    Level 1 sum = 1.
    Level 2 sum = 7 + 0 = 7.
    Level 3 sum = 7 + -8 = -1.
    So we return the level with the maximum sum which is level 2.

    Example 2:
    Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
    Output: 2

    Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    -105 <= Node.val <= 10^5
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, TreeNode.tree("1,7,7,n,n,-8,n,n,0,n,n")},
                {2, TreeNode.tree("989,n,10250,98693,n,n,-89388,n,-32127,n,n")},
        });
    }

    public int maxLevelSum(TreeNode root) {
        List<Integer> list = new ArrayList<>(100);
        travelTree(root, list, 0);
        return 1 + IntStream.range(0, list.size()).reduce(0, (i, j) -> Objects.equals(list.get(i), list.get(j)) ? Math.min(i, j) : list.get(i) < list.get(j) ? j : i);
    }

    private void travelTree(TreeNode root, List<Integer> list, int level) {
        if (root == null) return;
        if (level == list.size()) list.add(root.val);
        else list.set(level, list.get(level) + root.val);
        travelTree(root.left, list, ++level);
        travelTree(root.right, list, level);
    }
}
