package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.BitSet;

/**
 * @author Wit
 */
public class FindElementsinaContaminatedBinaryTree {
    /*
    Given a binary tree with the following rules:
    root.val == 0
    If treeNode.val == x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
    If treeNode.val == x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
    Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.
    Implement the FindElements class:
    FindElements(TreeNode* root) Initializes the object with a contaminated binary tree and recovers it.
    bool find(int target) Returns true if the target value exists in the recovered binary tree.

    Example 1:
    Input
    ["FindElements","find","find"]
    [[[-1,null,-1]],[1],[2]]
    Output
    [null,false,true]
    Explanation
    FindElements findElements = new FindElements([-1,null,-1]);
    findElements.find(1); // return False
    findElements.find(2); // return True

    Example 2:
    Input
    ["FindElements","find","find","find"]
    [[[-1,-1,-1,-1,-1]],[1],[3],[5]]
    Output
    [null,true,true,false]
    Explanation
    FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
    findElements.find(1); // return True
    findElements.find(3); // return True
    findElements.find(5); // return False

    Example 3:
    Input
    ["FindElements","find","find","find","find"]
    [[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
    Output
    [null,true,false,false,true]
    Explanation
    FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
    findElements.find(2); // return True
    findElements.find(3); // return False
    findElements.find(4); // return False
    findElements.find(5); // return True

    Constraints:
    TreeNode.val == -1
    The height of the binary tree is less than or equal to 20
    The total number of nodes is between [1, 10^4]
    Total calls of find() is between [1, 10^4]
    0 <= target <= 10^6
    */

    @Test
    public void test() {
        TestUtil.testEquals(new FindElements(TreeNode.tree("-1,n,-1,n,n")),
                new Object[][]{
                        {false, 1},
                        {true, 2}
                });
        TestUtil.testEquals(new FindElements(TreeNode.tree("-1,-1,-1,n,n,-1,n,n,-1,n,n")),
                new Object[][]{
                        {true, 1},
                        {true, 3},
                        {false, 5}
                });
        TestUtil.testEquals(new FindElements(TreeNode.tree("-1,n,-1,-1,-1,n,n,n,n")),
                new Object[][]{
                        {true, 2},
                        {false, 3},
                        {false, 4},
                        {true, 5}
                });
    }

    public class FindElements {
        BitSet set = new BitSet(1_000_001);

        public FindElements(TreeNode root) {recover(root, 0);}

        void recover(TreeNode root, int val) {
            if (root == null) return;
            set.set(root.val = val++);
            recover(root.right, val <<= 1);
            recover(root.left, --val);
        }

        public boolean find(int target) {return set.get(target);}
    }
}
