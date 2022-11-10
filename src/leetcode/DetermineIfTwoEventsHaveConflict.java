package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class DetermineIfTwoEventsHaveConflict {
	/*
	You are given two arrays of strings that represent two inclusive events that happened on the same day, event1 and event2, where:
	event1 = [startTime1, endTime1] and
	event2 = [startTime2, endTime2].
	Event times are valid 24 hours format in the form of HH:MM.
	A conflict happens when two events have some non-empty intersection (i.e., some moment is common to both events).
	Return true if there is a conflict between two events. Otherwise, return false.

	Example 1:
	Input: event1 = ["01:15","02:00"], event2 = ["02:00","03:00"]
	Output: true
	Explanation: The two events intersect at time 2:00.

	Example 2:
	Input: event1 = ["01:00","02:00"], event2 = ["01:20","03:00"]
	Output: true
	Explanation: The two events intersect starting from 01:20 to 02:00.

	Example 3:
	Input: event1 = ["10:00","11:00"], event2 = ["14:00","15:00"]
	Output: false
	Explanation: The two events do not intersect.

	Constraints:
	evnet1.length == event2.length == 2.
	event1[i].length == event2[i].length == 5
	startTime1 <= endTime1
	startTime2 <= endTime2
	All the event times follow the HH:MM format.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{false, new String[]{"14:13", "22:08"}, new String[]{"02:40", "08:08"}},
				{true, new String[]{"01:15", "02:00"}, new String[]{"02:00", "03:00"}},
				{true, new String[]{"01:00", "02:00"}, new String[]{"01:20", "03:00"}},
				{false, new String[]{"10:00", "11:00"}, new String[]{"14:00", "15:00"}},
		});
	}

	public boolean haveConflict(String[] event1, String[] event2) {
		return isBeforeOrEquals(event1[0], event2[0]) ? isBeforeOrEquals(event2[0], event1[1]) : isBeforeOrEquals(event1[0], event2[1]);
	}

	boolean isBeforeOrEquals(String time1, String time2) {
		int hour1 = Integer.parseInt(time1.substring(0, 2)), hour2 = Integer.parseInt(time2.substring(0, 2));
		if (hour1 == hour2)
			return Integer.parseInt(time1.substring(3, 5)) - Integer.parseInt(time2.substring(3, 5)) <= 0;
		return hour1 - hour2 < 0;
	}
}
