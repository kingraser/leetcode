package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LexicographicalNumbers {

  /*
  Given an integer n, return 1 - n in lexicographical order.
  
  For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
  
  Please optimize your algorithm to use less time and space. 
  The input size may be as large as 5,000,000. 
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9), lexicalOrder(13));
  }

  public List<Integer> lexicalOrder(int n) {
    List<Integer> result = new ArrayList<>();
    for (int i = 1; i < 10; lexicalOrder(result, n, i++));
    return result;
  }

  private void lexicalOrder(List<Integer> result, int n, int last) {
    if (last > n) return;
    result.add(last);
    for (int i = 0; i < 10; lexicalOrder(result, n, last * 10 + i++));
  }
}
