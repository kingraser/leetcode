package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class FindKClosestElements {

  /*
  Given a sorted array, two integers k and x, find the k closest elements to x in the array. 
  The result should also be sorted in ascending order. 
  If there is a tie, the smaller elements are always preferred.
  
  Example 1:  
  Input: [1,2,3,4,5], k=4, x=3
  Output: [1,2,3,4]
  
  Example 2:  
  Input: [1,2,3,4,5], k=4, x=-1
  Output: [1,2,3,4]
  
  Note:  
    The value k is positive and will always be smaller than the length of the sorted array.
    Length of the given array is positive and will not exceed 10^4
    Absolute value of elements in the array and x will not exceed 10^4  
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(1, 2, 3, 4), findClosestElements(Arrays.asList(1, 2, 3, 4, 5), 4, 3));
    assertEquals(Arrays.asList(1, 2, 3, 4), findClosestElements(Arrays.asList(1, 2, 3, 4, 5), 4, -1));
  }

  public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
    arr.sort((a, b) -> a == b ? 0 : Math.abs(x - a) - Math.abs(x - b));
    List<Integer> result = arr.subList(0, k);
    result.sort(Comparator.naturalOrder());
    return result;
  }

}
