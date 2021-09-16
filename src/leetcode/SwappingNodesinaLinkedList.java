package leetcode;

import leetcode.common.ListNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SwappingNodesinaLinkedList {
    /*
    You are given the head of a linked list, and an integer k.
    Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).

    Example 1:
    Input: head = [1,2,3,4,5], k = 2
    Output: [1,4,3,2,5]

    Example 2:
    Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
    Output: [7,9,6,6,8,7,3,0,9,5]

    Example 3:
    Input: head = [1], k = 1
    Output: [1]

    Example 4:
    Input: head = [1,2], k = 1
    Output: [2,1]

    Example 5:
    Input: head = [1,2,3], k = 2
    Output: [1,2,3]

    Constraints:
    The number of nodes in the list is n.
    1 <= k <= n <= 10^5
    0 <= Node.val <= 100
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {ListNode.list(1, 4, 3, 2, 5), ListNode.list(1, 2, 3, 4, 5), 2},
                {ListNode.list(7, 9, 6, 6, 8, 7, 3, 0, 9, 5), ListNode.list(7, 9, 6, 6, 7, 8, 3, 0, 9, 5), 5},
                {ListNode.list(1), ListNode.list(1), 1},
                {ListNode.list(1, 2), ListNode.list(2, 1), 1},
                {ListNode.list(1, 2, 3), ListNode.list(1, 2, 3), 2}
        });
    }

    public ListNode swapNodes(ListNode head, int k) {
        ListNode first, second, node;
        for (node = head; --k > 0; ) node = node.next;
        first = node;
        for (second = head; node.next != null; second = second.next) node = node.next;
        swap(first, second);
        return head;
    }

    private void swap(ListNode node1, ListNode node2) {
        if (node1 == node2 || node1.val == node2.val) return;
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }
}
