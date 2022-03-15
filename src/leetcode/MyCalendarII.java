package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Wit
 */
public class MyCalendarII {
    /*
    You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a triple booking.
    A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).
    The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
    Implement the MyCalendarTwo class:
    MyCalendarTwo() Initializes the calendar object.
    boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.

    Example 1:
    Input
    ["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
    [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
    Output
    [null, true, true, true, false, true, true]
    Explanation
    MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
    myCalendarTwo.book(10, 20); // return True, The event can be booked.
    myCalendarTwo.book(50, 60); // return True, The event can be booked.
    myCalendarTwo.book(10, 40); // return True, The event can be double booked.
    myCalendarTwo.book(5, 15);  // return False, The event cannot be booked, because it would result in a triple booking.
    myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
    myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.

    Constraints:
    0 <= start < end <= 10^9
    At most 1000 calls will be made to book.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new MyCalendarTwo(), new Object[][]{
                {true, 10, 20},
                {true, 50, 60},
                {true, 10, 40},
                {false, 5, 15},
                {true, 5, 10},
                {true, 25, 55}
        });
        TestUtil.testEquals(new MyCalendarTwo(), new Object[][]{
                        {true, 47, 50},
                        {true, 1, 10},
                        {true, 27, 36},
                        {true, 40, 47},
                        {true, 20, 27},
                        {true, 15, 23},
                        {true, 10, 18},
                        {true, 27, 36},
                        {false, 17, 25},
                        {false, 8, 17},
                        {false, 24, 33},
                        {false, 23, 28},
                        {false, 21, 27},
                        {true, 47, 50},
                        {false, 14, 21},
                        {false, 26, 32},
                        {false, 16, 21},
                        {true, 2, 7},
                        {false, 24, 33},
                        {false, 6, 13},
                        {false, 44, 50},
                        {false, 33, 39},
                        {false, 30, 36},
                        {false, 6, 15},
                        {false, 21, 27},
                        {false, 49, 50},
                        {true, 38, 45},
                        {false, 4, 12},
                        {false, 46, 50},
                        {false, 13, 21}
                }
        );
    }

    public static class MyCalendarTwo {
        TreeMap<Integer, Integer> doubleMap = new TreeMap<>(), map = new TreeMap<>();
        Map.Entry<Integer, Integer> entry;

        public boolean book(int start, int end) {return book(start, end, true);}

        boolean book(int start, int end, boolean needCheck) {
            if (needCheck && (entry = doubleMap.lowerEntry(end)) != null && entry.getValue() > start) return false;
            if ((entry = map.lowerEntry(end)) == null || entry.getValue() <= start) map.put(start, end);
            else {
                doubleMap.put(Math.max(start, entry.getKey()), Math.min(end, entry.getValue()));
                if (entry.getKey() > start) book(start, Math.max(end, map.remove(entry.getKey())), false);
                else if (end > entry.getValue()) map.put(entry.getKey(), end);
            }
            return true;
        }
    }
}
