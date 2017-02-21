package leetcode;

import java.util.Arrays;

public class ThreeSumSmaller {

  /*
  Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
  For example, given nums = [-2, 0, 1, 3], and target = 2.
  Return 2. Because there are two triplets which sums are less than 2:  
    [-2, 0, 1]
    [-2, 0, 3]  
  Follow up: Could you solve it in O(n2) runtime?
  */

  public int threeSumSmaller(int[] nums, int target) {
    int result = 0;
    Arrays.sort(nums);
    for (int i = 0, last = nums.length - 1, lastTwo = last - 1; i < lastTwo; i++)
      if (min(i, nums) >= target) break;
      else for (int left = i + 1, right = last; left < right;)
        if (nums[i] + nums[left] + nums[right] >= target) right--;
        else {
          result += right - left;
          left++;
        }
    return result;
  }

  private int min(int i, int[] nums) {
    return nums[i++] + nums[i++] + nums[i];
  }

}
