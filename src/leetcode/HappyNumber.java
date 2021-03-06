package leetcode;

import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class HappyNumber {

  /*
  Example: 19 is a happy number
  
  pow(1,2) + pow(9,2) = 82
  pow(8,2) + pow(2,2) = 68
  pow(6,2) + pow(8,2) = 100
  pow(1,2) + pow(0,2) + pow(0,2) = 1
  */

  public boolean isHappy(int n) {
    Set<Integer> set = Stream.of(n).collect(Collectors.toSet());
    for (int result = 0; n != 1; n = result, result = 0) {
      for (int digit; n != 0; result += (digit = n % 10) * digit, n /= 10);
      if (!set.add(result)) return false;
    }
    return true;
  }

  @Test
  public void test() {
    assertTrue(isHappy(19));
  }
}
