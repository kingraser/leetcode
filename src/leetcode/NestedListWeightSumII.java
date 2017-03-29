package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import leetcode.common.NestedInteger;

public class NestedListWeightSumII {

  /*
  Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
  
  Each element is either an integer, or a list -- whose elements may also be integers or other lists.
  
  Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
  
  Example 1:
  Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
  
  Example 2:
  Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)  
  */

  @Test
  public void test() {
    assertEquals(8, depthSumInverse(NestedInteger.fromString("[[1,1],2,[1,1]]").getList()));
    assertEquals(17, depthSumInverse(NestedInteger.fromString("[1,[4,[6]]]").getList()));
  }

  public int depthSumInverse(List<NestedInteger> list) {
    int unweighted = 0, weighted = 0;
    for (List<NestedInteger> next = new ArrayList<>(); !list
        .isEmpty(); weighted += unweighted, list = next, next = new ArrayList<>())
      for (NestedInteger ni : list)
        if (ni.isInteger()) unweighted += ni.getInteger();
        else next.addAll(ni.getList());
    return weighted;
  }
}
