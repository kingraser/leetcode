package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.Test;

public class MinimumIndexSumofTwoLists {

  /*
  Suppose Andy and Doris want to choose a restaurant for dinner, 
  and they both have a list of favorite restaurants represented by strings.  
  You need to help them find out their common interest with the least list index sum. 
  If there is a choice tie between answers, output all of them with no order requirement. 
  You could assume there always exists an answer.
  
  Example 1:  
  Input:
  ["Shogun", "Tapioca Express", "Burger King", "KFC"]
  ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
  Output: ["Shogun"]
  Explanation: The only restaurant they both like is "Shogun".
  
  Example 2:  
  Input:
  ["Shogun", "Tapioca Express", "Burger King", "KFC"]
  ["KFC", "Shogun", "Burger King"]
  Output: ["Shogun"]
  Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
  
  Note:  
    The length of both lists will be in the range of [1, 1000].
    The length of strings in both lists will be in the range of [1, 30].
    The index is starting from 0 to the list length minus 1.
    No duplicates in both lists.  
  */

  @Test
  public void test() {
    assertArrayEquals(new String[] { "Shogun" },
        findRestaurant(new String[] { "Shogun", "Tapioca Express", "Burger King", "KFC" },
            new String[] { "Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun" }));
    assertArrayEquals(new String[] { "Shogun" },
        findRestaurant(new String[] { "Shogun", "Tapioca Express", "Burger King", "KFC" },
            new String[] { "KFC", "Shogun", "Burger King" }));
  }

  public String[] findRestaurant(String[] list1, String[] list2) {
    List<String> result = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();
    IntStream.range(0, list1.length).forEach(i -> map.put(list1[i], i));
    for (int i = 0, minSum = Integer.MAX_VALUE, sum; i < list2.length; i++)
      if (map.containsKey(list2[i]) && (sum = map.get(list2[i]) + i) <= minSum) {
        if (sum < minSum) {
          result.clear();
          minSum = sum;
        }
        result.add(list2[i]);
      }
    return result.toArray(String[]::new);
  }

}
