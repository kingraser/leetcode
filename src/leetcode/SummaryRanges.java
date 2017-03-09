package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SummaryRanges {

  /*    
  Given a sorted integer array without duplicates, return the summary of its ranges.
  
  For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"]. 
  */

  public List<String> summaryRanges(int[] nums) {
    List<String> result = new ArrayList<>(nums.length);
    for (int i = 0, start; i < nums.length; i++) {
      for (start = nums[i]; i + 1 < nums.length && nums[i + 1] == nums[i] + 1; i++);
      if (start != nums[i]) result.add(String.join("->", Integer.toString(start), Integer.toString(nums[i])));
      else result.add(Integer.toString(start));
    }
    return result;
  }

  @Test
  public void test() {
    assertEquals(Arrays.asList("0->2", "4->5", "7"), summaryRanges(new int[] { 0, 1, 2, 4, 5, 7 }));
  }

}
