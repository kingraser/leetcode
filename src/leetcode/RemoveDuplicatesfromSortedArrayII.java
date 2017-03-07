package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RemoveDuplicatesfromSortedArrayII {

  /*
  Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?
  For example, Given sorted array nums = [1,1,1,2,2,3],  
  Your function should return length = 5, 
  with the first five elements of nums being 1, 1, 2, 2 and 3. 
  It doesn't matter what you leave beyond the new length. 
  */

  @Test
  public void test() {
    assertEquals(5, removeDuplicates(new int[] { 1, 1, 1, 2, 2, 3 }));
  }

  public int removeDuplicates(int[] A) {
    return removeDuplicates(A, 2);
  }

  /**
   * @param A array
   * @param k k duplicates allowed
   * @return
   */
  public static int removeDuplicates(int[] A, int k) {
    for (int i = k, size = k; i < A.length; i++)
      if (A[i] != A[k - size]) A[k++] = A[i];
    return Math.min(A.length, k);
  }

}
