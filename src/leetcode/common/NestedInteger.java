/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年8月22日;
//-------------------------------------------------------
public class NestedInteger {
  Integer val;
  List<NestedInteger> list;

  public NestedInteger() {
    this(new ArrayList<>());
  }

  public NestedInteger(int val) {
    this.val = val;
  }

  public NestedInteger(List<NestedInteger> list) {
    this.list = list;
  }

  public boolean isInteger() {
    return val != null;
  }

  public Integer getInteger() {
    return val;
  }

  public void setInteger(Integer val) {
    this.val = val;
  }

  public void add(NestedInteger ni) {
    this.list.add(ni);
  }

  public List<NestedInteger> getList() {
    return list;
  }

  @Override
  public String toString() {
    return val != null ? Integer.toString(val)
        : "[" + String.join(",", list.stream().map(i -> i.toString()).collect(Collectors.toList())) + "]";
  }

  public static NestedInteger fromString(String s) {
    Stack<NestedInteger> stack = new Stack<>();
    int top, num = 0;
    for (char c : s.toCharArray())
      if (c == '[') stack.push(new NestedInteger());
      else if (c == '-') stack.push(new NestedInteger(Integer.MIN_VALUE));
      else if (c >= '0' && c <= '9') {
        if (stack.isEmpty() || !stack.peek().isInteger()) stack.push(new NestedInteger(c - '0'));
        else stack.peek().setInteger(Objects.equals(Integer.MIN_VALUE, top = stack.peek().getInteger()) ? '0' - c
            : top * 10 + (top < 0 ? '0' - c : c - '0'));
      } else if (c == ',' && (num > 0 || stack.peek().isInteger())) {
        push(stack);
        num = 0;
      } else if (c == ']') {
        if (stack.peek().isInteger()) num = 1;
        push(stack);
      }
    return stack.pop();
  }

  private static void push(Stack<NestedInteger> stack) {
    if (stack.size() < 2) return;
    NestedInteger i = stack.pop();
    stack.peek().add(i);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof NestedInteger)) return false;
    NestedInteger other = (NestedInteger) o;
    return isInteger() ? other.isInteger() && getInteger().equals(other.getInteger())
        : Objects.equals(getList(), other.getList());
  }
}
