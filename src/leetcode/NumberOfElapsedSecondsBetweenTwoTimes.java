package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class NumberOfElapsedSecondsBetweenTwoTimes {
    /*
    You are given two valid times startTime and endTime, each represented as a string in the format "HH:MM:SS".
    Return the number of seconds that have elapsed from startTime to endTime.

    Example 1:
    Input: startTime = "01:00:00", endTime = "01:00:25"
    Output: 25

    Explanation:
    endTime is 25 seconds ahead of startTime.

    Example 2:
    Input: startTime = "12:34:56", endTime = "13:00:00"
    Output: 1504
    Explanation:
    endTime is 25 minutes and 4 seconds ahead of startTime, which equals 1504 seconds.

    Constraints:
    startTime.length == 8
    endTime.length == 8
    startTime and endTime are valid times in the format "HH:MM:SS"
    00 <= HH <= 23
    00 <= MM <= 59
    00 <= SS <= 59
    endTime is not earlier than startTime
    */
    @Test
    public void test() {
        TestUtil.testEquals(
                new Object[]{25, 1504},
                new Object[][]{
                        {"01:00:00", "01:00:25"},
                        {"12:34:56", "13:00:00"}
                }
        );
    }

    public int secondsBetweenTimes(String startTime, String endTime) {
        return getDiff(startTime, endTime, 0) * 3600 + getDiff(startTime, endTime, 3) * 60 + getDiff(startTime, endTime, 6);
    }

    int getDiff(String start, String end, int index) {
        return getUnit(end, index) - getUnit(start, index);
    }

    int getUnit(String time, int start) {
        return getDigit(time, start++) * 10 + getDigit(time, start);
    }

    int getDigit(String s, int index) {
        return s.charAt(index) - '0';
    }
}
