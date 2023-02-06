package leetcode;

import leetcode.common.ListNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class RemoveNodesFromLinkedList {
	/*
	You are given the head of a linked list.
	Remove every node which has a node with a strictly greater value anywhere to the right side of it.
	Return the head of the modified linked list.

	Example 1:
	Input: head = [5,2,13,3,8]
	Output: [13,8]
	Explanation: The nodes that should be removed are 5, 2 and 3.
	- Node 13 is to the right of node 5.
	- Node 13 is to the right of node 2.
	- Node 8 is to the right of node 3.

	Example 2:
	Input: head = [1,1,1,1]
	Output: [1,1,1,1]
	Explanation: Every node has value 1, so no nodes are removed.

	Constraints:
	The number of the nodes in the given list is in the range [1, 10^5].
	1 <= Node.val <= 10^5
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{ListNode.list(13, 8), ListNode.list(5, 2, 13, 3, 8)},
				{ListNode.list(1, 1, 1, 1), ListNode.list(1, 1, 1, 1)}
		});
	}

	public ListNode removeNodes(ListNode head) {
		if (head == null) return null;
		head.next = removeNodes(head.next);
		return head.next != null && head.val < head.next.val ? head.next : head;
	}
}
