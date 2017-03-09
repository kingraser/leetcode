package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class IntersectionofTwoArrays {

  /*
  Given two arrays, write a function to compute their intersection.
  
  Example:
  Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2]. 
  */

  @Test
  public void test() {
    assertArrayEquals(new int[] { 2 }, intersection(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 }));
    assertArrayEquals(new int[] { 2, 3 }, intersectionII(new int[] { 2, 1, 3 }, new int[] { 2, 2, 3, 4 }));
  }

  public int[] intersection(int[] nums1, int[] nums2) {
    Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
    return Arrays.stream(nums2).filter(set::remove).toArray();
  }

  public int[] intersectionII(int[] nums1, int[] nums2) {
    int k = 0, l1 = nums1.length, l2 = nums2.length;
    int[] result = new int[l1 < l2 ? l1 : l2];
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    for (int i = 0, j = 0; i < l1 && j < l2;)
      if (nums1[i] < nums2[j] || (i > 0 && nums1[i - 1] == nums1[i])) i++;
      else if (nums1[i] == nums2[j++]) result[k++] = nums1[i++];
    return Arrays.copyOf(result, k);
  }

}
