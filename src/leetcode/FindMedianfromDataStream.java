/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月19日<p>
//-------------------------------------------------------
public class FindMedianfromDataStream {

  /*
  Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
  Examples:
  
  [2,3,4] , the median is 3
  
  [2,3], the median is (2 + 3) / 2 = 2.5
  
  Design a data structure that supports the following two operations:
  
  void addNum(int num) - Add a integer number from the data stream to the data structure.
  double findMedian() - Return the median of all elements so far.
  
  For example:
  
  add(1)
  add(2)
  findMedian() -> 1.5
  add(3) 
  findMedian() -> 2
  */

  @Test
  public void test() {
    DataStream stream = new DataStream();
    stream.addNum(1);
    stream.addNum(2);
    assertEquals(1.5, stream.findMedian(), 0);
    stream.addNum(3);
    assertEquals(2, stream.findMedian(), 0);
  }

  public static class DataStream {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(), maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    // Adds a number into the data structure.
    public void addNum(int num) {
      if (maxHeap.isEmpty() || num <= maxHeap.peek()) maxHeap.offer(num);
      else minHeap.offer(num);
      adjust();
    }

    public void remove(Integer i) {
      if (i <= maxHeap.peek()) maxHeap.remove(i);
      else minHeap.remove(i);
      adjust();
    }

    private void adjust() {
      for (; maxHeap.size() < minHeap.size(); maxHeap.offer(minHeap.poll()));
      for (; minHeap.size() < maxHeap.size() - 1; minHeap.offer(maxHeap.poll()));
    }

    // Returns the median of current data stream
    public double findMedian() {
      if (maxHeap.size() == minHeap.size()) return ((double) maxHeap.peek() + minHeap.peek()) / 2;
      return maxHeap.peek();
    }
  }

}
