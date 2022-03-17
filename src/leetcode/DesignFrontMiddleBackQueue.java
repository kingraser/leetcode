package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @author Wit
 */
public class DesignFrontMiddleBackQueue {
    /*
    Design a queue that supports push and pop operations in the front, middle, and back.

    Implement the FrontMiddleBack class:

    FrontMiddleBack() Initializes the queue.
    void pushFront(int val) Adds val to the front of the queue.
    void pushMiddle(int val) Adds val to the middle of the queue.
    void pushBack(int val) Adds val to the back of the queue.
    int popFront() Removes the front element of the queue and returns it. If the queue is empty, return -1.
    int popMiddle() Removes the middle element of the queue and returns it. If the queue is empty, return -1.
    int popBack() Removes the back element of the queue and returns it. If the queue is empty, return -1.
    Notice that when there are two middle position choices, the operation is performed on the frontmost middle position choice. For example:

    Pushing 6 into the middle of [1, 2, 3, 4, 5] results in [1, 2, 6, 3, 4, 5].
    Popping the middle from [1, 2, 3, 4, 5, 6] returns 3 and results in [1, 2, 4, 5, 6].

    Example 1:
    Input:
    ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
    [[], [1], [2], [3], [4], [], [], [], [], []]
    Output:
    [null, null, null, null, null, 1, 3, 4, 2, -1]
    Explanation:
    FrontMiddleBackQueue q = new FrontMiddleBackQueue();
    q.pushFront(1);   // [1]
    q.pushBack(2);    // [1, 2]
    q.pushMiddle(3);  // [1, 3, 2]
    q.pushMiddle(4);  // [1, 4, 3, 2]
    q.popFront();     // return 1 -> [4, 3, 2]
    q.popMiddle();    // return 3 -> [4, 2]
    q.popMiddle();    // return 4 -> [2]
    q.popBack();      // return 2 -> []
    q.popFront();     // return -1 -> [] (The queue is empty)

    Constraints:
    1 <= val <= 10^9
    At most 1000 calls will be made to pushFront, pushMiddle, pushBack, popFront, popMiddle, and popBack.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new FrontMiddleBackQueue(),
                new String[]{"pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"},
                new Object[][]{
                        {null, 1},
                        {null, 2},
                        {null, 3},
                        {null, 4},
                        {1},
                        {3},
                        {4},
                        {2},
                        {-1},
                });
    }

    public static class FrontMiddleBackQueue {
        LinkedList<Integer> left = new LinkedList<>(), right = new LinkedList<>();

        public void pushFront(int val) {
            left.addFirst(val);
            balance();
        }

        public void pushMiddle(int val) {
            left.addLast(val);
            balance();
        }

        public void pushBack(int val) {
            right.addLast(val);
            balance();
        }

        public int popFront() {
            int res = -1;
            if (!left.isEmpty()) {res = left.pollFirst();} else if (!right.isEmpty()) {res = right.pollFirst();}
            balance();
            return res;
        }

        public int popMiddle() {
            int res = -1;
            if (((left.size() + right.size()) & 1) == 1 && !right.isEmpty()) res = right.pollFirst();
            else if (!left.isEmpty()) res = left.pollLast();
            balance();
            return res;
        }

        public int popBack() {
            int res = -1;
            if (!right.isEmpty()) {res = right.pollLast();} else if (!left.isEmpty()) {res = left.pollLast();}
            balance();
            return res;
        }

        private void balance() {
            while (left.size() < right.size() - 1) {left.offerLast(right.pollFirst());}
            while (left.size() > right.size()) {right.offerFirst(left.pollLast());}
        }
    }
}