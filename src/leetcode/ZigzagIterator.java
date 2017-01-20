package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.junit.Test;

public class ZigzagIterator {

  /*
  Given two 1d vectors, implement an iterator to return their elements alternately.  
  For example, given two 1d vectors:  
  v1 = [1, 2]
  v2 = [3, 4, 5, 6]  
  By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
  Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
  The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. 
  If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:
  
  [1,2,3]
  [4,5,6,7]
  [8,9]
  
  It should return [1,4,8,2,5,9,3,6,7].
  */

  @Test
  public void test() {
    List<Integer> actual = new ArrayList<>();
    ZigZagIterator iterator = new ZigZagIterator(Arrays.asList(Arrays.asList(1, 2, 3).iterator(),
        Arrays.asList(4, 5, 6, 7).iterator(), Arrays.asList(8, 9).iterator()));
    for (; iterator.hasNext(); actual.add(iterator.next()));
    assertEquals(Arrays.asList(1, 4, 8, 2, 5, 9, 3, 6, 7), actual);

    actual.clear();
    iterator = new ZigZagIterator(Arrays.asList(Arrays.asList(1, 2).iterator(), Arrays.asList(3, 4, 5, 6).iterator()));
    for (; iterator.hasNext(); actual.add(iterator.next()));
    assertEquals(Arrays.asList(1, 3, 2, 4, 5, 6), actual);

    actual.clear();
    iterator = new ZigZagIterator(
        Arrays.asList(Arrays.asList(1).iterator(), Arrays.asList(2, 3).iterator(), Arrays.asList(4, 5, 6).iterator()));
    for (; iterator.hasNext(); actual.add(iterator.next()));
    assertEquals(Arrays.asList(1, 2, 4, 3, 5, 6), actual);
  }

  public class ZigZagIterator implements Iterator<Integer> {
    List<Iterator<Integer>> iterators;
    int idx;

    public ZigZagIterator(List<Iterator<Integer>> list) {
      this.iterators = list.stream().filter(i -> i.hasNext()).collect(Collectors.toList());
      idx = 0;
    }

    public Integer next() {
      if (!hasNext()) throw new NoSuchElementException();
      Iterator<Integer> iterator = iterators.get(idx %= iterators.size());
      Integer result = iterator.next();
      if (!iterator.hasNext()) iterators.remove(idx);
      else idx++;
      return result;
    }

    public boolean hasNext() {
      return iterators.size() > 0;
    }
  }
}
