package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Test;

public class NextGreaterElementII {

  /*
  Given a circular array (the next element of the last element is the first element of the array), 
  print the Next Greater Number for every element. 
  The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, 
  which means you could search circularly to find its next greater number. 
  If it doesn't exist, output -1 for this number.
  
  Example 1: 
  Input: [1,2,1]
  Output: [2,-1,2]
  Explanation: The first 1's next greater number is 2; 
  The number 2 can't find next greater number; 
  The second 1's next greater number needs to search circularly, which is also 2.
  
  Note: The length of given array won't exceed 10000. 
  */

  @Test
  public void test() {
    assertArrayEquals(new int[] { 120, 11, 120, 120, 123, 123, -1, 100, 100, 100 },
        nextGreaterElements(new int[] { 100, 1, 11, 1, 120, 111, 123, 1, -1, -100 }));
    assertArrayEquals(new int[] { 2, 3, -1, 3, 2 }, nextGreaterElements(new int[] { 1, 2, 3, 2, 1 }));
    assertArrayEquals(new int[] { 2, -1, 2 }, nextGreaterElements(new int[] { 1, 2, 1 }));
  }

  public int[] nextGreaterElements(int[] nums) {
    int[] result = new int[nums.length];
    Arrays.fill(result, -1);
    Stack<Integer> stack = new Stack<>();
    for (int i = 0, len = nums.length << 1, num; i < len; i++) {
      while (!stack.isEmpty() && nums[stack.peek()] < (num = nums[i % nums.length]))
        result[stack.pop()] = num;
      if (i < nums.length) stack.push(i);
    }
    return result;
  }
}
