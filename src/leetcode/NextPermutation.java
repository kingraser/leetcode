package leetcode;

import static leetcode.util.ArrayUtil.reverse;
import static leetcode.util.ArrayUtil.swap;
import static org.junit.Assert.assertArrayEquals;

import java.util.Comparator;
import java.util.TreeMap;

import org.junit.Test;

public class NextPermutation {

  /*
  Implement next permutation, 
  which rearranges numbers into the lexicographically next greater permutation of numbers.
  If such arrangement is not possible, 
  it must rearrange it as the lowest possible order (ie, sorted in ascending order).
  The replacement must be in-place, do not allocate extra memory.  
  Here are some examples. 
  Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
  1,2,3 → 1,3,2
  3,2,1 → 1,2,3
  1,1,5 → 1,5,1
  */

  @Test
  public void test() {
    int[] actual = new int[] { 1, 2, 3 };
    nextPermutation(actual);
    assertArrayEquals(new int[] { 1, 3, 2 }, actual);

    actual = new int[] { 3, 2, 1 };
    nextPermutation(actual);
    assertArrayEquals(new int[] { 1, 2, 3 }, actual);

    actual = new int[] { 1, 1, 5 };
    nextPermutation(actual);
    assertArrayEquals(new int[] { 1, 5, 1 }, actual);

    actual = new int[] { 1, 2, 3, 7, 4, 5, 6 };
    nextPermutation(actual);
    assertArrayEquals(new int[] { 1, 2, 3, 7, 4, 6, 5 }, actual);
  }

  public void nextPermutation(int[] num) {
    TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
    map.put(Integer.MIN_VALUE, num.length);
    int idx = num.length - 1;
    for (; idx >= 0 && num[idx] > map.firstKey(); map.put(num[idx], idx--));
    if (idx != -1) swap(num, idx, map.get(map.lowerKey(num[idx])));
    reverse(num, idx + 1, num.length - 1);
  }
}
