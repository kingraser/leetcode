package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MajorityElement {

  /*    
  Given an array of size n, find the majority element. 
  The majority element is the element that appears more than n/2 times.
  */

  @Test
  public void test() {
    assertEquals(1, majorityElement(new int[] { 2, 3, 4, 1, 1, 1, 1 }));
  }

  public int majorityElement(int[] num) {
    int major = num[0];
    for (int i = 1, count = 1; i < num.length && count <= num.length >> 1; i++)
      if (major == num[i]) count++;
      else if (count-- == 0) {
        count = 1;
        major = num[i];
      }
    return major;
  }
}
