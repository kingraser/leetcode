package leetcode;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Wit
 */
public class ReformatDate {
    /*
    Given a date string in the form Day Month Year, where:
    Day is in the set {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}.
    Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}.
    Year is in the range [1900, 2100].
    Convert the date string to the format YYYY-MM-DD, where:
    YYYY denotes the 4 digit year.
    MM denotes the 2 digit month.
    DD denotes the 2 digit day.

    Example 1:
    Input: date = "20th Oct 2052"
    Output: "2052-10-20"

    Example 2:
    Input: date = "6th Jun 1933"
    Output: "1933-06-06"

    Example 3:
    Input: date = "26th May 1960"
    Output: "1960-05-26"

    Constraints:
    The given dates are guaranteed to be valid, so no error handling is necessary.
    */

    String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    Map<String, String> monthMap = IntStream.range(1, months.length + 1).boxed().collect(Collectors.toMap(i -> months[i - 1], i -> (i > 9 ? "" : "0") + i));

    public String reformatDate(String date) {
        String[] array = date.split(" ");
        return array[2] + "-" + monthMap.get(array[1]) + "-" + (array[0].length() == 3 ? "0" : "") + array[0].substring(0, array[0].length() - 2);
    }

}
