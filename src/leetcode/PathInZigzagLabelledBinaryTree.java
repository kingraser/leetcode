package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Wit
 */
public class PathInZigzagLabelledBinaryTree {
    /*
    In an infinite binary tree where every node has two children, the nodes are labelled in row order.
    In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.
    Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.

    Example 1:
    Input: label = 14
    Output: [1,3,4,14]

    Example 2:
    Input: label = 26
    Output: [1,2,6,10,26]

    Constraints:
    1 <= label <= 10^6
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of(1, 3, 4, 14), 14},
                {List.of(1, 2, 6, 10, 26), 26},
        });
    }

    public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> result = new LinkedList<>() {{add(label);}};
        for (int level = getLevel(label), orderFromLeft = Math.abs(label - getLeftMostLabel(level)); --level >= 0; )
            result.addFirst(getLeftMostLabel(level) + ((level & 1) == 0 ? 1 : -1) * (orderFromLeft >>= 1));
        return result;
    }

    private int getMaxInLevel(int level) {
        return (1 << ++level) - 1;
    }

    private int getLevel(int label) {
        for (int level = 0; ; ) if (getMaxInLevel(level++) >= label) return --level;
    }

    private int getLeftMostLabel(int level) {
        return (level & 1) == 0 ? 1 << level : getMaxInLevel(level);
    }
}
