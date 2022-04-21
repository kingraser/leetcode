package leetcode;

import leetcode.common.ListNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class LinkedListComponents {
    /*
    You are given the head of a linked list containing unique integer values and an integer array nums that is a subset of the linked list values.
    Return the number of connected components in nums where two values are connected if they appear consecutively in the linked list.

    Example 1:
    Input: head = [0,1,2,3], nums = [0,1,3]
    Output: 2
    Explanation: 0 and 1 are connected, so [0, 1] and [3] are the two connected components.

    Example 2:
    Input: head = [0,1,2,3,4], nums = [0,3,1,4]
    Output: 2
    Explanation: 0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.

    Constraints:
    The number of nodes in the linked list is n.
    1 <= n <= 10^4
    0 <= Node.val < n
    All the values Node.val are unique.
    1 <= nums.length <= n
    0 <= nums[i] < n
    All the values of nums are unique.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, ListNode.list(0, 1, 2, 3), new int[]{0, 1, 3}},
                {2, ListNode.list(0, 1, 2, 3, 4), new int[]{0, 3, 1, 4}},
        });
    }

    public int numComponents(ListNode head, int[] nums) {
        int result = 0, set[] = new int[10_000];
        for (int num : nums) set[num]++;
        while (head != null) {
            while (head != null && set[head.val] == 0) head = head.next;
            for (result += head != null ? 1 : 0; head != null && set[head.val] > 0; ) head = head.next;
        }
        return result;
    }
}
