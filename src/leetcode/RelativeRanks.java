package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

public class RelativeRanks {

  /*
  Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".
  
  Example 1:
  
  Input: [5, 4, 3, 2, 1]
  Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
  Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 
  For the left two athletes, you just need to output their relative ranks according to their scores.
  
  Note:
  
    N is a positive integer and won't exceed 10,000.
    All the scores of athletes are guaranteed to be unique.
  */

  @Test
  public void test() {
    assertArrayEquals(new String[] { "Gold Medal", "Silver Medal", "Bronze Medal", "4", "5" },
        findRelativeRanks(new int[] { 5, 4, 3, 2, 1 }));
  }

  int idx;

  public String[] findRelativeRanks(int[] nums) {
    int[] sorted = Arrays.stream(nums).sorted().toArray();
    return Arrays.stream(nums).boxed().map(
        score -> medalMap.getOrDefault(idx = nums.length - Arrays.binarySearch(sorted, score), Integer.toString(idx)))
        .toArray(String[]::new);
  }

  private final Map<Integer, String> medalMap = Map.of(1, "Gold Medal", 2, "Silver Medal", 3, "Bronze Medal");
}
