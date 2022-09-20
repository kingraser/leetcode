package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountDaysSpentTogether {
	/*
	Alice and Bob are traveling to Rome for separate business meetings.
	You are given 4 strings arriveAlice, leaveAlice, arriveBob, and leaveBob. Alice will be in the city from the dates arriveAlice to leaveAlice (inclusive), while Bob will be in the city from the dates arriveBob to leaveBob (inclusive). Each will be a 5-character string in the format "MM-DD", corresponding to the month and day of the date.
	Return the total number of days that Alice and Bob are in Rome together.
	You can assume that all dates occur in the same calendar year, which is not a leap year. Note that the number of days per month can be represented as: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31].

	Example 1:
	Input: arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
	Output: 3
	Explanation: Alice will be in Rome from August 15 to August 18. Bob will be in Rome from August 16 to August 19. They are both in Rome together on August 16th, 17th, and 18th, so the answer is 3.

	Example 2:
	Input: arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
	Output: 0
	Explanation: There is no day when Alice and Bob are in Rome together, so we return 0.

	Constraints:
	All dates are provided in the format "MM-DD".
	Alice and Bob's arrival dates are earlier than or equal to their leaving dates.
	The given dates are valid dates of a non-leap year.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, "08-15", "08-18", "08-16", "08-19"},
				{0, "10-01", "10-31", "11-01", "12-31"}
		});
	}

	static final int[] MONTH = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
		return countDaysTogether(arriveAlice.compareTo(arriveBob) < 0 ? arriveBob : arriveAlice, leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob);
	}

	int countDaysTogether(String start, String end) {
		return countDaysTogether(getInt(start, 0), getInt(start, 3), getInt(end, 0), getInt(end, 3));
	}

	int countDaysTogether(int startMonth, int startDay, int endMonth, int endDay) {
		if (endMonth < startMonth) return 0;
		if (startMonth == endMonth) return endDay < startDay ? 0 : endDay - startDay + 1;
		int result = 0;
		for (int month = startMonth + 1; month < endMonth; ) result += MONTH[month++];
		return result + MONTH[startMonth] - startDay + 1 + endDay;
	}


	int getInt(String s, int start) {return getDigit(s, start++) * 10 + getDigit(s, start);}

	int getDigit(String s, int index) {return s.charAt(index) - '0';}
}
