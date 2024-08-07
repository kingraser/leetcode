package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.*;

import leetcode.util.CollectionUtils;
import org.junit.Test;

import leetcode.common.NestedInteger;

public class FlattenNestedListIterator {

  /*
  Given a nested list of integers, implement an iterator to flatten it.    
  Each element is either an integer, or a list whose elements may also be integers or other lists.    
  Example 1:
  Given the list [[1,1],2,[1,1]],    
  By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].    
  Example 2:
  Given the list [1,[4,[6]]],    
  By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6]. 
  */

    @Test
    public void test() {
        assertEquals(Arrays.asList(1, 1, 2, 1, 1),
                CollectionUtils.asList(new NestedIterator(NestedInteger.fromString("[[1,1],2,[1,1]]").getList())));
        assertEquals(Arrays.asList(1, 4, 6),
                CollectionUtils.asList(new NestedIterator(NestedInteger.fromString("[1,[4,[6]]]").getList())));
    }

    public static class NestedIterator implements Iterator<Integer> {
        private Integer next;
        private final Stack<Iterator<NestedInteger>> stack = new Stack<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            if (nestedList != null) stack.push(nestedList.iterator());
            getNext();
        }

        private void getNext() {
            next = null;
            for (NestedInteger i; next == null && !stack.isEmpty(); )
                if (!stack.peek().hasNext()) stack.pop();
                else if ((i = stack.peek().next()).isInteger()) next = i.getInteger();
                else stack.push(i.getList().iterator());
        }

        @Override
        public Integer next() {
            if (next == null) throw new NoSuchElementException();
            Integer val = next;
            getNext();
            return val;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }
    }
}
