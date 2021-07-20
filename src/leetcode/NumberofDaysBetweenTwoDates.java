package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author Wit
 */
public class NumberofDaysBetweenTwoDates {
    /*
    Write a program to count the number of days between two dates.
    The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.

    Example 1:
    Input: date1 = "2019-06-29", date2 = "2019-06-30"
    Output: 1

    Example 2:
    Input: date1 = "2020-01-15", date2 = "2019-12-31"
    Output: 15

    Constraints:
    The given dates are valid dates between the years 1971 and 2100.
    */

    public int daysBetweenDates(String date1, String date2) {
        return (int) Math.abs(ChronoUnit.DAYS.between(LocalDate.parse(date1), LocalDate.parse(date2)));
    }

    @Test
    public void test() throws NoSuchMethodException {
        TestUtil.testEquals(this,
                new Object[][]{{1, "2019-06-29", "2019-06-30"}, {15, "2020-01-15", "2019-12-31"}});
    }
}
