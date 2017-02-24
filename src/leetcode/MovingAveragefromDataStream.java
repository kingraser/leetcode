package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;

public class MovingAveragefromDataStream {

  /*
  Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
  
  For example,
  MovingAverage m = new MovingAverage(3);
  m.next(1) = 1
  m.next(10) = (1 + 10) / 2
  m.next(3) = (1 + 10 + 3) / 3
  m.next(5) = (10 + 3 + 5) / 3 
  */

  @Test
  public void test() {
    Stream stream = new Stream(3);
    assertEquals(1d, stream.next(1), 0);
    assertEquals(5.5d, stream.next(10), 0);
    assertEquals(14d / 3, stream.next(3), 0);
    assertEquals(18d / 3, stream.next(5), 0);
  }

  class Stream {
    private Deque<Integer> deque = new ArrayDeque<>();
    int size;
    double sum = 0;

    public Stream(int size) {
      this.size = size;
    }

    double next(int val) {
      if (size == 0) return 0;
      if (deque.size() == size) sum -= deque.pollFirst();
      deque.addLast(val);
      return (sum += val) / deque.size();
    }
  }
}
