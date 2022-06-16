package leetcode;

import leetcode.common.ListNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class NextGreaterNodeInLinkedList {
    /*
    You are given the head of a linked list with n nodes.
    For each node in the list, find the value of the next greater node. That is, for each node, find the value of the first node that is next to it and has a strictly larger value than it.
    Return an integer array answer where answer[i] is the value of the next greater node of the ith node (1-indexed). If the ith node does not have a next greater node, set answer[i] = 0.

    Example 1:
    Input: head = [2,1,5]
    Output: [5,5,0]

    Example 2:
    Input: head = [2,7,4,3,5]
    Output: [7,0,5,5,0]

    Constraints:
    The number of nodes in the list is n.
    1 <= n <= 10^4
    1 <= Node.val <= 10^9
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                //{new int[]{5, 5, 0}, ListNode.list(2, 1, 5)},
                {new int[]{7, 0, 5, 5, 0}, ListNode.list(2, 7, 4, 3, 5)}
        });
    }

    public int[] nextLargerNodes(ListNode head) {
        int result[] = new int[10_000], size = 0, stack[][] = new int[10_000][2], index = 0;
        for (; head != null; stack[index++] = new int[]{head.val, size++}, head = head.next)
            while (index > 0 && stack[index - 1][0] < head.val) result[stack[--index][1]] = head.val;
        return Arrays.copyOf(result, size);
    }
}
