package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

public class Flatten2DVector {

  /*
  Implement an iterator to flatten a 2d vector.
  For example,
  Given 2d vector =
  
  [
  [1,2],
  [3],
  [4,5,6]
  ]
  
  By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
  Hint:  
    How many variables do you need to keep track?
    Two variables is all you need. Try with x and y.
    Beware of empty rows. It could be the first few rows.
    To write correct code, think about the invariant to maintain. What is it?
    The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
    Not sure? Think about how you would implement hasNext(). Which is more complex?
    Common logic in two different places should be refactored into a common method.
  */

  @Test
  public void test() {
    Vector2D test = new Vector2D(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3), Arrays.asList(4, 5, 6)));
    List<Integer> actual = new ArrayList<>();
    while (test.hasNext())
      actual.add(test.next());
    assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), actual);

    test = new Vector2D(Arrays.asList(Arrays.asList(1, 2), new ArrayList<>(), Arrays.asList(4, 5, 6)));
    actual = new ArrayList<>();
    while (test.hasNext())
      actual.add(test.next());
    assertEquals(Arrays.asList(1, 2, 4, 5, 6), actual);

    test = new Vector2D(Arrays.asList(Arrays.asList(1, 2), null, Arrays.asList(4, 5, 6)));
    actual = new ArrayList<>();
    while (test.hasNext())
      actual.add(test.next());
    assertEquals(Arrays.asList(1, 2, 4, 5, 6), actual);

    test = new Vector2D(null);
    actual = new ArrayList<>();
    while (test.hasNext())
      actual.add(test.next());
    assertEquals(new ArrayList<>(), actual);
  }

  public class Vector2D {
    private Iterator<List<Integer>> listIterator = null;
    private Iterator<Integer> iterator = null;

    public Vector2D(List<List<Integer>> list) {
      if (list != null) listIterator = list.iterator();
    }

    public int next() {
      if (!hasNext()) throw new NoSuchElementException();
      return iterator.next();
    }

    public boolean hasNext() {
      if (iterator != null && iterator.hasNext()) return true;
      if (listIterator == null || !listIterator.hasNext()) return false;
      for (List<Integer> list; listIterator.hasNext();)
        if ((list = listIterator.next()) != null) {
          iterator = list.iterator();
          break;
        }
      return hasNext();
    }
  }
}
