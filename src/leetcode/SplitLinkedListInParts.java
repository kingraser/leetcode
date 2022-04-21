package leetcode;

import leetcode.common.ListNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SplitLinkedListInParts {
    /*
    Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
    The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.
    The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to the parts occurring later.
    Return an array of the k parts.

    Example 1:
    Input: head = [1,2,3], k = 5
    Output: [[1],[2],[3],[],[]]
    Explanation:
    The first element output[0] has output[0].val = 1, output[0].next = null.
    The last element output[4] is null, but its string representation as a ListNode is [].

    Example 2:
    Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
    Output: [[1,2,3,4],[5,6,7],[8,9,10]]
    Explanation:
    The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.

    Constraints:
    The number of nodes in the list is in the range [0, 1000].
    0 <= Node.val <= 1000
    1 <= k <= 50
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new ListNode[]{ListNode.list(1), ListNode.list(2), ListNode.list(3), null, null},
                        ListNode.list(1, 2, 3),
                        5},
                {new ListNode[]{ListNode.list(1, 2, 3, 4), ListNode.list(5, 6, 7), ListNode.list(8, 9, 10)},
                        ListNode.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                        3}
        });
    }

    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode result[] = new ListNode[k], node;
        if (head == null) return result;
        for (int i = 0, len = getLength(head), count = len / k, remainder = len % k, move; i < k; head = node.next, node.next = null) {
            for (result[i++] = node = head, move = count; --move > 0; node = node.next) if (node == null) return result;
            if (node == null) return result;
            if (move == 0 && remainder-- > 0) node = node.next;
            if (node == null) return result;
        }
        return result;
    }

    int getLength(ListNode node) {
        int count = 0;
        for (; node != null; node = node.next) count++;
        return count;
    }
}
