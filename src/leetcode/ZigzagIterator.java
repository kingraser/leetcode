package leetcode;

import static leetcode.util.CollectionUtils.asList;
import static org.junit.Assert.assertEquals;

import java.util.*;
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
        assertEquals(List.of(1, 4, 8, 2, 5, 9, 3, 6, 7),
                asList(new ZigZagIterator(List.of(List.of(1, 2, 3).iterator(),
                        List.of(4, 5, 6, 7).iterator(), List.of(8, 9).iterator()))));

        assertEquals(List.of(1, 3, 2, 4, 5, 6), asList(
                new ZigZagIterator(List.of(List.of(1, 2).iterator(), List.of(3, 4, 5, 6).iterator()))));

        assertEquals(List.of(1, 2, 4, 3, 5, 6), asList(new ZigZagIterator(Arrays
                .asList(new ArrayList<>(List.of(1)).iterator(), List.of(2, 3).iterator(), List.of(4, 5, 6).iterator()))));
    }

    public static class ZigZagIterator implements Iterator<Integer> {
        List<Iterator<Integer>> iterators;
        int idx = 0;

        public ZigZagIterator(List<Iterator<Integer>> list) {
            this.iterators = list.stream().filter(Iterator::hasNext).collect(Collectors.toList());
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
            return !iterators.isEmpty();
        }
    }
}
