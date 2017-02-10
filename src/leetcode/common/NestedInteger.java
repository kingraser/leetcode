/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof NestedInteger)) return false;
    NestedInteger other = (NestedInteger) o;
    return isInteger() ? other.isInteger() && getInteger().equals(other.getInteger())
        : Objects.equals(getList(), other.getList());
  }
}
