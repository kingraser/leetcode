package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GasStation {

  /*
  There are N gas stations along a circular route, 
  where the amount of gas at station i is gas[i].    
  You have a car with an unlimited gas tank and it costs cost[i] of gas 
  to travel from station i to its next station (i+1). 
  You begin the journey with an empty tank at one of the gas stations.    
  Return the starting gas station's index if you can travel around the circuit once, 
  otherwise return -1.  
  */

  @Test
  public void test() {
    assertEquals(0, canCompleteCircuit(new int[] { 3, 1, 2 }, new int[] { 1, 2, 3 }));
  }

  public int canCompleteCircuit(int[] gas, int[] cost) {
    int total = 0, start = 0;
    for (int i = 0, sum = 0; i < gas.length; total += gas[i] - cost[i], i++)
      if ((sum += gas[i] - cost[i]) < 0) {
        start = i + 1;
        sum = 0;
      }
    return total < 0 ? -1 : start;
  }

}
