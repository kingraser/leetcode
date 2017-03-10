package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.Test;

public class OptimalAccountBalancing {

  /*
  A group of friends went on holiday and sometimes lent each other money. 
  For example, Alice paid for Bill's lunch for 10.Then later Chris gave Alice 5 for a taxi ride. 
  We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. 
  Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), 
  the transactions can be represented as [[0, 1, 10], [2, 0, 5]].  
  Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.  
  Note:  
    A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
    Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
    
  Example 1:  
  Input: [[0,1,10], [2,0,5]]  
  Output: 2
  
  Explanation:
  Person #0 gave person #1 $10.
  Person #2 gave person #0 $5.
  
  Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
  
  Example 2:  
  Input: [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
  Output: 1
  
  Explanation:
  Person #0 gave person #1 $10.
  Person #1 gave person #0 $1.
  Person #1 gave person #2 $5.
  Person #2 gave person #0 $5.
  
  Therefore, person #1 only need to give person #0 $4, and all debt is settled.
  */

  /*
  This is a NP-Hard problem, so we need an approximation algorithm.
  Just shuffle the positive and negative List N times(instead of all possible permutations).
  */

  @Test
  public void test() {
    assertEquals(2, minTransfers(new int[][] { { 0, 1, 10 }, { 2, 0, 5 } }));
    assertEquals(1, minTransfers(new int[][] { { 0, 1, 10 }, { 1, 0, 1 }, { 1, 2, 5 }, { 2, 0, 5 } }));
    assertEquals(3, minTransfers(new int[][] { { 0, 3, 2 }, { 1, 4, 3 }, { 2, 3, 2 }, { 2, 4, 2 } }));
  }

  public int minTransfers(int[][] transactions) {
    Map<Integer, Integer> accountMap = new HashMap<>();
    for (int[] transaction : transactions) {
      accountMap.compute(transaction[0], (k, v) -> (v == null ? 0 : v) - transaction[2]);
      accountMap.compute(transaction[1], (k, v) -> (v == null ? 0 : v) + transaction[2]);
    }
    LinkedList<Integer> positiveList = new LinkedList<>(), negativeList = new LinkedList<>();
    for (int amount : accountMap.values())
      if (amount == 0) continue;
      else if (amount < 0) negativeList.add(amount);
      else positiveList.add(amount);
    return dfs(positiveList, negativeList);
  }

  //dfs and backtracking
  private int dfs(LinkedList<Integer> positiveList, LinkedList<Integer> negativeList) {
    if (positiveList.size() == 1) return negativeList.size();
    if (negativeList.size() == 1) return positiveList.size();
    int result = Integer.MAX_VALUE;
    for (int i = 0, sum, negative, positive; i < positiveList.size(); i++)
      for (int j = 0; j < negativeList.size(); j++) {
        if ((sum = (positive = positiveList.get(i)) + (negative = negativeList.get(j))) == 0) {
          positiveList.remove(i);
          negativeList.remove(j);
        } else if (sum > 0) {
          positiveList.set(i, sum);
          negativeList.remove(j);
        } else {
          positiveList.remove(i);
          negativeList.set(j, sum);
        }
        result = Math.min(result, 1 + dfs(positiveList, negativeList));
        if (sum == 0) {
          positiveList.add(i, positive);
          negativeList.add(j, negative);
        } else if (sum > 0) {
          positiveList.set(i, positive);
          negativeList.add(j, negative);
        } else {
          positiveList.add(i, positive);
          negativeList.set(j, negative);
        }
      }
    return result;
  }

}
