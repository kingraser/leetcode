package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static com.google.common.base.CaseFormat.UPPER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

/**
 * @author Wit
 */
public class DayoftheWeek {
    /*
    Given a date, return the corresponding day of the week for that date.
    The input is given as three integers representing the day, month and year respectively.
    Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.

    Example 1:
    Input: day = 31, month = 8, year = 2019
    Output: "Saturday"

    Example 2:
    Input: day = 18, month = 7, year = 1999
    Output: "Sunday"

    Example 3:
    Input: day = 15, month = 8, year = 1993
    Output: "Sunday"

    Constraints:
    The given dates are valid dates between the years 1971 and 2100.
    */

    @Test
    public void test() {
        Assert.assertEquals("Saturday", dayOfTheWeek(31, 8, 2019));
        Assert.assertEquals("Sunday", dayOfTheWeek(18, 7, 1999));
        Assert.assertEquals("Sunday", dayOfTheWeek(15, 8, 1993));
    }

    String dayOfTheWeek(int day, int month, int year) {
        return UPPER_UNDERSCORE.to(UPPER_CAMEL, LocalDate.of(year, month, day).getDayOfWeek().name());
    }
}
