package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Test;

public class DesignHitCounter {

  /*
  Design a hit counter which counts the number of hits received in the past 5 minutes.  
  Each function accepts a timestamp parameter (in seconds granularity) 
  and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). 
  You may assume that the earliest timestamp starts at 1.  
  It is possible that several hits arrive roughly at the same time.
  
  Example:  
  HitCounter counter = new HitCounter();  
  // hit at timestamp 1.
  counter.hit(1);  
  // hit at timestamp 2.
  counter.hit(2);  
  // hit at timestamp 3.
  counter.hit(3);  
  // get hits at timestamp 4, should return 3.
  counter.getHits(4);  
  // hit at timestamp 300.
  counter.hit(300);  
  // get hits at timestamp 300, should return 4.
  counter.getHits(300);  
  // get hits at timestamp 301, should return 3.
  counter.getHits(301); 
  
  Follow up:
  What if the number of hits per second could be very large? Does your design scale? 
  */

  @Test
  public void test() {
    HitCounter counter = new HitCounter();
    IntStream.range(1, 4).forEach(i -> counter.hit(i));
    assertEquals(3, counter.getHits(4));
    counter.hit(300);
    assertEquals(4, counter.getHits(300));
    assertEquals(3, counter.getHits(301));
  }

  public class HitCounter {
    int idx, counter[][] = new int[300][2]; // first for time, second for hit

    public void hit(int timestamp) {
      if (counter[idx = timestamp % 300][0] != timestamp) counter[idx] = new int[] { timestamp, 1 };
      else counter[idx][1]++;
    }

    public int getHits(int timestamp) {
      return Arrays.stream(counter).filter(A -> timestamp - A[0] < 300).mapToInt(A -> A[1]).sum();
    }
  }
}
