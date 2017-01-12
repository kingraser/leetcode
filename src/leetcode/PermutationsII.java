/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月10日<p>
//-------------------------------------------------------
public class PermutationsII {
  /*
  Given a collection of numbers that might contain duplicates, 
  return all possible unique permutations.
  
  For example,
  [1,1,2] have the following unique permutations:
  [1,1,2], [1,2,1], and [2,1,1]. 
  */

  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(result, Arrays.stream(nums).boxed().collect(Collectors.toList()), 0);
    return result;
  }

  private void dfs(List<List<Integer>> result, List<Integer> list, int idx) {
    if (idx == list.size() - 1) result.add(new ArrayList<>(list));
    else {
      Set<Integer> set = new HashSet<>();
      for (int i = idx; i < list.size(); i++) {
        if (!set.add(list.get(i))) continue;
        if (idx != i) Collections.swap(list, idx, i);
        dfs(result, list, idx + 1);
        if (idx != i) Collections.swap(list, i, idx);
      }
    }
  }

  @Test
  public void test() {
    List<List<Integer>> expected = Arrays.asList(Arrays.asList(1, 1, 2), Arrays.asList(1, 2, 1),
        Arrays.asList(2, 1, 1));
    assertEquals(new HashSet<>(expected), new HashSet<>(permuteUnique(new int[] { 1, 1, 2 })));
    expected = Arrays.asList(Arrays.asList(2, 1, 2, 1), Arrays.asList(2, 1, 1, 2), Arrays.asList(2, 2, 1, 1),
        Arrays.asList(1, 2, 2, 1), Arrays.asList(1, 2, 1, 2), Arrays.asList(1, 1, 2, 2));
    assertEquals(new HashSet<>(expected), new HashSet<>(permuteUnique(new int[] { 2, 1, 2, 1 })));
  }

}
