package leetcode;

import leetcode.common.ListNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class DoubleANumberRepresentedAsALinkedList {
	/*
	You are given the head of a non-empty linked list representing a non-negative integer without leading zeroes.
	Return the head of the linked list after doubling it.

	Example 1:
	Input: head = [1,8,9]
	Output: [3,7,8]
	Explanation: The figure above corresponds to the given linked list which represents the number 189. Hence, the returned linked list represents the number 189 * 2 = 378.

	Example 2:
	Input: head = [9,9,9]
	Output: [1,9,9,8]
	Explanation: The figure above corresponds to the given linked list which represents the number 999. Hence, the returned linked list reprersents the number 999 * 2 = 1998.

	Constraints:
	The number of nodes in the list is in the range [1, 10^4]
	0 <= Node.val <= 9
	The input is generated such that the list represents a number that does not have leading zeros, except the number 0 itself.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{ListNode.list(3, 7, 8), ListNode.list(1, 8, 9)},
				{ListNode.list(1, 9, 9, 8), ListNode.list(9, 9, 9)}
		});
	}

	ListNode tail;
	int carry;

	public ListNode doubleIt(ListNode head) {
		internalDouble(head);
		return carry == 0 ? tail : new ListNode(1, tail);
	}

	void internalDouble(ListNode head) {
		if (head == null) {
			tail = null;
			carry = 0;
			return;
		}
		internalDouble(head.next);
		head.next = tail;
		if ((head.val = (head.val << 1) + carry) > 9) {
			head.val -= 10;
			carry = 1;
		} else carry = 0;
		tail = head;
	}
}
