/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

import org.junit.Test;

import com.google.common.collect.Lists;

import leetcode.common.NestedInteger;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年4月18日;
//-------------------------------------------------------
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
        Lists.newArrayList(new NestedIterator(
            Arrays.asList(new NestedInteger(Arrays.asList(new NestedInteger(1), new NestedInteger(1))),
                new NestedInteger(2), new NestedInteger(Arrays.asList(new NestedInteger(1), new NestedInteger(1)))))));
    assertEquals(Arrays.asList(1, 4, 6),
        Lists.newArrayList(new NestedIterator(Arrays.asList(new NestedInteger(1), new NestedInteger(
            Arrays.asList(new NestedInteger(4), new NestedInteger(Arrays.asList(new NestedInteger(6)))))))));
  }

  public class NestedIterator implements Iterator<Integer> {
    private Integer next;
    private Stack<Iterator<NestedInteger>> stack = new Stack<>();

    public NestedIterator(List<NestedInteger> nestedList) {
      if (nestedList != null) stack.push(nestedList.iterator());
      getNext();
    }

    private void getNext() {
      next = null;
      for (NestedInteger i; next == null && !stack.isEmpty();)
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
