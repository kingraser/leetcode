package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.Test;

public class NextGreaterElementI {

  /*
  You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
  
  The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
  
  Example 1:
  
  Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
  Output: [-1,3,-1]
  Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
  
  Example 2:
  
  Input: nums1 = [2,4], nums2 = [1,2,3,4].
  Output: [3,-1]
  Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
  
  Note:  
    All elements in nums1 and nums2 are unique.
    The length of both nums1 and nums2 would not exceed 1000.
  */

  @Test
  public void test() {
    assertArrayEquals(new int[] { -1, 3, -1 }, nextGreaterElement(new int[] { 4, 1, 2 }, new int[] { 1, 3, 4, 2 }));
    assertArrayEquals(new int[] { 3, -1 }, nextGreaterElement(new int[] { 2, 4 }, new int[] { 1, 2, 3, 4 }));
  }

  public int[] nextGreaterElement(int[] findNums, int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    Stack<Integer> stack = new Stack<>();
    for (int num : nums) {
      while (!stack.isEmpty() && stack.peek() < num)
        map.put(stack.pop(), num);
      stack.push(num);
    }
    return Arrays.stream(findNums).map(i -> map.getOrDefault(i, -1)).toArray();
  }
}
