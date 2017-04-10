package leetcode;

import static leetcode.util.ArrayUtil.reverse;
import static leetcode.util.ArrayUtil.swap;
import static org.junit.Assert.assertArrayEquals;

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

  public static void nextPermutation(int[] num) {
    int idx = num.length - 1, pivot = idx - 1;
    for (; pivot >= 0 && num[pivot] >= num[pivot + 1]; pivot--);
    if (pivot >= 0) {
      for (; idx > pivot && num[idx] <= num[pivot]; idx--);
      swap(num, pivot, idx);
    }
    reverse(num, pivot + 1, num.length - 1);
  }
}
