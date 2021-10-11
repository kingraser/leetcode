package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Objects;

/**
 * @author Wit
 */
public class FlattenaMultilevelDoublyLinkedList {
    /*
    You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.
    Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.

    Example 1:
    Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
    Output: [1,2,3,7,8,11,12,9,10,4,5,6]
    Explanation:
    The multilevel linked list in the input is as follows:
    After flattening the multilevel linked list it becomes:

    Example 2:
    Input: head = [1,2,null,3]
    Output: [1,3,2]
    Explanation:
    The input multilevel linked list is as follows:
      1---2---NULL
      |
      3---NULL

    Example 3:
    Input: head = []
    Output: []

    How multilevel linked list is represented in test case:
    We use the multilevel linked list from Example 1 above:
     1---2---3---4---5---6--NULL
             |
             7---8---9---10--NULL
                 |
                 11--12--NULL
    The serialization of each level is as follows:
    [1,2,3,4,5,6,null]
    [7,8,9,10,null]
    [11,12,null]
    To serialize all levels together we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:
    [1,2,3,4,5,6,null]
    [null,null,7,8,9,10,null]
    [null,11,12,null]
    Merging the serialization of each level and removing trailing nulls we obtain:
    [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]

    Constraints:
    The number of Nodes will not exceed 1000.
    1 <= Node.val <= 10^5
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {Node.create("1,2,3,7,8,11,12,9,10,4,5,6"), Node.create("1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12")},
                {Node.create("1,3,2"), Node.create("1,2,null,3")}
        });
    }

    public Node flatten(Node head) {
        for (Node current = head, next; current != null; current = current.next) {
            if (current.child == null) continue;
            link(current, flatten(current.child), flatten(current.next));
            break;
        }
        return head;
    }

    void link(Node prefix, Node child, Node suffix) {
        (prefix.next = child).prev = prefix;
        while (child.next != null) child = child.next;
        if ((child.next = suffix) != null) suffix.prev = child;
        prefix.child = null;
    }

    public static class Node {
        private static final String NULL = "null";

        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {this.val = val;}

        public Node(int val, Node prev) {
            this(val);
            if (prev != null) (this.prev = prev).next = this;
        }

        public static Node create(String s) {
            Node root = new Node(0);
            create(root, s.split(","), 0);
            return root.child;
        }

        static void create(Node parent, String[] nodes, int start) {
            Node root = null, prev = null;
            for (int i = start, isStartNull = 1; i < nodes.length; i++)
                if (Objects.equals(nodes[i], NULL)) {
                    if (isStartNull == 1) parent = parent.next;
                    else {
                        create(root, nodes, ++i);
                        return;
                    }
                } else {
                    prev = new Node(Integer.parseInt(nodes[i]), prev);
                    if (isStartNull == 1) {
                        parent.child = root = prev;
                        isStartNull = 0;
                    }
                }
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Node)) return false;
            Node another = (Node) o;
            return (val == another.val) && Objects.equals(child, another.child) && Objects.equals(next, another.next);
        }
    }
}
