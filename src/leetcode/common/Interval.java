
package leetcode.common;

import java.util.Objects;

public class Interval {
  public int start, end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public boolean equals(Object o) {
    if (Objects.isNull(o) || !(o instanceof Interval)) return false;
    Interval another = (Interval) o;
    return another.start == start && another.end == end;
  }

  @Override
  public String toString() {
    return String.format("[start:%d,end:%d]", start, end);
  }
}
