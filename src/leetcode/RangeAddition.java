package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class RangeAddition {

  /*
  Assume you have an array of length n initialized with all 0's and are given k update operations.  
  Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
  Return the modified array after all k operations were executed.
  
  Example:  
  Given:  
    length = 5,
    updates = [
        [1,  3,  2],
        [2,  4,  3],
        [0,  2, -2]
    ]
  
  Output:  
    [-2, 0, 3, 5, 3]
  
  Explanation:  
  Initial state:
  [ 0, 0, 0, 0, 0 ]  
  After applying operation [1, 3, 2]:
  [ 0, 2, 2, 2, 0 ]  
  After applying operation [2, 4, 3]:
  [ 0, 2, 5, 5, 3 ]  
  After applying operation [0, 2, -2]:
  [-2, 0, 3, 5, 3 ]
  
  Hint:  
    Thinking of using advanced data structures? You are thinking it too complicated.
    For each update operation, do you really need to update all elements between i and j?
    Update only the first and end element is sufficient.
    The optimal time complexity is O(k + n) and uses O(1) extra space. 
  */

  @Test
  public void test() {
    assertArrayEquals(new int[] { -2, 0, 3, 5, 3 },
        getModifiedArray(5, new int[][] { { 1, 3, 2 }, { 2, 4, 3 }, { 0, 2, -2 } }));
  }

  public int[] getModifiedArray(int length, int[][] updates) {
    int[] result = new int[length];
    for (int[] update : updates) {
      result[update[0]] += update[2];
      if (update[1] < length - 1) result[update[1] + 1] -= update[2];
    }
    for (int i = 0, sum = 0; i < length; i++)
      result[i] = sum += result[i];
    return result;
  }

}
