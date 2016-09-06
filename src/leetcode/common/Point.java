/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode.common;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月16日<p>
//-------------------------------------------------------
public class Point {

  public int x = 0, y = 0;

  public Point() {
  }

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int hashCode() {
    return 2 * x + 3 * y;
  }

  @Override
  public boolean equals(Object obj) {
    if (Objects.isNull(obj) || !(obj instanceof Point)) return false;
    Point point = (Point) obj;
    return x == point.x && y == point.y;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}
