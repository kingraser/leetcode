package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Objects;

import org.junit.Test;

public class ThreeSumClosest {

  /*
  Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
  Return the sum of the three integers. You may assume that each input would have exactly one solution.
  For example, given array S = {-1, 2, 1, -4}, and target = 1.
  The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
  */

  @Test
  public void test() {
    assertEquals(2, threeSumClosest(new int[] { -1, 2, 1, -4 }, 1));
  }

  public int threeSumClosest(int[] nums, int target) {
    Integer result = null;
    Arrays.sort(nums);
    for (int first = 0; first < nums.length - 2; first++)
      for (int left = first + 1, right = nums.length - 1, sum; left < right; result = Objects.isNull(result)
          || Math.abs(result - target) > Math.abs(sum - target) ? sum : result)
        if ((sum = nums[first] + nums[left] + nums[right]) == target) return target;
        else if (sum < target) left++;
        else right--;
    return result;
  }

}
