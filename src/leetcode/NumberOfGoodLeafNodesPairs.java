package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class NumberOfGoodLeafNodesPairs {
    /*
    You are given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.
    Return the number of good leaf node pairs in the tree.

    Example 1:
    Input: root = [1,2,3,null,4], distance = 3
    Output: 1
    Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.

    Example 2:
    Input: root = [1,2,3,4,5,6,7], distance = 3
    Output: 2
    Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.

    Example 3:
    Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
    Output: 1
    Explanation: The only good pair is [2,5].

    Constraints:
    The number of nodes in the tree is in the range [1, 2^10].
    1 <= Node.val <= 100
    1 <= distance <= 10
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1, TreeNode.treeLevel("1,2,3,null,4"), 3},
                {2, TreeNode.treeLevel("1,2,3,4,5,6,7"), 3},
                {1, TreeNode.treeLevel("7,1,4,6,null,5,3,null,null,null,null,null,2"), 3}
        });
    }

    public int countPairs(TreeNode root, int distance) {
        int[] result = new int[1];
        if (distance > 1) dfs(root, distance, result);
        return result[0];
    }

    int[] dfs(TreeNode root, int distance, int[] result) {
        if (root == null) return new int[distance - 1];
        if (root.left == null && root.right == null) {
            int res[] = new int[distance - 1];
            res[0]++;
            return res;
        }
        int[] left = dfs(root.left, distance, result), right = dfs(root.right, distance, result);
        for (int leftIdx = 0; leftIdx < left.length; leftIdx++)
            if (left[leftIdx] > 0) for (int rightIdx = distance - 2 - leftIdx; rightIdx >= 0; rightIdx--)
                if (right[rightIdx] > 0) result[0] += left[leftIdx] * right[rightIdx];
        int res[] = new int[distance - 1];
        for (int i = 1; i < res.length; i++) res[i] = left[i - 1] + right[i - 1];
        return res;
    }
}
