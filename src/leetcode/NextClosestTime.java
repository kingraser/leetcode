package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.junit.Test;

import leetcode.util.ArrayUtil;

public class NextClosestTime {

  /*
  Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.  
  You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
  
  Example 1:  
  Input: "19:34"
  Output: "19:39"
  Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
   
  Example 2:  
  Input: "23:59"
  Output: "22:22"
  Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
  */

  @Test
  public void test() {
    assertEquals("19:39", nextClosestTime("19:34"));
    assertEquals("22:22", nextClosestTime("23:59"));
  }

  public String nextClosestTime(String time) {
    char[] result = time.toCharArray();
    int[] digits = Stream.of(result[0], result[1], result[3], result[4]).distinct().sorted().mapToInt(i -> i).toArray();
    for (int i = 4, idx; i >= 0; i--)
      if (i == 2) continue;
      else if ((idx = ArrayUtil.findFirst(digits, result[i])) < digits.length - 1 && (i == 4
          || (i == 3 && digits[idx + 1] <= '5') || (i == 1 && (result[0] - '0') * 10 + digits[idx + 1] - '0' < 24)
          || (i == 0 && digits[idx + 1] <= '2'))) {
        result[i] = (char) digits[idx + 1];
        break;
      } else result[i] = (char) digits[0];
    return new String(result);
  }

}
