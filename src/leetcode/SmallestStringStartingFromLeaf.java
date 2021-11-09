package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SmallestStringStartingFromLeaf {
    /*
    You are given the root of a binary tree where each node has a value in the range [0, 25] representing the letters 'a' to 'z'.
    Return the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
    As a reminder, any shorter prefix of a string is lexicographically smaller.
    For example, "ab" is lexicographically smaller than "aba".
    A leaf of a node is a node that has no children.

    Example 1:
    Input: root = [0,1,2,3,4,3,4]
    Output: "dba"

    Example 2:
    Input: root = [25,1,3,1,3,0,2]
    Output: "adz"

    Example 3:
    Input: root = [2,2,1,null,1,0,null,0]
    Output: "abc"

    Constraints:
    The number of nodes in the tree is in the range [1, 8500].
    0 <= Node.val <= 25
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"dba", TreeNode.tree("0,1,3,n,n,4,n,n,2,3,n,n,4,n,n")},
                {"adz", TreeNode.tree("25,1,1,n,n,3,n,n,3,0,n,n,2,n,n")},
                {"abc", TreeNode.tree("2,2,n,1,0,n,n,n,1,0,n,n,n")},
        });
    }

    int nodeCountLimit = 8500;

    public String smallestFromLeaf(TreeNode root) {
        Word result = new Word(nodeCountLimit);
        dfs(root, result, new Word(result));
        return result.toString();
    }

    private boolean dfs(TreeNode root, Word res, Word cur) {
        if (root == null) return true;
        cur.add(root.val);
        if ((dfs(root.left, res, cur) & dfs(root.right, res, cur)) && cur.compare(res) < 0) res.set(cur);
        cur.poll();
        return false;
    }

    public static class Word {
        public int text[], start;

        public Word(int length) {text = new int[start = length];}

        public Word(Word other) {
            this(other.text.length);
            set(other);
        }

        public int size() {return text.length - start;}

        public void add(int val) {text[--start] = 'a' + val;}

        public void poll() {++start;}

        public void set(Word other) {
            if (text.length < other.text.length) text = new int[other.text.length];
            for (start = text.length; start > other.start; ) text[--start] = other.text[start];
        }

        public int compare(Word other) {
            if (other.size() == 0) return -1;
            for (int i1 = start, i2 = other.start, c1, c2, size = Math.min(size(), other.size()); size-- > 0; )
                if ((c1 = text[i1++]) > (c2 = other.text[i2++])) return 1;
                else if (c1 < c2) return -1;
            return Integer.compare(size(), other.size());
        }

        public String toString() {return new String(text, start, size());}
    }
}
