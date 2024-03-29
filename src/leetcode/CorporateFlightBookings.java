package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CorporateFlightBookings {
	/*
	There are n flights that are labeled from 1 to n.
	You are given an array of flight bookings bookings, where bookings[i] = [firsti, lasti, seatsi] represents a booking for flights firsti through lasti (inclusive) with seatsi seats reserved for each flight in the range.
	Return an array answer of length n, where answer[i] is the total number of seats reserved for flight i.

	Example 1:
	Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
	Output: [10,55,45,25,25]
	Explanation:
	Flight labels:        1   2   3   4   5
	Booking 1 reserved:  10  10
	Booking 2 reserved:      20  20
	Booking 3 reserved:      25  25  25  25
	Total seats:         10  55  45  25  25
	Hence, answer = [10,55,45,25,25]

	Example 2:
	Input: bookings = [[1,2,10],[2,2,15]], n = 2
	Output: [10,25]
	Explanation:
	Flight labels:        1   2
	Booking 1 reserved:  10  10
	Booking 2 reserved:      15
	Total seats:         10  25
	Hence, answer = [10,25]

	Constraints:
	1 <= n <= 2 * 10^4
	1 <= bookings.length <= 2 * 10^4
	bookings[i].length == 3
	1 <= firsti <= lasti <= n
	1 <= seatsi <= 10^4
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{10, 55, 45, 25, 25}, TestUtil.getArray("[[1,2,10],[2,3,20],[2,5,25]]"), 5},
				{new int[]{10, 25}, TestUtil.getArray("[[1,2,10],[2,2,15]]"), 2},
		});
	}

	public int[] corpFlightBookings(int[][] bookings, int n) {
		int[] result = new int[n];
		for (int[] booking : bookings) {
			result[booking[0] - 1] += booking[2];
			if (booking[1] < n) result[booking[1]] -= booking[2];
		}
		for (int i = 1; i < n; i++) result[i] += result[i - 1];
		return result;
	}
}
