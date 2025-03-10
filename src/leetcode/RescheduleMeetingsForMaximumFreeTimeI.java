package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class RescheduleMeetingsForMaximumFreeTimeI {
    /*
    You are given an integer eventTime denoting the duration of an event, where the event occurs from time t = 0 to time t = eventTime.
    You are also given two integer arrays startTime and endTime, each of length n. These represent the start and end time of n non-overlapping meetings, where the ith meeting occurs during the time [startTime[i], endTime[i]].
    You can reschedule at most k meetings by moving their start time while maintaining the same duration, to maximize the longest continuous period of free time during the event.
    The relative order of all the meetings should stay the same, and they should remain non-overlapping.
    Return the maximum amount of free time possible after rearranging the meetings.
    Note that the meetings can not be rescheduled to a time outside the event.

    Example 1:
    Input: eventTime = 5, k = 1, startTime = [1,3], endTime = [2,5]
    Output: 2
    Explanation:
    Reschedule the meeting at [1, 2] to [2, 3], leaving no meetings during the time [0, 2].

    Example 2:
    Input: eventTime = 10, k = 1, startTime = [0,2,9], endTime = [1,4,10]
    Output: 6
    Explanation:
    Reschedule the meeting at [2, 4] to [1, 3], leaving no meetings during the time [3, 9].

    Example 3:
    Input: eventTime = 5, k = 2, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]
    Output: 0
    Explanation:
    There is no time during the event not occupied by meetings.

    Constraints:
        1 <= eventTime <= 10^9
        n == startTime.length == endTime.length
        2 <= n <= 10^5
        1 <= k <= n
        0 <= startTime[i] < endTime[i] <= eventTime
        endTime[i] <= startTime[i + 1] where i lies in the range [0, n - 2].
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, 5, 1, new int[]{1, 3}, new int[]{2, 5}},
                {6, 10, 1, new int[]{0, 2, 9}, new int[]{1, 4, 10}},
                {0, 5, 2, new int[]{0, 1, 2, 3, 4}, new int[]{1, 2, 3, 4, 5}},
        });
    }

    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int totalMeetingCount = startTime.length, gaps[] = new int[totalMeetingCount + 1], prefixSum[] = new int[totalMeetingCount + 2];
        // Calculate the first and last gap separately
        gaps[0] = startTime[0];  // Free time before the first meeting
        gaps[totalMeetingCount] = eventTime - endTime[totalMeetingCount - 1];  // Free time after the last meeting
        // Compute gaps between meetings
        for (int i = 1; i < totalMeetingCount; i++) gaps[i] = startTime[i] - endTime[i - 1];
        // Compute prefix sum for efficient range sum calculation
        for (int i = 1; i <= totalMeetingCount + 1; i++) prefixSum[i] = prefixSum[i - 1] + gaps[i - 1];
        // Sliding window to find the maximum sum of k+1 consecutive gaps
        int result = 0;
        for (int i = k + 1; i <= totalMeetingCount + 1; i++)
            result = Math.max(result, prefixSum[i] - prefixSum[i - (k + 1)]);
        return result;
    }
}
