package leetcode;

import leetcode.common.ListNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Wit
 */
public class DesignLinkedList {
    /*
    Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
    A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node.
    If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
    Implement the MyLinkedList class:
    MyLinkedList() Initializes the MyLinkedList object.
    int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
    void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
    void addAtTail(int val) Append a node of value val as the last element of the linked list.
    void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
    void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.

    Example 1:
    Input
    ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
    [[], [1], [3], [1, 2], [1], [1], [1]]
    Output
    [null, null, null, null, 2, null, 3]
    Explanation
    MyLinkedList myLinkedList = new MyLinkedList();
    myLinkedList.addAtHead(1);
    myLinkedList.addAtTail(3);
    myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
    myLinkedList.get(1);              // return 2
    myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
    myLinkedList.get(1);              // return 3

    Constraints:
    0 <= index, val <= 1000
    Please do not use the built-in LinkedList library.
    At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex.
    */
    public static class MyLinkedList {
        ListNode head, tail, node;
        int size;

        public MyLinkedList() {}

        ListNode getNode(int index) {
            for (node = head; index-- > 0; ) node = node.next;
            return node;
        }

        public int get(int index) {
            return index < 0 || index >= size ? -1 : getNode(index).val;
        }

        public void addAtHead(int val) {
            head = new ListNode(val, head);
            if (size++ == 0) tail = head;
        }

        public void addAtTail(int val) {
            if (size == 0) addAtHead(val);
            else if (++size > 0) tail = tail.next = new ListNode(val);
        }

        public void addAtIndex(int index, int val) {
            if (index < 0 || index > size) return;
            if (index == 0) addAtHead(val);
            else if (index == size) addAtTail(val);
            else if (++size > 0) getNode(--index).next = new ListNode(val, node.next);
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) return;
            if (--size == 0) head = tail = null;
            else if (index == 0) head = head.next;
            else {
                getNode(--index).next = node.next == null ? null : node.next.next;
                if (++index == size) tail = node;
            }
        }

        public String toString() {return ListNode.toString(head);}
    }

    @Test
    public void test() {
        MyLinkedList list = new MyLinkedList();
        List.of(7, 2, 1).forEach(list::addAtHead);
        list.addAtIndex(3, 0);
        list.deleteAtIndex(2);
        list.addAtHead(6);
        list.addAtTail(4);
        Assert.assertEquals(4, list.get(4));
    }
}
