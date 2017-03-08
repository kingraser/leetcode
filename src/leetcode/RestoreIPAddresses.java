package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class RestoreIPAddresses {

  /*
  Given a string containing only digits, restore it by returning all possible valid IP address combinations.
  
  For example:
  Given "25525511135",
  
  return ["255.255.11.135", "255.255.111.35"]. (Order does not matter) 
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList("255.255.11.135", "255.255.111.35"), restoreIpAddresses("25525511135"));
  }

  public List<String> restoreIpAddresses(String string) {
    List<String> result = new ArrayList<>();
    char[] s = string.toCharArray();
    for (int first = 1; first < Math.min(4, s.length - 2); first++)
      for (int second = first + 1; second < Math.min(first + 4, s.length - 1); second++)
        for (int third = second + 1; third < Math.min(second + 4, s.length); third++)
          if (isValid(s, 0, first) && isValid(s, first, second) && isValid(s, second, third)
              && isValid(s, third, s.length))
            result.add(getResult(s, first, second, third));
    return result;
  }

  public boolean isValid(char[] s, int start, int end) {
    return end - start == 1 || (s[start] != '0' && smallerThan(s, start, end, 256));
  }

  private boolean smallerThan(char[] s, int start, int end, int limit) {
    for (int result = 0, base = (int) Math.pow(10, end - start - 1); base > 0; base /= 10)
      if ((result += (s[start++] - '0') * base) >= limit) return false;
    return true;
  }

  private String getResult(char[] s, int first, int second, int third) {
    char[] result = new char[s.length + 3];
    for (int idx = 0, i = 0; i < s.length; result[idx++] = s[i++])
      if (i == first || i == second || i == third) result[idx++] = '.';
    return new String(result);
  }

}
