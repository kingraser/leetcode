package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author Wit
 */
public class SeatReservationManager {
    /*
    Design a system that manages the reservation state of n seats that are numbered from 1 to n.
    Implement the SeatManager class:
    SeatManager(int n) Initializes a SeatManager object that will manage n seats numbered from 1 to n. All seats are initially available.
    int reserve() Fetches the smallest-numbered unreserved seat, reserves it, and returns its number.
    void unreserve(int seatNumber) Unreserves the seat with the given seatNumber.

    Example 1:
    Input
    ["SeatManager", "reserve", "reserve", "unreserve", "reserve", "reserve", "reserve", "reserve", "unreserve"]
    [[5], [], [], [2], [], [], [], [], [5]]
    Output
    [null, 1, 2, null, 2, 3, 4, 5, null]

    Explanation
    SeatManager seatManager = new SeatManager(5); // Initializes a SeatManager with 5 seats.
    seatManager.reserve();    // All seats are available, so return the lowest numbered seat, which is 1.
    seatManager.reserve();    // The available seats are [2,3,4,5], so return the lowest of them, which is 2.
    seatManager.unreserve(2); // Unreserve seat 2, so now the available seats are [2,3,4,5].
    seatManager.reserve();    // The available seats are [2,3,4,5], so return the lowest of them, which is 2.
    seatManager.reserve();    // The available seats are [3,4,5], so return the lowest of them, which is 3.
    seatManager.reserve();    // The available seats are [4,5], so return the lowest of them, which is 4.
    seatManager.reserve();    // The only available seat is seat 5, so return 5.
    seatManager.unreserve(5); // Unreserve seat 5, so now the available seats are [5].

    Constraints:
    1 <= n <= 10^5
    1 <= seatNumber <= n
    For each call to reserve, it is guaranteed that there will be at least one unreserved seat.
    For each call to unreserve, it is guaranteed that seatNumber will be reserved.
    At most 10^5 calls in total will be made to reserve and unreserve.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new SeatManager(5),
                new String[]{"reserve", "reserve", "unreserve", "reserve", "reserve", "reserve", "reserve", "unreserve"},
                new Object[][]{
                        {1},
                        {2},
                        {null, 2},
                        {2},
                        {3},
                        {4},
                        {5},
                        {null, 5}
                });
        TestUtil.testEquals(new SeatManager(3),
                new String[]{"reserve", "reserve", "unreserve", "unreserve", "reserve", "unreserve", "reserve", "unreserve"},
                new Object[][]{
                        {1},
                        {2},
                        {null, 1},
                        {null, 2},
                        {1},
                        {null, 1},
                        {1},
                        {null, 1}
                });
    }

    public static class SeatManager {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int min = 1;

        public SeatManager(int n) {}

        public int reserve() {
            if (queue.isEmpty()) return min++;
            return queue.poll();
        }

        public void unreserve(int seatNumber) {queue.offer(seatNumber);}
    }
}
