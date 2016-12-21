/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode.common;

import java.util.ArrayList;
import java.util.List;

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
}