package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class ButtonWithLongestPushTime {
    /*
    You are given a 2D array events which represents a sequence of events where a child pushes a series of buttons on a keyboard.
    Each events[i] = [indexi, timei] indicates that the button at index indexi was pressed at time timei.
        The array is sorted in increasing order of time.
        The time taken to press a button is the difference in time between consecutive button presses. The time for the first button is simply the time at which it was pressed.
    Return the index of the button that took the longest time to push. If multiple buttons have the same longest time, return the button with the smallest index.

    Example 1:
    Input: events = [[1,2],[2,5],[3,9],[1,15]]
    Output: 1
    Explanation:
        Button with index 1 is pressed at time 2.
        Button with index 2 is pressed at time 5, so it took 5 - 2 = 3 units of time.
        Button with index 3 is pressed at time 9, so it took 9 - 5 = 4 units of time.
        Button with index 1 is pressed again at time 15, so it took 15 - 9 = 6 units of time.

    Example 2:
    Input: events = [[10,5],[1,7]]
    Output: 10
    Explanation:
        Button with index 10 is pressed at time 5.
        Button with index 1 is pressed at time 7, so it took 7 - 5 = 2 units of time.

    Constraints:
        1 <= events.length <= 1000
        events[i] == [indexi, timei]
        1 <= indexi, timei <= 10^5
        The input is generated such that events is sorted in increasing order of timei.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1, TestUtil.getArray("[[1,2],[2,5],[3,9],[1,15]]")},
                {10, TestUtil.getArray("[[10,5],[1,7]]")},
        });
    }

    public int buttonWithLongestTime(int[][] events) {
        int result = events[0][0], resultTime = events[0][1];
        for (int i = 1, time; i < events.length; i++)
            if ((time = events[i][1] - events[i - 1][1]) > resultTime) {
                resultTime = time;
                result = events[i][0];
            } else if (time == resultTime) result = Math.min(result, events[i][0]);
        return result;
    }
}
