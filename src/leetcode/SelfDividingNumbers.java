package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class SelfDividingNumbers {
  /*
  A self-dividing number is a number that is divisible by every digit it contains.  
  For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.  
  Also, a self-dividing number is not allowed to contain the digit zero.  
  Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible.
  
  Example 1:  
  Input: 
  left = 1, right = 22
  Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
  
  Note:
  The boundaries of each input argument are 1 <= left <= right <= 10000.  
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22), selfDividingNumbers(1, 22));
  }

  public List<Integer> selfDividingNumbers(int left, int right) {
    return IntStream.range(left, ++right).filter(i -> isSelfDividing(i)).boxed().collect(Collectors.toList());
  }

  private boolean isSelfDividing(int i) {
    for (int j = i, k; j > 0; j /= 10)
      if ((k = j % 10) == 0 || i % k != 0) return false;
    return true;
  }
}
