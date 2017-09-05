package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Test;

import leetcode.util.ArrayUtil;

public class MaximumSwap {

  /*
  Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. 
  Return the maximum valued number you could get.
  
  Example 1:  
  Input: 2736
  Output: 7236
  Explanation: Swap the number 2 and the number 7.
  
  Example 2:  
  Input: 9973
  Output: 9973
  Explanation: No swap.
  
  Note:  
    The given number is in the range [0, 10^8] 
  */

  @Test
  public void test() {
    assertEquals(7236, maximumSwap(2736));
    assertEquals(9973, maximumSwap(9973));
    assertEquals(1, maximumSwap(1));
  }

  public int maximumSwap(int num) {
    int[] digits = Integer.toString(num).chars().map(i -> i - '0').toArray(), buckets = new int[10];
    IntStream.range(0, digits.length).forEach(i -> buckets[digits[i]] = i);
    for (int i = 0; i < digits.length; i++)
      for (int k = 9; k > digits[i]; k--)
        if (buckets[k] > i) {
          ArrayUtil.swap(digits, i, buckets[k]);
          return Arrays.stream(digits).reduce((a, b) -> a * 10 + b).getAsInt();
        }
    return num;
  }
}
